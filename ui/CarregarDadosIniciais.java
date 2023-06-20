package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dados.Carga;
import dados.CargaDuravel;
import dados.CargaPerecivel;
import dados.Cliente;
import dados.Navio;
import dados.Porto;
import dados.SistemaPorto;
import dados.TipoCarga;

public class CarregarDadosIniciais extends JFrame {
    private JTextField nomeArquivoField;
    private JButton enviaButton;
    private JLabel mensagem;

    public CarregarDadosIniciais() {
        super();
        JLabel formTitle = new JLabel("Digite as informações da carga:");
        formTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        GridLayout gridCampos = new GridLayout(3, 1);
        enviaButton = new JButton("Enviar");
        JPanel painelCampos = new JPanel(gridCampos);
        JLabel nomeArquivoLabel = new JLabel("Insira o nome do arquivo");
        nomeArquivoField = new JTextField();
        painelCampos.add(nomeArquivoLabel);
        painelCampos.add(nomeArquivoField);
        painelCampos.add(enviaButton);
        mensagem = new JLabel();
        JPanel painel = new JPanel(new GridLayout(2, 2));
        painel.add(painelCampos);
        this.setTitle("Carregar dados iniciais");
        this.add(painel);
        this.setSize(900,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        enviaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!nomeArquivoField.getText().equals("")) {
                    ArrayList<String> arquivos = new ArrayList<>();
                    SistemaPorto repo = new SistemaPorto();
                    arquivos.add("-PORTOS.CSV");
                    arquivos.add("-DISTANCIAS.CSV");
                    arquivos.add("-NAVIOS.CSV");
                    arquivos.add("-CLIENTES.CSV");
                    arquivos.add("-TIPOSCARGAS.CSV");
                    arquivos.add("-CARGAS.CSV");
                    try {
                        for (String arquivo : arquivos) {
                            switch (arquivo) {
                                case "-PORTOS.CSV":
                                    String portosRetorno = lerPortos(new Scanner(Files.newBufferedReader(Paths.get(nomeArquivoField.getText() + "" + arquivo), Charset.defaultCharset())), repo);
                                    if(!portosRetorno.equals("")) {
                                        throw new DataFormatException(portosRetorno);
                                    }
                                    break;
                                case "-DISTANCIAS.CSV":
                                    break;
                                    
                                case "-NAVIOS.CSV":
                                    String naviosRetorno = lerNavios(new Scanner(Files.newBufferedReader(Paths.get(nomeArquivoField.getText() + "" + arquivo), Charset.defaultCharset())), repo);
                                    if(!naviosRetorno.equals("")) {
                                        throw new DataFormatException(naviosRetorno);
                                    }
                                    break;
                                    
                                case "-CLIENTES.CSV":
                                    String clientesRetorno = lerClientes(new Scanner(Files.newBufferedReader(Paths.get(nomeArquivoField.getText() + "" + arquivo), Charset.defaultCharset())), repo);
                                    if(!clientesRetorno.equals("")) {
                                        throw new DataFormatException(clientesRetorno);
                                    }
                                    break;
                                case "-TIPOSCARGAS.CSV":
                                    String tiposCargasRetorno = lerTipoDeCarga(new Scanner(Files.newBufferedReader(Paths.get(nomeArquivoField.getText() + "" + arquivo), Charset.defaultCharset())), repo);
                                    if(!tiposCargasRetorno.equals("")) {
                                        throw new DataFormatException(tiposCargasRetorno);
                                    }
                                    break;
                                case "-CARGAS.CSV":
                                    String cargasRetorno = lerCarga(new Scanner(Files.newBufferedReader(Paths.get(nomeArquivoField.getText() + "" + arquivo), Charset.defaultCharset())), repo);
                                    if(!cargasRetorno.equals("")) {
                                        throw new DataFormatException(cargasRetorno);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        mensagem.setForeground(Color.BLACK);
                        mensagem.setText(portosString(repo));
                        // JPanel contentPanel = new JPanel(new GridLayout(1, 4));
                        // JLabel mensagemPortos = new JLabel();
                        // mensagemPortos.setForeground(Color.BLACK);
                        // mensagemPortos.setText(portosString(repo));
                        // JLabel mensagemNavios = new JLabel();
                        // JLabel mensagemClientes = new JLabel();
                        // JLabel mensagemCargas = new JLabel();
                        // contentPanel.add(mensagemPortos);
                        // painel.add(contentPanel);
                        // nomeArquivoField.setText("");
                    }
                    catch(DataFormatException exc) {
                        System.out.println(exc.getMessage());
                        mensagem.setForeground(Color.RED);
                        mensagem.setText(exc.getMessage());
                    }
                    catch(Exception ex) {
                        mensagem.setForeground(Color.RED);
                        mensagem.setText("Nome inválido, arquivos não foram encontrados");
                    }
                }
                else {
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("Preencha o campo");
                }
                
            }
        });
        painel.add(mensagem);
    }

    public static String lerNavios(Scanner sc, SistemaPorto sp) {
        sc.useDelimiter("[\\n]"); // separadores: nova linha
        sc.next();
        while (sc.hasNext()) {
            String line = sc.next();
            String[] slices = line.split(";");
            try {
                double velocidade = Double.parseDouble(slices[1].replace(',', '.'));
                double autonomia = Double.parseDouble(slices[2].replace(',', '.'));
                double customilhabasico = Double.parseDouble(slices[3].replace(',', '.'));
                sp.cadastrarNavio(slices[0], velocidade, autonomia, customilhabasico);
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
                return "ERRO: Erro na leitura do arquivo de navios, a carga de dados não prosseguirá";
            }
        }
        return "";
    }

    public static String lerDistancias(Scanner sc, SistemaPorto sp) {
        sc.useDelimiter("[\\n]"); // separadores: nova linha
        sc.next();
        while (sc.hasNext()) {
            String line = sc.next();
            String[] slices = line.split(";");
            try {
                int origem = Integer.parseInt(slices[0]);
                int destino = Integer.parseInt(slices[1]);
                double distancia = Double.parseDouble(slices[2].replace(',', '.'));
                sp.cadastrarDistancia(origem, destino, distancia);
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
                return "ERRO: Erro na leitura do arquivo de distancias, a carga de dados não prosseguirá";
            }
        }
        return "";
    }

    public static String lerPortos(Scanner sc, SistemaPorto sp) {
        sc.useDelimiter("[\\n]"); // separadores: nova linha
        sc.next();
        while (sc.hasNext()) {
            String line = sc.next();
            String[] slices = line.split(";");
            try {
                int id = Integer.parseInt(slices[0]);
                sp.cadastrarPorto(id, slices[1], slices[2]);
            }
            catch(Exception e) {
                return "ERRO: Erro na leitura do arquivo de portos, a carga de dados não prosseguirá";
            }
        }
        return "";
    }

    public static String lerClientes(Scanner sc, SistemaPorto sp) {
        sc.useDelimiter("[\\n]"); // separadores: nova linha
        sc.next(); 
        while (sc.hasNext()) {
            String line = sc.next();
            String[] slices = line.split(";");
            try {
                int cod = Integer.parseInt(slices[0].trim());
                sp.cadastrarCliente(cod, slices[1], slices[2]);
            }
            catch(Exception e) {
                return "ERRO: Erro na leitura do arquivo de clientes, a carga de dados não prosseguirá";
            }
        }
        return "";
    }

    public static String lerTipoDeCarga(Scanner sc, SistemaPorto sp) {
        sc.useDelimiter("[\\n]"); // separadores: nova linha
        sc.next();
        while (sc.hasNext()) {
            String line = sc.next();
            String[] slices = line.split(";");
            try {
                int numero = Integer.parseInt(slices[0]);
                if(slices[2].equals("PERECIVEL")) {
                    int tempo = Integer.parseInt(slices[4]);
                    sp.cadastrarTipoCarga(new CargaPerecivel(numero, slices[1], slices[3], tempo));
                }
                else {
                    double porcentagemIPI = Double.parseDouble(slices[5].replace(',', '.'));
                    sp.cadastrarTipoCarga(new CargaDuravel(numero, slices[1], slices[3], slices[4], porcentagemIPI));
                }
            }
            catch(Exception e) {
                System.out.println(e);
                return "ERRO: Erro na leitura do arquivo de tipos de cargas, a carga de dados não prosseguirá";
            }
        }
        return "";
    }
    
    public static String lerCarga(Scanner sc, SistemaPorto sp) {
        sc.useDelimiter("[\\n]"); // separadores: nova linha
        sc.next();
        while (sc.hasNext()) {
            String line = sc.next();
            String[] slices = line.split(";");
            try {
                int codigo = Integer.parseInt(slices[0]);
                int codCliente = Integer.parseInt(slices[1]);
                Cliente cliente = sp.getClientes().stream().filter(c -> c.getCodigo() == codCliente).toList().get(0);
                int codOrigem = Integer.parseInt(slices[2]);
                int codDestino = Integer.parseInt(slices[3]);
                int peso = Integer.parseInt(slices[4]);
                Double valorDeclarado = Double.parseDouble(slices[5].replace(',', '.'));
                int tempoMaximo = Integer.parseInt(slices[6]);
                int codTipoCarga = Integer.parseInt(slices[7]);
                TipoCarga tipoCarga = sp.getTiposCargas().stream().filter(tc -> tc.getNumero() == codTipoCarga).toList().get(0);
                sp.cadastrarCarga(codigo, codOrigem, codDestino, cliente, peso, valorDeclarado, tempoMaximo, slices[9], tipoCarga, slices[8]);

            }
            catch(Exception e) {
                System.out.println(e);
                return "ERRO: Erro na leitura do arquivo de cargas, a carga de dados não prosseguirá";
            }
        }
        return "";
    }

    public static String portosString(SistemaPorto sp) {

        String mensagemString = "<html> Portos: ";
        for (Porto porto : sp.getPortos()) {
            mensagemString += "Id: " + porto.getId() + ", nome: " + porto.getNome() + ", pais: " + porto.getPais() + " | ";
        }
        mensagemString += " <br /> Navios: ";
        for (Navio navio : sp.getNavios()) {
            mensagemString += "Nome: " + navio.getNome() + ", velocidade: " + navio.getVelocidade() + ", autonomia:" + navio.autonomia() + ", custo por milha basico: " + navio.getCustoPorMilhaBasico() + " | ";
        }
        mensagemString += "<br /> Clientes:";
        for (Cliente cliente: sp.getClientes()) {
            mensagemString += "Código: " + cliente.getCodigo() + ", nome:" + cliente.getNome() + ", email: " + cliente.getEmail() + " | ";
        }
        mensagemString += "<br /> Cargas: \n";
        for (Carga carga: sp.getCargas()) {
            mensagemString += "Id: " + carga.getIdentificador() + ", peso: " + carga.getPeso() + ", valor declarado: " + carga.getValorDeclarado() + ", tempo máximo: " + carga.getTempoMaximo() + " | "; 
        }
        mensagemString += "</html>";
        return mensagemString;
    }
    public static void main(String[] args) {
        CarregarDadosIniciais a =  new CarregarDadosIniciais();
    }
}
