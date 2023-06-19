package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 public class FormularioPrincipal extends JFrame {
    private JButton botaoCadastroCliente;
    private JButton botaoCadastroNavio;
    private JButton botaoCadastroPorto;
    private JButton botaoCadastroCarga;
    private JButton botaoCadastroTipoCarga;
    private JButton botaoConsultaCarga;
    private JButton botaoAlterarCarga;
    private JButton botaoCarregarDados;
    private JButton botaoFretarCarga;
    private JButton botaoFINAL;
    private JButton botaoSalvarDados;
    private JLabel mensagem;

    public FormularioPrincipal() {
        super();

        JLabel formTitle = new JLabel("ACMEHandelsschifffahrtsgesellschaft");
        formTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        botaoCadastroNavio = new JButton("Cadastrar Navio");
        botaoCadastroPorto = new JButton("Cadastrar Porto");
        botaoCadastroCliente = new JButton("Cadastrar Cliente");
        botaoCadastroCarga = new JButton("Cadastrar Carga");
        botaoCadastroTipoCarga = new JButton("Cadastrar Tipo de Carga");
        botaoConsultaCarga = new JButton("Consultar Carga");
        botaoAlterarCarga = new JButton("Alterar Situação de uma carga");
        botaoCarregarDados = new JButton("Carregar Dados");
        botaoFretarCarga = new JButton("Fretar Carga");
        botaoSalvarDados = new JButton("Salvar Dados");
        botaoFINAL = new JButton("Finalizar");
        mensagem = new JLabel();

        GridLayout grid = new GridLayout(6, 4);
        JPanel painel = new JPanel(grid);
        painel.add(formTitle);
        FlowLayout botaoLayout = new FlowLayout(FlowLayout.RIGHT);
        JPanel botaoPainel = new JPanel(botaoLayout);
        botaoPainel.add(botaoCadastroNavio);
        botaoPainel.add(botaoCadastroPorto);
        botaoPainel.add(botaoCadastroCliente);
        botaoPainel.add(botaoCadastroCarga);
        botaoPainel.add(botaoCadastroTipoCarga);
        botaoPainel.add(botaoConsultaCarga);
        botaoPainel.add(botaoAlterarCarga);
        botaoPainel.add(botaoCarregarDados);
        botaoPainel.add(botaoFretarCarga);
        botaoPainel.add(botaoSalvarDados);
        botaoPainel.add(botaoFINAL);
        painel.add(botaoPainel);
        painel.add(mensagem);

        this.setTitle("ACMEHandelsschifffahrtsgesellschaft");
        this.add(painel);
        this.setSize(1000,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        // Tratamento de eventos
        TratadorEventos tratadorEventos = new TratadorEventos();
        botaoFINAL.addActionListener(tratadorEventos);
    }
    
    class TratadorEventos implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == botaoCadastroPorto)
            {
                
            }
            else if(e.getSource() == botaoCadastroNavio)
            {
                new FormularioNavio();
            }
            else if(e.getSource() == botaoCadastroCliente)
            {
              
            }
            else if(e.getSource() == botaoCadastroCarga)
            {
              
            }
            else if(e.getSource() == botaoCadastroTipoCarga)
            {
              
            }
            else if(e.getSource() == botaoConsultaCarga)
            {
              
            }
            else if(e.getSource() == botaoAlterarCarga)
            {
              
            }
            else if(e.getSource() == botaoCarregarDados)
            {
              
            }
            else if(e.getSource() == botaoFretarCarga)
            {
              
            }
            else if(e.getSource() == botaoSalvarDados)
            {
              
            }
            else if(e.getSource() == botaoFINAL)
            {
              dispose();
            }
        }
    }
}
