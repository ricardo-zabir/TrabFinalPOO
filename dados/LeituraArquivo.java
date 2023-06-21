package dados;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeituraArquivo {
    public static List<String> lerArquivo(String nomeArquivo) throws IOException {
        List<String> linhas = new ArrayList<>();

        try (Scanner scanner = new Scanner(Path.of(nomeArquivo), Charset.defaultCharset())) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                linhas.add(linha);
            }
        }

        return linhas;
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



    public static String salvarDados(Scanner sc, SistemaPorto sp) {
        System.out.print("Digite o nome do arquivo para salvar os dados (sem extensão): ");
        String nomeArquivo = sc.next();
        
        try {
            salvarArquivoPortos(nomeArquivo, sp.getPortos());
            salvarArquivoNavios(nomeArquivo, sp.getNavios());
            salvarArquivoClientes(nomeArquivo, sp.getClientes());
            salvarArquivoCargas(nomeArquivo, sp.getCargas());
        } catch (IOException e) {
            return "ERRO: Problema ao salvar os dados. " + e.getMessage();
        }

        return "Dados salvos com sucesso no arquivo " + nomeArquivo + ".txt";
    }

    private static void salvarArquivoPortos(String nomeArquivo, List<Porto> portos) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo + "_portos.txt"))) {
            writer.write("Id;Nome;Pais");
            writer.newLine();
            for (Porto porto : portos) {
                writer.write(porto.getId() + ";" + porto.getNome() + ";" + porto.getPais());
                writer.newLine();
            }
        }
    }

    private static void salvarArquivoNavios(String nomeArquivo, List<Navio> navios) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo + "_navios.txt"))) {
            writer.write("Nome;Velocidade;Autonomia;CustoMilhaBasico");
            writer.newLine();
            for (Navio navio : navios) {
                writer.write(navio.getNome() + ";" + navio.getVelocidade() + ";" + navio.autonomia() + ";" + navio.getCustoPorMilhaBasico());
                writer.newLine();
            }
        }
    }

    private static void salvarArquivoClientes(String nomeArquivo, List<Cliente> clientes) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo + "_clientes.txt"))) {
            writer.write("Codigo;Nome;Email");
            writer.newLine();
            for (Cliente cliente : clientes) {
                writer.write(cliente.getCodigo() + ";" + cliente.getNome() + ";" + cliente.getEmail());
                writer.newLine();
            }
        }
    }

    private static void salvarArquivoCargas(String nomeArquivo, List<Carga> cargas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo + "_cargas.txt"))) {
            writer.write("Identificador;PortoOrigem;PortoDestino;CodigoCliente;Peso;ValorDeclarado;TempoMaximo;Situacao;Numero;Prioridade");
            writer.newLine();
            for (Carga carga : cargas) {
                writer.write(carga.getIdentificador() + ";" + carga.getPortoOrigem() + ";" + carga.getPortoDestino() + ";" +
                            carga.getCodigo().getCodigo() + ";" + carga.getPeso() + ";" + carga.getValorDeclarado() + ";" +
                            carga.getTempoMaximo() + ";" + carga.getSituacao() + ";" + carga.getNumero().getNumero() + ";" +
                            carga.getPrioridade());
                writer.newLine();
            }
        }
    }
}



    
