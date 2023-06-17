package dados;

import java.util.ArrayList;

public class SistemaPorto {

    private ArrayList<Porto> portos;

    public SistemaPorto() {
        portos = new ArrayList<>();
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

    public ArrayList<Porto> listarPortos() {
        return portos;
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

    public boolean verificarIdExistente(int id) {
        for (Porto porto : portos) {
            if (porto.getId() == id) {
                return true;
            }
        }
        return false;
    }
}