package ui;

import javax.swing.*;

import dados.Cliente;
import dados.SistemaPorto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class FormularioCarga extends JFrame{
    private JTextField identificadorField;
    private JTextField portoOrigemField;
    private JTextField portoDestinoField;
    private JTextField codigoClienteField;
    private JTextField pesoField;
    private JTextField valorDeclaradoField;
    private JTextField tempoMaximoField;
    private JTextField tipoCargaNumeroField;
    private JTextField prioridadeField;
    
    private JButton botaoFinal;
    private JButton botaoConfirma;
    private JButton botaoLimpa;
    private JLabel mensagem;

    private HashSet<Cliente> clientes;

    public FormularioCarga(){
        super();

        JLabel formTitle = new JLabel("Digite as informações da carga:");
        formTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));

        clientes = new HashSet<>();
        GridLayout gridCampos = new GridLayout(2, 2);
        JPanel painelCampos = new JPanel(gridCampos);
        JLabel identificadorLabel = new JLabel(" ");
        JLabel portoOrigemLabel = new JLabel(" ");
        JLabel portoDestinoLabel = new JLabel(" ");
        JLabel codigoClienteLabel = new JLabel(" ");
        JLabel pesoLabel = new JLabel(" ");
        JLabel valorDeclaradoLabel = new JLabel(" ");
        JLabel tempoMaximoLabel = new JLabel(" ");
        JLabel tipoCargaNumeroLabel = new JLabel(" ");
        JLabel prioridadeLabel = new JLabel(" ");
        identificadorField = new JTextField();
        portoOrigemField = new JTextField();
        portoDestinoField = new JTextField();
        codigoClienteField = new JTextField();
        pesoField = new JTextField();
        valorDeclaradoField = new JTextField();
        tempoMaximoField = new JTextField();
        tipoCargaNumeroField = new JTextField();
        prioridadeField = new JTextField();
        painelCampos.add(identificadorLabel);
        painelCampos.add(identificadorField);
        painelCampos.add(portoOrigemLabel);
        painelCampos.add(portoOrigemField);
        painelCampos.add(portoDestinoLabel);
        painelCampos.add(portoDestinoField);
        painelCampos.add(codigoClienteLabel);
        painelCampos.add(codigoClienteField);
        painelCampos.add(pesoLabel);
        painelCampos.add(pesoField);
        painelCampos.add(valorDeclaradoLabel);
        painelCampos.add(valorDeclaradoField);
        painelCampos.add(tempoMaximoLabel);
        painelCampos.add(tempoMaximoField);
        painelCampos.add(tipoCargaNumeroLabel);
        painelCampos.add(tipoCargaNumeroField);
        painelCampos.add(prioridadeLabel);
        painelCampos.add(prioridadeField);

        botaoConfirma = new JButton("Confirmar cadastro");
        botaoLimpa = new JButton("Limpar");
        botaoFinal = new JButton("Finalizar");
        mensagem = new JLabel();

        GridLayout grid = new GridLayout(4, 1);
        JPanel painel = new JPanel(grid);
        painel.add(formTitle);
        painel.add(painelCampos);
        FlowLayout botaoLayout = new FlowLayout(FlowLayout.RIGHT);
        JPanel botaoPainel = new JPanel(botaoLayout);
        botaoPainel.add(botaoConfirma);
        botaoPainel.add(botaoLimpa);
        botaoPainel.add(botaoFinal);
        painel.add(botaoPainel);
        painel.add(mensagem);

        this.setTitle("Cadastro de Carga");
        this.add(painel);
        this.setSize(900,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        TratadorEventos tratadorEventos = new TratadorEventos();
        botaoLimpa.addActionListener(tratadorEventos);
        botaoFinal.addActionListener(tratadorEventos);
        botaoConfirma.addActionListener(tratadorEventos);
    }

    class TratadorEventos implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == botaoLimpa)
            {
                identificadorField.setText("");
                portoOrigemField.setText("");
                portoDestinoField.setText("");
                codigoClienteField.setText("");
                pesoField.setText("");
                valorDeclaradoField.setText("");
                tempoMaximoField.setText("");
                tipoCargaNumeroField.setText("");
                prioridadeField.setText("");
            }
            else if(e.getSource() == botaoFinal)
            {
                dispose();
            }
            else if(e.getSource() == botaoConfirma)
            {
                try {
                    int identificador = Integer.parseInt(identificadorField.getText());
                    int portoOrigem = Integer.parseInt(portoOrigemField.getText());
                    int portoDestino = Integer.parseInt(portoDestinoField.getText());
                    int peso = Integer.parseInt(pesoField.getText());
                    double valorDeclarado = Double.parseDouble(valorDeclaradoField.getText());
                    int tempoMaximo = Integer.parseInt(tempoMaximoField.getText());
                    String prioridade = prioridadeField.getText();
                    int codigo = Integer.parseInt(codigoClienteField.getText());
                    Cliente cliente = checkCodigo(codigo, clientes);


                    if (cliente != null) {
                        SistemaPorto.cadastrarCarga(identificador, portoOrigem, portoDestino, codigo, peso, valorDeclarado, tempoMaximo, situacao, tipoCarga, prioridade);
                        mensagem.setForeground(Color.GREEN);
                        mensagem.setText("Carga cadastrada com sucesso");
                    } else {
                        mensagem.setForeground(Color.RED);
                        mensagem.setText("Erro, cliente não encontrado");
                    }
                } catch (NumberFormatException erro) {
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("Erro, verifique os campos numéricos");
                } catch (Exception erro) { 
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("Erro, carga não foi cadastrada");
                }
            }
        }
    }

        public static Cliente checkCodigo(int codigo, HashSet<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            if (cliente.getCodigo() == codigo) {
                return cliente;
            }
        }
        return null; // Retorna null se o cliente não for encontrado
    }
}
