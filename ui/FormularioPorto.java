package ui;

import dados.Porto;
import dados.SistemaPorto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FormularioPorto extends JFrame {

    private JTextField campoTextoID, campoTextoNome, campoTextoPais;
    private JButton botaoConfirmar, botaoApagar, botaoMostrarPorto, botaoSair;
    private JLabel mensagemInicial, mensagemID, mensagemNOME, mensagemPAIS;

    private SistemaPorto sistemaPorto;

    public FormularioPorto(SistemaPorto repo) {
        super();
        sistemaPorto = repo;
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
        botaoMostrarPorto = new JButton("Mostrar Portos");
        botaoSair = new JButton("Fechar");
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

        painel.add(botaoMostrarPorto);
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

                    // Escrever no arquivo "PORTO.CSV"
                    String linha = id + "," + nome + "," + pais;

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("PORTOS.CSV", true))) {
                        writer.write(linha);
                        writer.newLine();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo.");
                    }
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

        botaoMostrarPorto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Porto> portos = sistemaPorto.getPortos();
                if (portos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Não há portos cadastrados.");
                } else {
                    StringBuilder mensagem = new StringBuilder();
                    mensagem.append("Lista de Portos:\n");
                    for (Porto porto : portos) {
                        mensagem.append("ID: ").append(porto.getId()).append("\n");
                        mensagem.append("Nome: ").append(porto.getNome()).append("\n");
                        mensagem.append("País: ").append(porto.getPais()).append("\n");
                        mensagem.append("\n");
                    }
                    JOptionPane.showMessageDialog(null, mensagem.toString());
                }
            }
        });
    }
}