package dados;

public class CargaPerecivel extends TipoCarga{
    private String origem;
    private int tempoValidade;

    public CargaPerecivel(int numero, String descricao, String origem, int tempoValidade) {
        super(numero, descricao);
        this.origem = origem;
        this.tempoValidade = tempoValidade;
    }

    public String getOrigem() {
        return origem;
    }

    public int getTempoValidade() {
        return tempoValidade;
    }
}
