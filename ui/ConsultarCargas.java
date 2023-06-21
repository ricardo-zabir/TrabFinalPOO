package ui;

import javax.swing.*;

import dados.SistemaPorto;

import java.awt.*;

public class ConsultarCargas extends JFrame {
    private JLabel mensagem;
    private SistemaPorto sc;
    public ConsultarCargas(SistemaPorto repo) {
        sc = repo;
        setTitle("Consultar cargas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);

        mensagem = new JLabel();
        JButton botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(e -> dispose());
        mensagem.setText(sc.consultarCargas());
        JPanel panel = new JPanel(new GridLayout(3,2));
        panel.add(mensagem);
        panel.add(botaoFechar);
        this.add(panel);
    }

    public static void main(String[] args) {
        
    }
}
