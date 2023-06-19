package dados;

public class Carga {

    private int identificador;
    private int portoOrigem;
    private int portoDestino;
    private Cliente codigo;
    private int peso;
    private Double valorDeclarado;
    private int tempoMaximo;
    private String situacao;
    private TipoCarga numero;
    private String prioridade;

    public Carga(int identificador, int portoOrigem, int portoDestino, Cliente codigo, int peso, Double valorDeclarado, int tempoMaximo, String situacao, TipoCarga numero, String prioridade) {
        this.identificador = identificador;
        this.portoOrigem = portoOrigem;
        this.portoDestino = portoDestino;
        this.codigo = codigo;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
        this.tempoMaximo = tempoMaximo;
        this.situacao = situacao;
        this.numero = numero;
        this.prioridade = prioridade;
    }

    public int getIdentificador() {
        return identificador;
    }

    public int getPortoOrigem() {
        return portoOrigem;
    }

    public int getPortoDestino() {
        return portoDestino;
    }

    public void setPortoDestino(int portoDestino) {
        this.portoDestino = portoDestino;
    }

    public Cliente getCodigo() {
        return codigo;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public TipoCarga getNumero() {
        return numero;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

}