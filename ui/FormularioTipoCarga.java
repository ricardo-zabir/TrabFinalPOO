package ui;
import javax.swing.*;

import dados.TipoCarga;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;

public class FormularioTipoCarga extends JFrame {

    // Componentes principais
    private JTextField numeroField;
    private JTextField descricaoField;
    private JButton botao;
    private JButton limparBotao;
    private JButton fecharBotao;

    private JLabel mensagem;

    public FormularioTipoCarga() {
        super();
        ArrayList<TipoCarga> tiposCargas = new ArrayList<TipoCarga>();
        JLabel formTitle = new JLabel("Digite os dados do tipo de carga");
        formTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JRadioButton perecivelRadioButton = new JRadioButton("Perecivel");
        JRadioButton duravelRadioButton = new JRadioButton("Duravel");

        // Create a button group to ensure only one radio button can be selected
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(perecivelRadioButton);
        buttonGroup.add(duravelRadioButton);


        GridLayout gridCampos = new GridLayout(3, 2);
        JPanel painelCampos = new JPanel(gridCampos);
        JLabel numeroLabel = new JLabel("Número: ");
        JLabel descricaoLabel = new JLabel("Descrição: ");
        numeroField = new JTextField();
        descricaoField = new JTextField();
        painelCampos.add(perecivelRadioButton);
        painelCampos.add(duravelRadioButton);
        painelCampos.add(numeroLabel);
        painelCampos.add(numeroField);
        painelCampos.add(descricaoLabel);
        painelCampos.add(descricaoField);

        botao = new JButton("Adicionar");
        limparBotao = new JButton("Limpar");
        fecharBotao = new JButton("Fechar");
        mensagem = new JLabel();

        // Tratamento de evento do botao
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                }
                catch (NumberFormatException nfe) {
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("Código deve ser numérico");
                }
                catch(IllegalArgumentException iae) {
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("E-mail inválido");
                }
                catch(Exception ex) {
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("Nenhum campo pode estar vazio");
                }
            }
        });
        limparBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                descricaoField.setText("");
                numeroField.setText("");
            }
        });
        fecharBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        perecivelRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JLabel origemLabel = new JLabel("Origem: ");
                JLabel tempoValidadeLabel = new JLabel("Tempo validade: ");
                JTextField origemField = new JTextField();
                JTextField tempoValidadeField = new JTextField();
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    painelCampos.removeAll();
                    painelCampos.add(perecivelRadioButton);
                    painelCampos.add(duravelRadioButton);
                    painelCampos.add(numeroLabel);
                    painelCampos.add(numeroField);
                    painelCampos.add(descricaoLabel);
                    painelCampos.add(descricaoField);
                    painelCampos.add(origemLabel);
                    painelCampos.add(origemField);
                    painelCampos.add(tempoValidadeLabel);
                    painelCampos.add(tempoValidadeField);
                    painelCampos.revalidate();
                }
            }
        });

        duravelRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JLabel setorLabel = new JLabel("Setor: ");
                JLabel materialLabel = new JLabel("Material: ");
                JLabel porcentagemIPILabel = new JLabel("Porcentagem IPI: ");
                JTextField setorField = new JTextField();
                JTextField materialField = new JTextField();
                JTextField porcentagemIPIField = new JTextField();
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    painelCampos.removeAll();
                    painelCampos.add(perecivelRadioButton);
                    painelCampos.add(duravelRadioButton);
                    painelCampos.add(numeroLabel);
                    painelCampos.add(numeroField);
                    painelCampos.add(descricaoLabel);
                    painelCampos.add(descricaoField);
                    painelCampos.add(setorLabel);
                    painelCampos.add(setorField);
                    painelCampos.add(materialLabel);
                    painelCampos.add(materialField);
                    painelCampos.add(porcentagemIPILabel);
                    painelCampos.add(porcentagemIPIField);
                    painelCampos.revalidate();
                }
            }
        });

        GridLayout grid = new GridLayout(4, 1);
        JPanel painel = new JPanel(grid);
        painel.add(formTitle);
        painel.add(painelCampos);
        FlowLayout botaoLayout = new FlowLayout(FlowLayout.RIGHT);
        FlowLayout botaoLimparLayout = new FlowLayout(FlowLayout.LEFT);
        FlowLayout botaoFecharLayout = new FlowLayout(FlowLayout.CENTER);
        JPanel botaoPainel = new JPanel(botaoLayout);
        JPanel botaoLimparPainel = new JPanel(botaoLimparLayout);
        JPanel botaoFecharPainel = new JPanel(botaoFecharLayout);
        botaoPainel.add(botao);
        botaoFecharPainel.add(fecharBotao);
        botaoLimparPainel.add(limparBotao);
        painel.add(botaoPainel);
        painel.add(botaoLimparPainel);
        painel.add(botaoFecharPainel);
        painel.add(mensagem);
        this.setTitle("Formulario - Tipo de Carga");
        this.add(painel);
        this.setSize(900,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public String checkClient(int numero, ArrayList<TipoCarga> clientes) {
        Iterator<TipoCarga> it = clientes.iterator();
        while (it.hasNext()) {
            TipoCarga tipoCarga = it.next();
            if (tipoCarga.getNumero() == numero) {
                return "numero";
            }
        }
        return "";
    }
    public static void main(String[] args) {
        FormularioTipoCarga form = new FormularioTipoCarga();
    }

}