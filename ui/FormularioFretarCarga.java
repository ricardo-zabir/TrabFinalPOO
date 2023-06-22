package ui;

import dados.SistemaPorto;
import dados.Carga;
import dados.Navio;
import dados.Porto;
import dados.Distancia;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

 public class FormularioFretarCarga extends JFrame {
    private JLabel mensagem;
    private SistemaPorto sp;

    public FormularioFretarCarga(SistemaPorto repo) {
        super();
        sp = repo;
        JLabel formTitle = new JLabel("Fretar Cargas");
        formTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));

        mensagem = new JLabel();

        GridLayout grid = new GridLayout(4, 8);
        JPanel painel = new JPanel(grid);
        painel.add(formTitle);
        painel.add(mensagem);

        this.setTitle("Cadastro de Navio");
        this.add(painel);
        this.setSize(700,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }

    public void fretarCargas() {
        ArrayList<Carga> cargas = sp.getCargas();
        ArrayList<Navio> navios = sp.getNavios();
        ArrayList<Porto> portos = sp.getPortos();
        ArrayList<Distancia> distancias = sp.getDistancias();
        String mensagemAcerto = "Fretada com sucesso";
        if (cargas.isEmpty()) {
            mensagem.setForeground(Color.RED);
                    mensagem.setText("Erro, não existe carga na fila de espera");
            return;
        }

        for (Carga carga : cargas) {
            if (carga.getSituacao().equals("PENDENTE")) {
                boolean cargaFretada = false;

                for (Navio navio : navios) {
                    if (carga.getPeso() <= navio.getCapacidade() && navio.getStatus().equals("LIBERADO")) {
                        navio.setStatus("OCUPADO");
                        carga.setSituacao("LOCADO");
                        carga.setFrete(navio, portos, distancias);
                        cargaFretada = true;
                        mensagem.setForeground(Color.BLUE);
                        mensagem.setText(carga.getNome() +   mensagemAcerto);
                        break;
                    }
                }

                if (!cargaFretada) {
                    carga.setSituacao("CANCELADO");
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        //FretarCargaUI fretarUI = new FretarCargaUI();
        }
}
