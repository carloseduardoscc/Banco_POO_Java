import java.util.Arrays;
import java.util.Objects;

public class Banco {
    private static final int MAX_CONTAS = 50;

    private Conta[] contas = new Conta[MAX_CONTAS];
    private String nomeSocial;
    private final String cnpj;

    public Banco(String nomeSocial, String cnpj) {
        //todo validação funcional de nomeSocial e cnpj
        if (cnpj == null || cnpj.isBlank()) throw new IllegalArgumentException("CNPJ do banco não pode ser nulo ou em branco");
        setNomeSocial(nomeSocial);
        this.cnpj = cnpj;
    }

    public void abrirConta(Cliente clienteTitular){
        if (clienteTitular == null) throw new IllegalArgumentException("Cliente titular não pode ser nulo");
        Conta contaNova = new Conta(clienteTitular);
        for(int i = 0; i < MAX_CONTAS; i++){
            if(contas[i] == null){
                contas[i] = contaNova;
                return;
            }
        }
        throw new IllegalStateException("Não há mais espaços disponíveis para novas contas");
    }

    public Conta buscarPorNumero(long numConta){
        if (numConta < 0) throw new IllegalArgumentException("Numero de conta informado para busca não pode ser negativo");
        for(Conta conta : contas){
            if (conta != null && conta.getNumero() == numConta){
                return conta;
            }
        }
        return null;
    }

    public void setNomeSocial(String nomeSocial) {
        if (nomeSocial == null || nomeSocial.isBlank()) throw new IllegalArgumentException("Nome social do banco não pode ser nulo ou em branco");
        this.nomeSocial = nomeSocial;
    }

    public Conta[] getContas() {
        return contas.clone();
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "contas=" + Arrays.toString(contas) +
                ", nomeSocial='" + nomeSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Banco banco = (Banco) o;
        return Objects.equals(cnpj, banco.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cnpj);
    }
}
