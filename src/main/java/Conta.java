import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

//todo fixar escala (2 casas) e RoundingMode na entrada (normaliza).
public class Conta {
    private static final AtomicLong SEQ = new AtomicLong(1);
    private static int agenciaDefault = 1;

    public static void definirAgenciaDefault(int novaAgenciaDefault) {
        agenciaDefault = novaAgenciaDefault;
    }

    private long numero = SEQ.getAndIncrement();
    private BigDecimal saldo = BigDecimal.ZERO;
    private final int agencia;
    private final Cliente titular;

    public Conta(int agencia, Cliente titular) {
        if (titular == null){
            throw new IllegalArgumentException("Titular não pode ser nulo");
        }
        if (agencia < 0){
            throw new IllegalArgumentException("Agência inválida");
        }
        this.agencia = agencia;
        this.titular = titular;
    }

    public Conta(Cliente titular) {
        this.titular = titular;
        this.agencia = agenciaDefault;
    }

    public void transferir(BigDecimal quantia, Conta contaDestino) {
        if (this.equals(contaDestino)) {
            throw new IllegalArgumentException("Conta de destino não pode ser a mesma que a pagadora");
        }
        try {
            this.sacar(quantia);
            contaDestino.depositar(quantia);
        } catch (RuntimeException e) {
            throw new RuntimeException("Não foi possível concluir a transferência bancária: " + e.getMessage(), e.getCause());
        }
    }

    public void depositar(BigDecimal quantia) {
        if (quantia == null || quantia.signum() <= 0) {
            throw new IllegalArgumentException("Valor de depósito não pode ser nulo, 0 ou negativo");
        }
        this.saldo = this.saldo.add(quantia);
    }

    public void sacar(BigDecimal quantia) {
        if (quantia == null || quantia.signum() <= 0) {
            throw new IllegalArgumentException("Valor de saque não pode ser nulo, 0 ou negativo");
        }
        if (saldo.compareTo(quantia) < 0) {
            throw new IllegalArgumentException("Valor de saque não pode ser maior que o saldo");
        }
        this.saldo = this.saldo.subtract(quantia);
    }

    public static int getAgenciaDefault() {
        return agenciaDefault;
    }

    public long getNumero() {
        return numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public int getAgencia() {
        return agencia;
    }

    public Cliente getTitular() {
        return titular;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numero=" + numero +
                ", saldo=" + saldo +
                ", agencia=" + agencia +
                ", titular=" + titular +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return numero == conta.numero && agencia == conta.agencia && Objects.equals(titular, conta.titular);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, agencia, titular);
    }
}
