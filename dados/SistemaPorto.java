package dados;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SistemaPorto {

    private ArrayList<Porto> portos;
    private ArrayList<Carga> cargas;
    private ArrayList<TipoCarga> tipoCargas;

    public SistemaPorto() {
        portos = new ArrayList<>();
        cargas = new ArrayList<>();
        tipoCargas = new ArrayList<>();
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
    
    public void mostrarPortos() {
        if (portos.isEmpty()) {
            System.out.println("Não há portos cadastrados.");
        } else {
            System.out.println("Lista de Portos:");
            for (Porto porto : portos) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + porto.getId());
                System.out.println("Nome: " + porto.getNome());
                System.out.println("Pais: " + porto.getPais());
                System.out.println("Distância: " + porto.getDistancia() + " milhas náuticas");
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

        Carga novaCarga = new Carga(identificador, portoOrigem, portoDestino, cliente, peso, valorDeclarado, tempoMaximo, situacao, tipoCarga, prioridade);

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

    public void mostrarCargas() {
        if (cargas.isEmpty()) {
            System.out.println("Não há cargas cadastradas.");
        } else {
            System.out.println("Lista de Cargas:");
            for (Carga carga : cargas) {
                System.out.println("-----------------------------------");
                System.out.println("Identificador: " + carga.getIdentificador());
                System.out.println("Cliente: " + carga.getCodigo().getCodigo());
                System.out.println("Origem: " + carga.getPortoOrigem());
                System.out.println("Destino: " + carga.getPortoDestino());
                System.out.println("Peso: " + carga.getPeso());
                System.out.println("Valor Declarado: " + carga.getValorDeclarado());
                System.out.println("Tempo Máximo: " + carga.getTempoMaximo());
                System.out.println("Tipo de Carga: " + carga.getNumero().getDescricao());
                System.out.println("Prioridade: " + carga.getPrioridade());
                System.out.println("Situação: " + carga.getSituacao());
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

    private void setLocado(){
        //this.situacao = "Locado";
    }

    private void setCancelado(){
        //this.situacao = "Cancelado";
    }

    private void setFinalizado(){
        //this.situacao = "Finalizado";
    }
}

