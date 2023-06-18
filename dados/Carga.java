package dados;

public class Carga {

    private int identificador;
    private int peso;
    private Double valorDeclarado;
    private int tempoMaximo;
    private String situacao;

    public Carga(int identificador, int peso, Double valorDeclarado, int tempoMaximo, String situacao) {
        this.identificador = identificador;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
        this.tempoMaximo = tempoMaximo;
        this.situacao = situacao;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(Double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public int getTempoMaximo() {
        return tempoMaximo;
    }

    public void setTempoMaximo(int tempoMaximo) {
        this.tempoMaximo = tempoMaximo;
    }

    public String getSituacao(){
        return situacao;
    }

    public void setSituacao(String situacao){
        this.situacao = situacao;
    }
}