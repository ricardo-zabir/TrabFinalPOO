package dados;

public class CargaDuravel extends TipoCarga{
    private String setor;
    private String material;
    private double porcentagemIPI;

    public CargaDuravel(int numero, String descricao, String setor, String material, double porcentagemIPI) {
        super(numero, descricao);
        this.setor = setor;
        this.material = material;
        this.porcentagemIPI = porcentagemIPI;
    }

    public String getSetor() {
        return setor;
    }

    public String getMaterial() {
        return material;
    }

    public double getPorcentagemIPI() {
        return porcentagemIPI;
    }
}
