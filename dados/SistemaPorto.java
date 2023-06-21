package dados;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SistemaPorto {

    private ArrayList<Porto> portos;
    private ArrayList<Carga> cargas;
    private ArrayList<Navio> navios;
    private ArrayList<Cliente> clientes;
    private ArrayList<TipoCarga> tipoCargas;
    private ArrayList<Distancia> distancias;

    public SistemaPorto() {
        portos = new ArrayList<>();
        cargas = new ArrayList<>();
        navios = new ArrayList<>();
        clientes = new ArrayList<>();
        tipoCargas = new ArrayList<>();
        distancias = new ArrayList<>();
    }

    public ArrayList<Porto> getPortos() {
        return portos;
    }

    public ArrayList<Carga> getCargas() {
        return cargas;
    }

    public ArrayList<Navio> getNavios() {
        return navios;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<TipoCarga> getTiposCargas() {
        return tipoCargas;
    }

    public ArrayList<Distancia> getDistancias()  {
        return distancias;
    }

    public void cadastrarPorto(int id, String nome, String pais) {
        // Verifica se o porto já está cadastrado
        for (Porto porto : portos) {
            if (porto.getId() == id) {
                System.out.println("Erro: Porto com o identificador indicado já cadastrado.");
                return;
            }
        }

        Porto novoPorto = new Porto(id, nome, pais, 0);

        // Encontra a posição onde o porto deve ser inserido para manter a ordem crescente de identificador
        int posicaoInsercao = 0;
        while (posicaoInsercao < portos.size() && portos.get(posicaoInsercao).getId() < id) {
            posicaoInsercao++;
        }

        // Insere o porto na posição correta
        portos.add(posicaoInsercao, novoPorto);

        // Atualiza a distância de 100 milhas náuticas para os portos existentes
        for (int i = 0; i < portos.size(); i++) {
            if (i != posicaoInsercao) {
                portos.get(i).setDistancia(100);
            }
        }
    }

    public void cadastrarCarga(int identificador, int portoOrigem, int portoDestino, Cliente cliente, int peso, Double valorDeclarado, int tempoMaximo, String situacao, TipoCarga tipoCarga, String prioridade) {
        // Verifica se a carga já está cadastrada
        for (Carga carga : cargas) {
            if (carga.getIdentificador() == identificador) {
                System.out.println("Erro: Carga com o identificador indicado já cadastrada.");
                return;
            }
        }

        Carga novaCarga = new Carga(identificador, portoOrigem, portoDestino, cliente, peso, valorDeclarado, tempoMaximo, situacao = "PENDENTE", tipoCarga, prioridade);

        // Encontra a posição onde a carga deve ser inserida para manter a ordem crescente de identificador
        int posicaoInsercao = 0;
        while (posicaoInsercao < cargas.size() && cargas.get(posicaoInsercao).getIdentificador() < identificador) {
            posicaoInsercao++;
        }

        // Insere a carga na posição correta
        cargas.add(posicaoInsercao, novaCarga);

        // Mostra os dados da nova carga cadastrada
        System.out.println("Nova carga cadastrada com sucesso:");
        System.out.println("Código: " + novaCarga.getIdentificador());
        System.out.println("Cliente: " + novaCarga.getCodigo().getCodigo());
        System.out.println("Origem: " + novaCarga.getPortoOrigem());
        System.out.println("Destino: " + novaCarga.getPortoDestino());
        System.out.println("Peso: " + novaCarga.getPeso());
        System.out.println("Valor Declarado: " + novaCarga.getValorDeclarado());
        System.out.println("Tempo Máximo: " + novaCarga.getTempoMaximo());
        System.out.println("Tipo de Carga: " + novaCarga.getNumero().getDescricao());
        System.out.println("Prioridade: " + novaCarga.getPrioridade());
        System.out.println("Situação: " + novaCarga.getSituacao());
    }

    public void cadastrarNavio(String nome, double velocidade, double autonomia, double custoMilhaBasico) {
        // Verifica se o navio já está cadastrado
        for (Navio navio : navios) {
            if (navio.getNome().equals(nome)) {
                System.out.println("Erro: Navio já está cadastrado com o nome indicado.");
                return;
            }
        }

        Navio novoNavio = new Navio(nome, velocidade, autonomia, custoMilhaBasico, 0, "");

        // Encontra a posição onde o navio deve ser inserido para manter a ordem crescente de nome
        int posicaoInsercao = 0;
        while (posicaoInsercao < navios.size() && navios.get(posicaoInsercao).getNome().compareTo(nome) < 0) {
            posicaoInsercao++;
        }

        // Insere o navio na posição correta
        navios.add(posicaoInsercao, novoNavio);

        // Grava o navio no arquivo CSV
        try (PrintWriter writer = new PrintWriter(new FileWriter("NAVIO.CSV", true))) {
            writer.println(novoNavio.getNome() + ";" + novoNavio.getVelocidade() + ";" + novoNavio.getAutonomia() + ";" + novoNavio.getCustoPorMilhaBasico());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cadastrarCliente(int codigo, String nome, String email) {
        // Verifica se o cliente já está cadastrado
        for (Cliente cliente : clientes) {
            if (cliente.getCodigo() == codigo || cliente.getEmail().equals(email)) {
                System.out.println("Erro: Cliente já cadastrado no sistema.");
                return;
            }
        }

        Cliente novoCliente = new Cliente(codigo, nome, email);

        // Encontra a posição onde o cliente deve ser inserido para manter a ordem crescente de código
        int posicaoInsercao = 0;
        while (posicaoInsercao < clientes.size() && clientes.get(posicaoInsercao).getCodigo() < codigo) {
            posicaoInsercao++;
        }

        // Insere o cliente na posição correta
        clientes.add(posicaoInsercao, novoCliente);
    }

    public void cadastrarTipoCarga(TipoCarga tc) {
        // Verifica se o tipo de carga já está cadastrado
        for (TipoCarga tipo : tipoCargas) {
            if (tipo.getNumero() == tc.getNumero()) {
                System.out.println("Erro: Tipo de carga com o número indicado já cadastrado.");
                return;
            }
        }

        // Encontra a posição onde o tipo de carga deve ser inserido para manter a ordem crescente de número
        int posicaoInsercao = 0;
        while (posicaoInsercao < tipoCargas.size() && tipoCargas.get(posicaoInsercao).getNumero() < tc.getNumero()) {
            posicaoInsercao++;
        }

        // Insere o tipo de carga na posição correta
        tipoCargas.add(posicaoInsercao, tc);
    }

    public void cadastrarDistancia (int codOrigem, int codDestino, double distancia) {
        this.distancias.add(new Distancia(codOrigem, codDestino, distancia));
    }

    public String consultarCargas() {
        String msg = "";
        if (cargas.isEmpty()) {
            msg += "Não há cargas cadastradas.";
            return msg;
        }

        for (Carga carga : cargas) {
            msg += "Carga:";
            msg += "Identificador: " + carga.getIdentificador();
            msg += "Cliente cod: " + carga.getCodigo().getCodigo();
            msg += "Porto origem cod:  " + carga.getPortoOrigem();
            msg += "Porto destino cod: " + carga.getPortoDestino();
            msg += "Peso: " + carga.getPeso();
            msg += "Valor Declarado: " + carga.getValorDeclarado();
            msg += "Tempo Máximo: " + carga.getTempoMaximo();
            msg += "Tipo de Carga: " + carga.getNumero().getDescricao();
            msg += "Prioridade: " + carga.getPrioridade();
            msg += "Situação: " + carga.getSituacao();

            if (carga.getNome() != null) {
                msg += "Navio Designado: " + carga.getNome().getNome();
                msg += "Valor do Frete: " + carga.getFrete();
            }

            System.out.println("--------------------");
        }
        return msg;
    }

    public void alterarSituacaoCarga(int codigoCarga, String novaSituacao) {
        // Procura a carga com o código indicado
        Carga carga = null;
        for (Carga c : cargas) {
            if (c.getIdentificador() == codigoCarga) {
                carga = c;
                break;
            }
        }

        // Verifica se a carga foi encontrada
        if (carga == null) {
            System.out.println("Erro: Não há carga com o código indicado.");
            return;
        }

        // Verifica se a carga está na situação FINALIZADO
        if (carga.getSituacao().equals("FINALIZADO")) {
            System.out.println("Erro: A carga já está na situação FINALIZADO e não pode ser alterada.");
            return;
        }

        // Atualiza a situação da carga
        carga.setSituacao(novaSituacao);

        // Mostra os dados atualizados da carga
        System.out.println("Situação da carga " + carga.getIdentificador() + " alterada com sucesso:");
        System.out.println("Cliente: " + carga.getCodigo().getCodigo());
        System.out.println("Origem: " + carga.getPortoOrigem());
        System.out.println("Destino: " + carga.getPortoDestino());
        System.out.println("Peso: " + carga.getPeso());
        System.out.println("Valor Declarado: " + carga.getValorDeclarado());
        System.out.println("Tempo Máximo: " + carga.getTempoMaximo());
        System.out.println("Tipo de Carga: " + carga.getNumero().getDescricao());
        System.out.println("Prioridade: " + carga.getPrioridade());
        System.out.println("Nova Situação: " + carga.getSituacao());
    }

    public void fretarCargas() {
        if (cargas.isEmpty()) {
            System.out.println("Erro: Não há cargas na fila de cargas pendentes.");
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

    public void mostrarPortos() {
        if (portos.isEmpty()) {
            System.out.println("Não há portos cadastrados.");
        } else {
            System.out.println("Lista de Portos:");
            for (Porto porto : portos) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + porto.getId());
                System.out.println("Nome: " + porto.getNome());
                System.out.println("País: " + porto.getPais());
            }
        }
    }

    public boolean verificarIdExistente(int id) {
        for (Porto porto : portos) {
            if (porto.getId() == id) {
                return true;
            }
        }
        return false;
    }
}

