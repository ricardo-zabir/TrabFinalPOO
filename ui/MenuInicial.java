package ui;

import javax.swing.*;

import dados.SistemaPorto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 public class MenuInicial extends JFrame {
    private JButton botaoCadastroCliente;
    private JButton botaoCadastroNavio;
    private JButton botaoCadastroPorto;
    private JButton botaoCadastroCarga;
    private JButton botaoCadastroTipoCarga;
    private JButton botaoConsultaCarga;
    private JButton botaoAlterarCarga;
    private JButton botaoEntradaSaidaDeDados;
    private JButton botaoFretarCarga;
    private JButton botaoFINAL;
    private SistemaPorto sp;

    public MenuInicial(SistemaPorto repo) {
        super();
        sp = repo;
        JLabel formTitle = new JLabel("Menu Inicial");
        formTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        botaoCadastroNavio = new JButton("Cadastrar Navio");
        botaoCadastroPorto = new JButton("Cadastrar Porto");
        botaoCadastroCliente = new JButton("Cadastrar Cliente");
        botaoCadastroCarga = new JButton("Cadastrar Carga");
        botaoCadastroTipoCarga = new JButton("Cadastrar Tipo de Carga");
        botaoConsultaCarga = new JButton("Consultar Carga");
        botaoAlterarCarga = new JButton("Alterar Situação de uma carga");
        botaoEntradaSaidaDeDados = new JButton("Entrada e Saída de Dados");
        botaoFretarCarga = new JButton("Fretar Carga");
        botaoFINAL = new JButton("Finalizar");

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
        botaoPainel.add(botaoEntradaSaidaDeDados);
        botaoPainel.add(botaoFretarCarga);
        botaoPainel.add(botaoFINAL);
        painel.add(botaoPainel);

        this.setTitle("ACMEHandelsschifffahrtsgesellschaft");
        this.add(painel);
        this.setSize(1000,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        // Tratamento de eventos
        TratadorEventos tratadorEventos = new TratadorEventos();
        botaoCadastroPorto.addActionListener(tratadorEventos);
        botaoCadastroNavio.addActionListener(tratadorEventos);
        botaoCadastroCliente.addActionListener(tratadorEventos);
        botaoCadastroCarga.addActionListener(tratadorEventos);
        botaoCadastroTipoCarga.addActionListener(tratadorEventos);
        botaoConsultaCarga.addActionListener(tratadorEventos);
        botaoAlterarCarga.addActionListener(tratadorEventos);
        botaoFretarCarga.addActionListener(tratadorEventos);
        botaoEntradaSaidaDeDados.addActionListener(tratadorEventos);
        botaoFINAL.addActionListener(tratadorEventos);
    }
    
    class TratadorEventos implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == botaoCadastroPorto)
            {
                FormularioPorto formularioPorto = new FormularioPorto(sp);
                formularioPorto.setVisible(true);
            }
            else if(e.getSource() == botaoCadastroNavio)
            {
                FormularioNavio formularioNavio = new FormularioNavio(sp);
                formularioNavio.setVisible(true);
            }
            else if(e.getSource() == botaoCadastroCliente)
            {
                FormularioCliente formularioCliente = new FormularioCliente(sp);
                formularioCliente.setVisible(true);
            }
            else if(e.getSource() == botaoCadastroCarga)
            {
                FormularioCarga formularioCarga = new FormularioCarga(sp);
                formularioCarga.setVisible(true);
            }
            else if(e.getSource() == botaoCadastroTipoCarga)
            {
                FormularioTipoCarga formularioTipoCarga = new FormularioTipoCarga(sp);
                formularioTipoCarga.setVisible(true);
            }
            else if (e.getSource() == botaoConsultaCarga) {
                
            }
            else if(e.getSource() == botaoAlterarCarga)
            {
              FormularioAlterarCarga alterarCarga = new FormularioAlterarCarga(sp);
              alterarCarga.setVisible(true);
            }
            else if(e.getSource() == botaoFretarCarga)
            {
              FormularioFretarCarga fretarCarga = new FormularioFretarCarga(sp);
              fretarCarga.setVisible(true);
            }
            else if(e.getSource() == botaoEntradaSaidaDeDados)
            {
                InterfaceDados dados = new InterfaceDados(sp);
                dados.setVisible(true);
            }
            else if(e.getSource() == botaoFINAL)
            {
              dispose();
            }
        }
    }

    public static void main(String[] args) {
        // MenuInicial janela = new MenuInicial();
    }
    
}