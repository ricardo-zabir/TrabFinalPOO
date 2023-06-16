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
    public int getCodigo() {
        return codigo;
    }
    public String getEmail() {
        return email;
    }
    public String getNome() {
        return nome;
    }

}
