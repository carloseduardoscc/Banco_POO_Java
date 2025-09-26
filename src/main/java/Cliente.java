import java.util.Objects;

public class Cliente {
    private String nome;
    private final String cpf;

    public Cliente(String cpf, String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome não pode ser nulo ou em branco");
        if (cpf == null || !cpf.matches("\\d{11}")) throw new IllegalArgumentException("Nome não pode ser nulo ou em branco");
        this.cpf = cpf;
        this.nome = nome.trim();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf=" + cpf +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cpf == cliente.cpf;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }
}
