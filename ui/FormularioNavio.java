package ui;

import dados.Navio;
import dados.SistemaPorto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

 public class FormularioNavio extends JFrame {
    protected ArrayList<Navio> navios;
    private JTextField nomeField;
    private JTextField velocidadeField;
    private JTextField autonomiaField;
    private JTextField custoMilhaField;
    private JButton botaoCONFIRMA;
    private JButton botaoLIMPA;
    private JButton botaoFINAL;
    private JLabel mensagem;
    private SistemaPorto sp;

    public FormularioNavio(SistemaPorto repo) {
        super();
        sp = repo;
        navios = sp.getNavios();
        JLabel formTitle = new JLabel("Digite as informações do navio:");
        formTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));

        GridLayout gridCampos = new GridLayout(3, 3);
        JPanel painelCampos = new JPanel(gridCampos);
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel velocidadeLabel = new JLabel("Velocidade(em nós):");
        JLabel autonomiaLabel = new JLabel("Autonomia(milhas náuticas):");
        JLabel custoMilhaLabel = new JLabel("Custo por Milha:");
        nomeField = new JTextField();
        velocidadeField = new JTextField();
        autonomiaField = new JTextField();
        custoMilhaField = new JTextField();
        painelCampos.add(nomeLabel);
        painelCampos.add(nomeField);
        painelCampos.add(velocidadeLabel);
        painelCampos.add(velocidadeField);
        painelCampos.add(autonomiaLabel);
        painelCampos.add(autonomiaField);
        painelCampos.add(custoMilhaLabel);
        painelCampos.add(custoMilhaField);


        botaoCONFIRMA = new JButton("Confirmar cadastro");
        botaoLIMPA = new JButton("Limpar");
        botaoFINAL = new JButton("Fechar");
        mensagem = new JLabel();

        GridLayout grid = new GridLayout(4, 8);
        JPanel painel = new JPanel(grid);
        painel.add(formTitle);
        painel.add(painelCampos);
        FlowLayout botaoLayout = new FlowLayout(FlowLayout.RIGHT);
        JPanel botaoPainel = new JPanel(botaoLayout);
        botaoPainel.add(botaoCONFIRMA);
        botaoPainel.add(botaoLIMPA);
        botaoPainel.add(botaoFINAL);
        painel.add(botaoPainel);
        painel.add(mensagem);

        this.setTitle("Cadastro de Navio");
        this.add(painel);
        this.setSize(700,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        // Tratamento de eventos
        TratadorEventos tratadorEventos = new TratadorEventos();
        botaoLIMPA.addActionListener(tratadorEventos);
        botaoFINAL.addActionListener(tratadorEventos);
        botaoCONFIRMA.addActionListener(tratadorEventos);
    }
    
    class TratadorEventos implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == botaoLIMPA)
            {
                nomeField.setText("");
                velocidadeField.setText("");
                autonomiaField.setText("");
                custoMilhaField.setText("");
            }
            else if(e.getSource() == botaoFINAL)
            {
                dispose();
            }
            else if(e.getSource() == botaoCONFIRMA)
            {
                String nome = nomeField.getText();
                try {
                    double velocidade = Double.parseDouble(velocidadeField.getText());
                    double autonomia = Double.parseDouble(autonomiaField.getText());
                    double custoPorMilhaBasico = Double.parseDouble(custoMilhaField.getText());
                    int capacidade = 0;
                    String status = null;
                    Navio navio = new Navio(nome, velocidade, autonomia, custoPorMilhaBasico, capacidade, status);
                    if(CadastraNavio(navio)){
                        mensagem.setForeground(Color.BLUE);
                        mensagem.setText("Navio " + nome + " cadastrado com sucesso");
                    }
                    else{
                        mensagem.setForeground(Color.RED);
                        mensagem.setText("Erro, navio " + nome + " não foi cadastrado, pois já exite um navio com este nome.");
                    }
                } catch (NumberFormatException er) { 
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("Erro, navio " + nome + "não foi cadastrado");
                }
            }
        }
    }

    public boolean CadastraNavio(Navio navio){
        for(int i=0; i<navios.size(); i++){
            Navio aux = navios.get(i);
            if(aux.getNome().equals(navio.getNome())){return false;}
        }
        navios.add(navio);
        return true;
    }

    public static void main(String[] args) {
        // FormularioNavio formNavio = new FormularioNavio();
        }
}