package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import dados.Carga;
import dados.CargaDuravel;
import dados.CargaPerecivel;
import dados.Cliente;
import dados.Distancia;
import dados.Navio;
import dados.Porto;
import dados.SistemaPorto;
import dados.TipoCarga;

public class InterfaceDados extends JFrame{
    private JTextField nomeArquivoField;
    private JButton botaoFinal;
    private JButton botaoSalvarDados;
    private JButton botaoCarregarDadosIniciais;
    private JButton botaoCarregarDados;
    private JLabel mensagem;
    private SistemaPorto sp;

    public InterfaceDados(SistemaPorto repo) {
        super();
        sp = repo;
        GridLayout gridCampos = new GridLayout(2, 2);
        JPanel painelCampos = new JPanel(gridCampos);
        JLabel arquivoLabel = new JLabel("Digite o nome do arquivo e a opção desejada");
        nomeArquivoField = new JTextField();

        painelCampos.add(arquivoLabel);
        painelCampos.add(nomeArquivoField);

        botaoFinal = new JButton("Finalizar ");
        botaoSalvarDados = new JButton("Salvar Dados");
        botaoCarregarDados = new JButton("Carregar Dados");
        botaoCarregarDadosIniciais = new JButton("Carregar Dados INICIAIS");
        mensagem = new JLabel();

        GridLayout grid = new GridLayout(4, 1);
        JPanel painel = new JPanel(grid);
        painel.add(painelCampos);
        FlowLayout botaoLayout = new FlowLayout(FlowLayout.RIGHT);
        JPanel botaoPainel = new JPanel(botaoLayout);
        botaoPainel.add(botaoFinal);
        botaoPainel.add(botaoSalvarDados);
        botaoPainel.add(botaoCarregarDados);
        botaoPainel.add(botaoCarregarDadosIniciais);
        painel.add(botaoPainel);
        painel.add(mensagem);

        this.setTitle("Entrada/Saida de Dados");
        this.setSize(600,300);
        this.add(painel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


        TratadorEventos tratadorEventos = new TratadorEventos();
        botaoFinal.addActionListener(tratadorEventos);
        botaoSalvarDados.addActionListener(tratadorEventos);
        botaoCarregarDados.addActionListener(tratadorEventos);
        botaoCarregarDadosIniciais.addActionListener(tratadorEventos);
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
            else if(e.getSource() == botaoSalvarDados)
            {
                try {
                    String file = nomeArquivoField.getText();
                   
                    salvarArquivoPortos(file, sp);
                    salvarArquivoNavios(file, sp);
                    salvarArquivoClientes(file, sp);
                    salvarArquivoCargas(file, sp);
                    salvarArquivoTipoCarga(file, sp);
                    salvarArquivoDistancia(file, sp);

                    mensagem.setForeground(Color.BLACK);
                    mensagem.setText("Salvamento concluido");
                } catch (Exception er) {
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("Erro");
                }
            }
            else if (e.getSource() == botaoCarregarDados) {
                if (!nomeArquivoField.getText().equals("")) {
                    ArrayList<String> arquivos = new ArrayList<>();
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
                                    carregarArquivoPortos(nomeArquivoField.getText() + arquivo, sp);
                                    break;
                                case "-DISTANCIAS.CSV":
                                    carregarArquivoDistancia(nomeArquivoField.getText() + arquivo, sp);
                                    break;
                                case "-NAVIOS.CSV":
                                    carregarArquivoNavios(nomeArquivoField.getText() + arquivo, sp);
                                    break;
                                case "-CLIENTES.CSV":
                                    carregarArquivoClientes(nomeArquivoField.getText() + arquivo, sp);
                                    break;
                                case "-TIPOSCARGAS.CSV":
                                    carregarArquivoTiposCargas(nomeArquivoField.getText() + arquivo, sp);
                                    break;
                                case "-CARGAS.CSV":
                                    carregarArquivoCargas(nomeArquivoField.getText() + arquivo, sp);
                                    break;
                                default:
                                    break;
                            }
                        }
                        mensagem.setForeground(Color.BLACK);
                        mensagem.setText(portosString(sp));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        mensagem.setForeground(Color.RED);
                        mensagem.setText("Erro ao carregar arquivos: " + ex.getMessage());
                    }
                } else {
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("Preencha o campo");
                }
            }
            else if(e.getSource() == botaoCarregarDadosIniciais)
            {
                if(!nomeArquivoField.getText().equals("")) {
                    ArrayList<String> arquivos = new ArrayList<>();
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
                                    String portosRetorno = lerPortos(new Scanner(Files.newBufferedReader(Paths.get(nomeArquivoField.getText() + "" + arquivo), Charset.defaultCharset())), sp);
                                    if(!portosRetorno.equals("")) {
                                        throw new DataFormatException(portosRetorno);
                                    }
                                    break;
                                case "-DISTANCIAS.CSV":
                                    String distanciaRetorno = lerDistancias(new Scanner(Files.newBufferedReader(Paths.get(nomeArquivoField.getText() + "" + arquivo), Charset.defaultCharset())), sp);
                                    if(!distanciaRetorno.equals("")) {
                                        throw new DataFormatException(distanciaRetorno);
                                    }
                                    break;
                                    
                                case "-NAVIOS.CSV":
                                    String naviosRetorno = lerNavios(new Scanner(Files.newBufferedReader(Paths.get(nomeArquivoField.getText() + "" + arquivo), Charset.defaultCharset())), sp);
                                    if(!naviosRetorno.equals("")) {
                                        throw new DataFormatException(naviosRetorno);
                                    }
                                    break;
                                    
                                case "-CLIENTES.CSV":
                                    String clientesRetorno = lerClientes(new Scanner(Files.newBufferedReader(Paths.get(nomeArquivoField.getText() + "" + arquivo), Charset.defaultCharset())), sp);
                                    if(!clientesRetorno.equals("")) {
                                        throw new DataFormatException(clientesRetorno);
                                    }
                                    break;
                                case "-TIPOSCARGAS.CSV":
                                    String tiposCargasRetorno = lerTipoDeCarga(new Scanner(Files.newBufferedReader(Paths.get(nomeArquivoField.getText() + "" + arquivo), Charset.defaultCharset())), sp);
                                    if(!tiposCargasRetorno.equals("")) {
                                        throw new DataFormatException(tiposCargasRetorno);
                                    }
                                    break;
                                case "-CARGAS.CSV":
                                    String cargasRetorno = lerCarga(new Scanner(Files.newBufferedReader(Paths.get(nomeArquivoField.getText() + "" + arquivo), Charset.defaultCharset())), sp);
                                    if(!cargasRetorno.equals("")) {
                                        throw new DataFormatException(cargasRetorno);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        mensagem.setForeground(Color.BLACK);
                        mensagem.setText(portosString(sp));
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
        }
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

    // Metodos de salvar Arquivo.

    private static void salvarArquivoPortos(String nomeArquivo, SistemaPorto sp) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("PORTOS.CSV"))) {
            writer.write("id;nome;pais");
            writer.newLine();
            for (Porto porto : sp.getPortos()) {
                writer.write(porto.getId() + ";" + porto.getNome() + ";" + porto.getPais() + "\n");
            }
        }
    }

    private static void salvarArquivoNavios(String nomeArquivo, SistemaPorto sp) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("NAVIO.CSV"))) {
            writer.write("nome;velocidade;autonomia;customilhabasico");
            writer.newLine();
            for (Navio navio : sp.getNavios()) {
                writer.write(navio.getNome() + ";" + navio.getVelocidade() + ";" + navio.getAutonomia() + ";" + navio.getCustoPorMilhaBasico() + "\n");
            }
        }
    }

    private static void salvarArquivoClientes(String nomeArquivo, SistemaPorto sp) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CLIENTE.CSV"))) {
            writer.write("cod;nome;email");
            writer.newLine();
            for (Cliente cliente : sp.getClientes()) {
                writer.write(cliente.getCodigo() + ";" + cliente.getNome() + ";" + cliente.getEmail() + "\n");
            }
        }
    }

    private static void salvarArquivoCargas(String nomeArquivo, SistemaPorto sp) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CARGAS.CSV"))) {
            writer.write("codigo;cliente;origem;destino;peso;valordeclarado;tempomaximo;tipocarga;prioridade;situacao");
            writer.newLine();
            for (Carga carga : sp.getCargas()) {
                writer.write(carga.getIdentificador() + ";" + carga.getCodigo().getCodigo() + carga.getPortoOrigem() + ";" + carga.getPortoDestino() + ";" +
                             carga.getPeso() + ";" + carga.getValorDeclarado() + ";" + carga.getTempoMaximo() + ";" +
                             carga.getPrioridade() + ";" + carga.getNumero().getNumero() + ";" +
                             carga.getSituacao() + "\n");
            }
        }
    }

    public static void salvarArquivoTipoCarga(String arquivo, SistemaPorto sp) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("TIPOSCARGAS.CSV"))) {
            writer.write("numero;descricao;categoria;origem_setor;tempomaximo_material" + "\n");
            

            for (TipoCarga tipoCarga : sp.getTiposCargas()) {
                if (tipoCarga instanceof CargaPerecivel) {
                    CargaPerecivel cargaPerecivel = (CargaPerecivel) tipoCarga;
                    writer.write(cargaPerecivel.getNumero() + ";"
                            + cargaPerecivel.getDescricao() + ";"
                            + "PERECIVEL" + ";"
                            + cargaPerecivel.getOrigem() + ";"
                            + cargaPerecivel.getTempoValidade() + System.lineSeparator());
                } else if (tipoCarga instanceof CargaDuravel) {
                    CargaDuravel cargaDuravel = (CargaDuravel) tipoCarga;
                    writer.write(cargaDuravel.getNumero() + ";"
                            + cargaDuravel.getDescricao() + ";"
                            + "DURAVEL" + ";"
                            + cargaDuravel.getSetor() + ";"
                            + cargaDuravel.getMaterial() + ";"
                            + cargaDuravel.getPorcentagemIPI() + System.lineSeparator() + "\n");
                }
            }
        }
    }

    public static void salvarArquivoDistancia(String arquivo, SistemaPorto sp) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DISTANCIA.CSV"))) {
            writer.write("origem;destino;distancia" + System.lineSeparator());

            ArrayList<Distancia> distancias = sp.getDistancias();

            for (Distancia distancia : distancias) {
                int origem = distancia.getOrigem();
                int destino = distancia.getDestino();
                double valorDistancia = distancia.getDistancia();

                writer.write(origem + ";" + destino + ";" + valorDistancia + System.lineSeparator());
            }
        }
    }

    // Metodos de carregar Arquivos.

    private static void carregarArquivoPortos(String nomeArquivo, SistemaPorto sp) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo + "PORTO.CSV"))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] slices = line.split(";");
                if (slices.length >= 3) {
                    int id = Integer.parseInt(slices[0]);
                    String nome = slices[1];
                    String pais = slices[2];
                    sp.cadastrarPorto(id, nome, pais);
                }
            }
        }
    }

    private static void carregarArquivoNavios(String nomeArquivo, SistemaPorto sp) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo + "NAVIO.CSV"))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] slices = line.split(";");
                if (slices.length >= 4) {
                    String nome = slices[0];
                    double velocidade = Double.parseDouble(slices[1]);
                    double autonomia = Double.parseDouble(slices[2]);
                    double custoMilhaBasico = Double.parseDouble(slices[3]);
                    sp.cadastrarNavio(nome, velocidade, autonomia, custoMilhaBasico);
                }
            }
        }
    }

    private static void carregarArquivoClientes(String nomeArquivo, SistemaPorto sp) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo + "CLIENTE.CSV"))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] slices = line.split(";");
                if (slices.length >= 3) {
                    int codigo = Integer.parseInt(slices[0]);
                    String nome = slices[1];
                    String email = slices[2];
                    sp.cadastrarCliente(codigo, nome, email);
                }
            }
        }
    }

    private static void carregarArquivoCargas(String nomeArquivo, SistemaPorto sp) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo + "CARGA.CSV"))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] slices = line.split(";");
                if (slices.length >= 10) {
                    int identificador = Integer.parseInt(slices[0]);
                    int portoOrigem = Integer.parseInt(slices[1]);
                    int portoDestino = Integer.parseInt(slices[2]);
                    int codigoCliente = Integer.parseInt(slices[3]);
                    int peso = Integer.parseInt(slices[4]);
                    double valorDeclarado = Double.parseDouble(slices[5]);
                    int tempoMaximo = Integer.parseInt(slices[6]);
                    String situacao = slices[7];
                    int numero = Integer.parseInt(slices[8]);
                    String prioridade = slices[9];

                    Cliente cliente = null;
                    for (Cliente c : sp.getClientes()) {
                        if (c.getCodigo() == codigoCliente) {
                            cliente = c;
                            break;
                        }
                    }

                    TipoCarga tipoCarga = null;
                    for (TipoCarga tc : sp.getTiposCargas()) {
                        if (tc.getNumero() == numero) {
                            tipoCarga = tc;
                            break;
                        }
                    }

                    if (cliente != null && tipoCarga != null) {
                        sp.cadastrarCarga(identificador, portoOrigem, portoDestino, cliente, peso, valorDeclarado, tempoMaximo, situacao, tipoCarga, prioridade);
                    } else {
                        System.out.println("Erro: Cliente ou TipoCarga não encontrado para a carga com identificador " + identificador);
                    }
                }
            }
        }
    }

    public static void carregarArquivoDistancia(String arquivo, SistemaPorto sp) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] slices = line.split(";");
                int origem = Integer.parseInt(slices[0]);
                int destino = Integer.parseInt(slices[1]);
                double distancia = Double.parseDouble(slices[2].replace(',', '.'));
                sp.cadastrarDistancia(origem, destino, distancia);
            }
        }
    }

    public static void carregarArquivoTiposCargas(String arquivo, SistemaPorto sp) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] slices = line.split(";");
                int numero = Integer.parseInt(slices[0]);
                String nome = slices[1];
                String descricao = slices[2];
                if (slices[3].equals("PERECIVEL")) {
                    int tempo = Integer.parseInt(slices[4]);
                    sp.cadastrarTipoCarga(new CargaPerecivel(numero, nome, descricao, tempo));
                } else {
                    String material = slices[3];
                    double porcentagemIPI = Double.parseDouble(slices[4].replace(',', '.'));
                    sp.cadastrarTipoCarga(new CargaDuravel(numero, nome, descricao, material, porcentagemIPI));
                }
            }
        }
    }
    
    public static String portosString(SistemaPorto sp) {

        String mensagemString = "<html> Portos: ";
        for (Porto porto : sp.getPortos()) {
            mensagemString += "Id: " + porto.getId() + ", nome: " + porto.getNome() + ", pais: " + porto.getPais() + " | ";
        }
        mensagemString += " <br /> Navios: ";
        for (Navio navio : sp.getNavios()) {
            mensagemString += "Nome: " + navio.getNome() + ", velocidade: " + navio.getVelocidade() + ", autonomia:" + navio.getAutonomia() + ", custo por milha basico: " + navio.getCustoPorMilhaBasico() + " | ";
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
        // InterfaceDados save = new InterfaceDados();
        }
}
