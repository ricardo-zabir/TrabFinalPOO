package dados;

public class Porto {
    
    private int id;
    private String nome;
    private String pais;
    private int distancia;

    public Porto(int id, String nome, String pais, int distancia) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.distancia = distancia;
    }

    // Getters and setters for the class properties

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
}