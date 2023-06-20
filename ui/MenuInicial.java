package ui;

import javax.swing.*;
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
    private JButton botaoCarregarDados;
    private JButton botaoFretarCarga;
    private JButton botaoFINAL;
    private JButton botaoSalvarDados;
    private JButton botaoCarregarDadosInicias;

    public MenuInicial() {
        super();

        JLabel formTitle = new JLabel("Menu Inicial");
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
        botaoCarregarDadosInicias = new JButton("Carregar Dados Iniciais");
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
        botaoPainel.add(botaoCarregarDados);
        botaoPainel.add(botaoFretarCarga);
        botaoPainel.add(botaoSalvarDados);
        botaoPainel.add(botaoCarregarDadosInicias);
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
        botaoSalvarDados.addActionListener(tratadorEventos);
        botaoCarregarDadosInicias.addActionListener(tratadorEventos);
        botaoFINAL.addActionListener(tratadorEventos);
    }
    
    class TratadorEventos implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == botaoCadastroPorto)
            {
                FormularioPorto formularioPorto = new FormularioPorto();
                formularioPorto.setVisible(true);
            }
            else if(e.getSource() == botaoCadastroNavio)
            {
                FormularioNavio formularioNavio = new FormularioNavio();
                formularioNavio.setVisible(true);
            }
            else if(e.getSource() == botaoCadastroCliente)
            {
                FormularioCliente formularioCliente = new FormularioCliente();
                formularioCliente.setVisible(true);
            }
            else if(e.getSource() == botaoCadastroCarga)
            {
                FormularioCarga formularioCarga = new FormularioCarga();
                formularioCarga.setVisible(true);
            }
            else if(e.getSource() == botaoCadastroTipoCarga)
            {
              FormularioTipoCarga formularioTipoCarga = new FormularioTipoCarga();
              formularioTipoCarga.setVisible(true);
            }
            else if(e.getSource() == botaoConsultaCarga)
            {
              
            }
            else if(e.getSource() == botaoAlterarCarga)
            {
              AlterarCarga alterarCarga = new AlterarCarga();
              alterarCarga.setVisible(true);
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
            else if(e.getSource() == botaoCarregarDadosInicias)
            {
                CarregarDadosIniciais carregarDadosIniciais = new CarregarDadosIniciais();
                carregarDadosIniciais.setVisible(true);
            }
            else if(e.getSource() == botaoFINAL)
            {
              dispose();
            }
        }
    }

    public static void main(String[] args) {
        MenuInicial janela = new MenuInicial();
        }
    
}