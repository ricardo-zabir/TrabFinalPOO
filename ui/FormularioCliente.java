package ui;
import javax.swing.*;

import dados.Cliente;
import dados.SistemaPorto;
import ui.FormularioCliente;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class FormularioCliente extends JFrame {

    // Componentes principais
    private JTextField codigoField;
    private JTextField emailField;
    private JTextField nomeField;
    private JButton botao;
    private JButton limparBotao;
    private JButton fecharBotao;
    private SistemaPorto sp;

    private JLabel mensagem;

    public FormularioCliente(SistemaPorto repo) {
        super();
        sp = repo;
        ArrayList<Cliente> clientes = sp.getClientes();
        JLabel formTitle = new JLabel("Digite os dados do cliente");
        formTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));

        GridLayout gridCampos = new GridLayout(3, 2);
        JPanel painelCampos = new JPanel(gridCampos);
        JLabel codigoLabel = new JLabel("Código: ");
        JLabel emailLabel = new JLabel("E-mail: ");
        JLabel nomeLabel = new JLabel("Nome: ");
        codigoField = new JTextField();
        emailField = new JTextField();
        nomeField = new JTextField();
        painelCampos.add(codigoLabel);
        painelCampos.add(codigoField);
        painelCampos.add(emailLabel);
        painelCampos.add(emailField);
        painelCampos.add(nomeLabel);
        painelCampos.add(nomeField);

        botao = new JButton("Adicionar");
        limparBotao = new JButton("Limpar");
        fecharBotao = new JButton("Fechar");
        mensagem = new JLabel();

        // Tratamento de evento do botao
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer.parseInt(codigoField.getText());
                    if (codigoField.getText().equals("") || emailField.getText().equals("") || nomeField.getText().equals("")) {
                        throw new Exception("Campos não podem estar vazios");
                    }
                    String exists = checkClient(Integer.parseInt(codigoField.getText()), emailField.getText(), clientes);
                    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                    Pattern pattern = Pattern.compile(emailRegex);
                    if (!pattern.matcher(emailField.getText()).matches()) {
                        throw new IllegalArgumentException();
                    }
                    if (exists.equals("")) {
                        sp.cadastrarCliente(Integer.parseInt(codigoField.getText()), nomeField.getText(), emailField.getText());
                        String nome = nomeField.getText();
                        nomeField.setText("");
                        codigoField.setText("");
                        emailField.setText("");
                        mensagem.setForeground(Color.GREEN);
                        mensagem.setText("Cliente " + nome + " adicionado!");

                        // Escrever no arquivo "CLIENTES.CSV"
                        String linha = codigoField.getText() + "," + nome + "," + emailField.getText();

                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CLIENTES.CSV", true))) {
                            writer.write(linha);
                            writer.newLine();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo.");
                        }

                    } else {
                        mensagem.setForeground(Color.RED);
                        mensagem.setText(exists + " já existe");
                    }
                } catch (NumberFormatException nfe) {
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("Código deve ser numérico");
                } catch (IllegalArgumentException iae) {
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("E-mail inválido");
                } catch (Exception ex) {
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("Nenhum campo pode estar vazio");
                }
            }
        });

        limparBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nomeField.setText("");
                codigoField.setText("");
                emailField.setText("");
            }
        });
        fecharBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        GridLayout grid = new GridLayout(4, 1);
        JPanel painel = new JPanel(grid);
        painel.add(formTitle);
        painel.add(painelCampos);
        FlowLayout botaoLayout = new FlowLayout(FlowLayout.RIGHT);
        JPanel botaoPainel = new JPanel(botaoLayout);
        botaoPainel.add(botao);
        botaoPainel.add(limparBotao);
        botaoPainel.add(fecharBotao);
        painel.add(botaoPainel);
        painel.add(mensagem);
        this.setTitle("Formulario - Cliente");
        this.add(painel);
        this.setSize(700,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public String checkClient(int codigo, String email, ArrayList<Cliente> clientes) {
        Iterator<Cliente> it = clientes.iterator();
        while (it.hasNext()) {
            Cliente cliente = it.next();
            if (cliente.getCodigo() == codigo || cliente.getEmail().equals(email)) {
                if(cliente.getCodigo() == codigo) {
                    return "código";
                }
                return "email";
            }
        }
        return "";
    }
    public static void main(String[] args) {
        // FormularioCliente form = new FormularioCliente();
    }

}