package ui;

import javax.swing.*;

import dados.Carga;
import dados.SistemaPorto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AlterarCarga extends JFrame{
    private JTextField codigoSolicitadoField;
    private JTextField novaSituacaoField;
    private JButton botaoFinal;
    private JButton botaoConfirma;
    private JLabel mensagem;
    private JLabel mensagem2;
    private SistemaPorto sp;

    public AlterarCarga(SistemaPorto repo) {
        super();
        sp = repo;
        GridLayout gridCampos = new GridLayout(2, 2);
        JPanel painelCampos = new JPanel(gridCampos);
        JLabel codigoSolicitadoLabel = new JLabel("Digite o código da carga:");
        JLabel novaSituacaoJLabel = new JLabel("Nova situação: ");
        novaSituacaoField = new JTextField();
        codigoSolicitadoField = new JTextField();
        mensagem = new JLabel();
        mensagem2 = new JLabel();

        painelCampos.add(codigoSolicitadoLabel);
        painelCampos.add(codigoSolicitadoField);
        painelCampos.add(novaSituacaoJLabel);
        painelCampos.add(novaSituacaoField);

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
        painel.add(mensagem);
        painel.add(mensagem2);

        this.setTitle("Alteração de Carga");
        this.setSize(600,500);
        this.add(painel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


        TratadorEventos tratadorEventos = new TratadorEventos();
        botaoFinal.addActionListener(tratadorEventos);
        botaoConfirma.addActionListener(tratadorEventos);
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
            else if(e.getSource() == botaoConfirma){
                int codigoCarga = Integer.parseInt(codigoSolicitadoField.getText());
                String novaSituacao = novaSituacaoField.getText();
                ArrayList<Carga> cargas = sp.getCargas();
                Carga carga = null;
                for (Carga c : cargas) {
                    if (c.getIdentificador() == codigoCarga) {
                        carga = c;
                        break;
                    }
                }
                // Verifica se a carga foi encontrada
                if (carga == null) {
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("Erro, não existe carga com esse código");
                    return;
                }
                else if (carga.getSituacao().equals("FINALIZADO")) {
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("Erro: A carga já está na situação FINALIZADO e não pode ser alterada.");
                    return;
                }
                else{
        
                // Atualiza a situação da carga
                carga.setSituacao(novaSituacao);
                mensagem.setForeground(Color.BLUE);
                mensagem.setText("Situação alterada com sucesso. Informações atualizadas da carga:");
                mensagem2.setForeground(Color.BLUE);
                mensagem2.setText("Situação da carga: " + carga.getIdentificador() + " alterada com sucesso; " + " Cliente: " + carga.getCodigo().getCodigo() + ";" +
                " Origem: " + carga.getPortoOrigem() + ";" +
                " Destino: " + carga.getPortoDestino() + ";" +
                " Peso: " + carga.getPeso() + ";" +
                " Valor Declarado: " + carga.getValorDeclarado() + ";" +
                " Tempo Máximo: " + carga.getTempoMaximo() + ";" +
                " Tipo de Carga: " + carga.getNumero().getDescricao() + ";" +
                " Prioridade: " + carga.getPrioridade() + ";" +
                " Nova Situação: " + carga.getSituacao());
                return;
                }
            }  
        }
    }
    
    public static void main(String[] args) {
        // AlterarCarga formAlterarCarga = new AlterarCarga();
    }
}
