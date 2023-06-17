package dados;

public class Cliente {

    private String nome;
    private int codigo;
    private String email;

    public Cliente(String nome, int codigo, String email) {
        this.nome = nome;
        this.codigo = codigo;
        this.email = email;
    }

    // Getters and setters for the class properties

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
