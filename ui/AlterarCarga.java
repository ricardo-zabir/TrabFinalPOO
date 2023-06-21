package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlterarCarga extends JFrame{
    private JTextField codigoSolicitadoField;
    private JButton botaoFinal;
    private JButton botaoConfirma;
    private JLabel mensagem;

    public AlterarCarga() {
        super();

        GridLayout gridCampos = new GridLayout(2, 2);
        JPanel painelCampos = new JPanel(gridCampos);
        JLabel codigoSolicitadoLabel = new JLabel("Digite o código da carga:");
        codigoSolicitadoField = new JTextField();

        painelCampos.add(codigoSolicitadoLabel);
        painelCampos.add(codigoSolicitadoField);

        botaoFinal = new JButton("Fechar ");
        botaoConfirma = new JButton("Confirmar");

        GridLayout grid = new GridLayout(4, 1);
        JPanel painel = new JPanel(grid);
        painel.add(painelCampos);
        FlowLayout botaoLayout = new FlowLayout(FlowLayout.RIGHT);
        JPanel botaoPainel = new JPanel(botaoLayout);
        botaoPainel.add(botaoFinal);
        botaoPainel.add(botaoConfirma);
        painel.add(botaoPainel);

        this.setTitle("Alteração de Carga");
        this.setSize(600,300);
        this.add(painel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


        TratadorEventos tratadorEventos = new TratadorEventos();
        botaoFinal.addActionListener(tratadorEventos);
    }

    class TratadorEventos implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == botaoFinal)
            {
                dispose();
            }  
        }
    }
}
