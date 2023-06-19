package ui;

import dados.SistemaPorto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioPorto extends JFrame {

    private JTextField campoTextoID, campoTextoNome, campoTextoPais;
    private JButton botaoConfirmar, botaoApagar, botaoMostrarPortos, botaoSair;
    private JLabel mensagemInicial, mensagemID, mensagemNOME, mensagemPAIS;

    private SistemaPorto sistemaPorto;

    public FormularioPorto() {
        super();
        sistemaPorto = new SistemaPorto();
        mensagemInicial = new JLabel("Digite as informações do porto ");
        mensagemInicial.setFont(mensagemInicial.getFont().deriveFont(45.0f));
        mensagemID = new JLabel("Digite o ID do porto");
        mensagemNOME = new JLabel("Digite o nome do porto");
        mensagemPAIS = new JLabel("Digite o país do porto");
        campoTextoID = new JTextField(20);
        campoTextoNome = new JTextField(21);
        campoTextoPais = new JTextField(20);
        botaoConfirmar = new JButton("Confirmar");
        botaoApagar = new JButton("Apagar");
        botaoMostrarPortos = new JButton("Mostrar Portos");
        botaoSair = new JButton("Sair");
        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout());

        painel.add(mensagemInicial);

        painel.add(mensagemID);
        painel.add(campoTextoID);

        painel.add(mensagemNOME);
        painel.add(campoTextoNome);

        painel.add(mensagemPAIS);
        painel.add(campoTextoPais);

        painel.add(botaoConfirmar);
        painel.add(botaoApagar);

        painel.add(botaoMostrarPortos);
        painel.add(botaoSair);

        this.add(painel);
        this.setSize(800, 400);
        this.setTitle("Sistema Handelsschifffahrtsgesellschaft");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String idTexto = campoTextoID.getText();
            String nome = campoTextoNome.getText();
            String pais = campoTextoPais.getText();

            if (idTexto.isEmpty() || nome.isEmpty() || pais.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Erro: Preencha todos os campos.");
                return;
            }

            try {
                int id = Integer.parseInt(idTexto);

                if (sistemaPorto.verificarIdExistente(id)) {
                    JOptionPane.showMessageDialog(null, "Erro: ID já cadastrado.");
                } else {
                    sistemaPorto.cadastrarPorto(id, nome, pais);
                    JOptionPane.showMessageDialog(null, "Porto cadastrado com sucesso!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erro: O ID deve ser um número inteiro válido.");
            }
        }
    });

        botaoApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoTextoID.setText("");
                campoTextoNome.setText("");
                campoTextoPais.setText("");
            }
        });

        botaoMostrarPortos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistemaPorto.mostrarPortos();
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a janela principal
                Window window = SwingUtilities.getWindowAncestor(botaoSair);
                window.dispose();
            }
        });
    }
}