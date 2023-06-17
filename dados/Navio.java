package dados;

public class Navio{

    private String nome;
    private double velocidade;
    private double autonomia;
    private double custoPorMilhaBasico;
    
    public Navio(String nome, double velocidade, double autonomia, double custoPorMilhaBasico){
        this.nome = nome;
        this.velocidade = velocidade;
        this.autonomia = autonomia;
        this.custoPorMilhaBasico = custoPorMilhaBasico;
    }
    
    // Getters and setters for the class properties

    public String getNome(){
        return nome;
    }
    
    public double getVelocidade(){
        return velocidade;
    }
    
    public double autonomia(){
        return autonomia;
    }
    
    public double getCustoPorMilhaBasico(){
        return custoPorMilhaBasico;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setVelocidade(double velocidade){
        this.velocidade = velocidade;
    }
       
    public void setAutonomia(double autonomia){
        this.autonomia = autonomia;
    }

    public void setCustoMilha(double custoPorMilhaBasico){
        this.custoPorMilhaBasico = custoPorMilhaBasico;
    }
}