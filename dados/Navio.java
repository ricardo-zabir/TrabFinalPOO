package dados;

public class Navio{

    private String nome;
    private double velocidade;
    private double autonomia;
    private double custoPorMilhaBasico;
    private double capacidade;
    private String status;
    private Carga carga;
    
    public Navio(String nome, double velocidade, double autonomia, double custoPorMilhaBasico, double capacidade, String status){
        this.nome = nome;
        this.velocidade = velocidade;
        this.autonomia = autonomia;
        this.custoPorMilhaBasico = custoPorMilhaBasico;
        this.capacidade = capacidade;
        this.status = status;
        this.carga = null;
    }
    
    // Getters and setters for the class properties

    public String getNome(){
        return nome;
    }
    
    public double getVelocidade(){
        return velocidade;
    }
    
    public double getAutonomia(){
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

    public double getCapacidade(){
        return capacidade;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setCarga (Carga carga) {
        this.carga = carga;
    }
}