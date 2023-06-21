import dados.SistemaPorto;
import ui.MenuInicial;

public class Main {
    public static void main(String[] args) {
        SistemaPorto repo = new SistemaPorto();
        new MenuInicial(repo);
    }
}