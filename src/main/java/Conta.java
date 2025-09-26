import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Conta {
    private static final AtomicLong SEQ;
    private static int agenciaDefault;

    static{
        SEQ  = new AtomicLong(1);
        agenciaDefault = 1;
    }

    private long numero;
    private BigDecimal saldo;
    private final int agencia;
    private final Cliente titular;

    {
        numero = SEQ.getAndIncrement();
        saldo = new BigDecimal(0);
    }

    public Conta(int agencia, Cliente titular) {
        this.agencia = agencia;
        this.titular = titular;
    }

    public Conta(Cliente titular) {
        this.titular = titular;
        this.agencia = agenciaDefault;
    }

    public void transferir(BigDecimal quantia, Conta contaRecebedoraDoDeposito) {
        try {
            this.sacar(quantia);
            contaRecebedoraDoDeposito.depositar(quantia);
        } catch (RuntimeException e) {
            throw new RuntimeException("Não foi possível concluir a transferência bancária: " + e.getMessage());
        }
    }

    public void depositar(BigDecimal quantia) {
        if (quantia == null || quantia.longValue() <= 0) {
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

    public static void setAgenciaDefault(int agenciaDefault) {
        Conta.agenciaDefault = agenciaDefault;
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
