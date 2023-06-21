package dados;

import java.util.ArrayList;

public class Carga {

    private int identificador;
    private int portoOrigem;
    private int portoDestino;
    private Cliente codigo;
    private int peso;
    private double valorDeclarado;
    private int tempoMaximo;
    private String situacao;
    private TipoCarga numero;
    private String prioridade;
    private Navio nome;
    private double frete;

    public Carga(int identificador, int portoOrigem, int portoDestino, Cliente codigo, int peso, double valorDeclarado, int tempoMaximo, String situacao, TipoCarga numero, String prioridade) {
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
        this.nome = null;
        this.frete = 0.00;
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

    public double getValorDeclarado() {
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

    public Navio getNome(){
        return nome;
    }

    public double getFrete(){
        return frete;
    }

    public void setFrete(Navio n, ArrayList<Porto> portos, ArrayList<Distancia> distancias) {
        double frete = 0.00;
        Distancia dist = distancias.stream().filter(d -> ((d.getOrigem() == this.getPortoOrigem() && d.getDestino() == this.getPortoDestino()) || (d.getOrigem() == this.getPortoDestino() && d.getDestino() == this.getPortoOrigem()))).toList().get(0);
        if(prioridade.equals("BARATO")) {
            frete += (dist.getDistancia() * n.getCustoPorMilhaBasico());
        }
        else if(prioridade.equals("RAPIDO")) {
            frete += (dist.getDistancia() * (n.getCustoPorMilhaBasico() * 2));
        }
        if (this.getNumero() instanceof CargaPerecivel) {
            frete += this.getPeso() * 2;
        }
        else if (this.getNumero() instanceof CargaDuravel) {
            CargaDuravel cd = (CargaDuravel) this.getNumero();
            double imposto = valorDeclarado * (cd.getPorcentagemIPI() / 100);
            frete += this.getPeso() * 1.5 + imposto;
        }
        Porto portoOrigem = portos.stream().filter(p -> p.getId() == this.getPortoOrigem()).toList().get(0);
        Porto portoDestino = portos.stream().filter(p -> p.getId() == this.getPortoDestino()).toList().get(0);
        if(portoOrigem.getPais().equals("Brasil") && portoDestino.getPais().equals("BRASIL")) {
            frete += 10000;
        }
        else {
            frete += 50000;
        }
        this.frete = frete;
    }

}