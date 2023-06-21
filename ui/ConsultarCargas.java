package ui;

import javax.swing.*;

import dados.SistemaPorto;

import java.awt.*;

public class ConsultarCargas extends JFrame {
    private JTextArea textArea;
    private SistemaPorto sc;
    public ConsultarCargas(SistemaPorto repo) {
        sc = repo;
        setTitle("Consultar cargas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(e -> dispose());
        textArea.setText(repo.consultarCargas());
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        add(panel);
    }

    public static void main(String[] args) {
        
    }
}
