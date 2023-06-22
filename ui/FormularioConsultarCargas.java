package ui;

import javax.swing.*;

import dados.SistemaPorto;

import java.awt.*;

public class FormularioConsultarCargas extends JFrame {
    private JLabel mensagem;
    private SistemaPorto sc;
    public FormularioConsultarCargas(SistemaPorto repo) {
        sc = repo;
        setTitle("Consultar cargas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);

        mensagem = new JLabel();
        JButton botaoFechar = new JButton("Fechar");
        JPanel botaoPanel = new JPanel(new GridLayout(4, 1));
        botaoPanel.add(botaoFechar);
        botaoFechar.addActionListener(e -> dispose());
        mensagem.setText("<html> " + sc.consultarCargas().replace("Carga:Identificador", "<br />Carga: <br /> Id") + " </html>");
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(mensagem);
        panel.add(botaoPanel);
        this.add(panel);
    }

    public static void main(String[] args) {
        
    }
}
