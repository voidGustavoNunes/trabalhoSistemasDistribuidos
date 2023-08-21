
package comunicacaoUDP;

/**
 *
 * @author Gustavo
 */

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MeuServidorUDP {
    
    private static MatrizUDP matriz =null;
    
    private static Map<String, Map<String, Integer>> avaliacoes = new HashMap<>();
    
    // funcao para calcular a distancia euclidiana entre duas avaliacoes de filmes
    public static double calcularDistanciaEuclidiana(Map<String, Integer> avaliacoesUsuario1, Map<String, Integer> avaliacoesUsuario2) {
        double distancia = 0.0;

        for (String filme : avaliacoesUsuario1.keySet()) { //para cada filme de avaliaçõesUsuario1 pega o valor atribuido a avaliação pelo metodo keyset
            if (avaliacoesUsuario2.containsKey(filme)) { //se o filme avaliado pelo usuario1 foi também avaliado pelo usuario2
                int notaUsuario1 = avaliacoesUsuario1.get(filme); //pega a nota atribuida dos usuarios aos filmes
                int notaUsuario2 = avaliacoesUsuario2.get(filme);
                distancia += Math.pow(notaUsuario1 - notaUsuario2, 2); //realiza o calculo
            }
        }

        return Math.sqrt(distancia);
    }
    
    // recomenda um filme com base nas avaliacoes dos usuarios
    public static String recomendarFilme(String nomeUsuario) {
        Map<String, Integer> avaliacoesUsuario = avaliacoes.get(nomeUsuario);
        double menorDistancia = Double.MAX_VALUE;
        String filmeRecomendado = "";

        for (Map.Entry<String, Map<String, Integer>> entry : avaliacoes.entrySet()) {
            String outroUsuario = entry.getKey();
            if (!outroUsuario.equals(nomeUsuario)) {
                double distancia = calcularDistanciaEuclidiana(avaliacoesUsuario, entry.getValue());
                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    filmeRecomendado = entry.getValue().entrySet().stream() //pega os filmes da lista e coloca numa fila
                        .filter(e -> e.getValue() > 0)  // se o filme tiver nota maior que 0 mantemos ele na fila
                        .findFirst() //pega o primeiro da fila que tem o numero maior que 0
                        .map(Map.Entry::getKey) //armazena 
                        .orElse(""); //else
                }
            }
        }

        return filmeRecomendado;
    }
    
    public static void main(String[] args) {
        
        MatrizUDP matriz = new MatrizUDP(); //matriz de dados 
        String[][] dados = matriz.getMatriz();
        
        try {
            DatagramSocket socket =null;
            
            socket= new DatagramSocket(6789); //define a porta
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket requisicao = new DatagramPacket(buffer, buffer.length); //requisicao do cliente
                socket.receive(requisicao);

                String dadosRequisicao = new String(requisicao.getData(), 0, requisicao.getLength());
                String[] partesRequisicao = dadosRequisicao.split(";");

                String resposta = "";
                String nomeUsuario = partesRequisicao[1];

                if (partesRequisicao[0].equals("1")) {
                    // Verificar os filmes que o usuário ainda não avaliou
                    Map<String, Integer> avaliacoesUsuario = avaliacoes.getOrDefault(nomeUsuario, new HashMap<>());
                    String filmeNaoAvaliado = null;

                    for (String filme : dados[0]) {
                        if (!avaliacoesUsuario.containsKey(filme)) {
                            filmeNaoAvaliado = filme;
                            break;
                        }
                    }
                    resposta = filmeNaoAvaliado != null ? filmeNaoAvaliado : "Nenhum filme disponível para avaliação.";
                    
                } else if (partesRequisicao[0].equals("2")) {
                    String tituloFilme = partesRequisicao[2];
                    int avaliacao = Integer.parseInt(partesRequisicao[3]);
                    
                    
                    Map<String, Integer> avaliacoesUsuario = avaliacoes.getOrDefault(nomeUsuario, new HashMap<>());
                    avaliacoesUsuario.put(tituloFilme, avaliacao);
                    avaliacoes.put(nomeUsuario, avaliacoesUsuario);

                    resposta = "Avaliação registrada.";
                    
                } else if (partesRequisicao[0].equals("3")) {
                    resposta = recomendarFilme(nomeUsuario);
                }else if (partesRequisicao[0].equals("4")) {
                    Map<String, Integer> avaliacoesUsuario = avaliacoes.getOrDefault(nomeUsuario, new HashMap<>());
                    resposta = avaliacoesUsuario.toString();
                }

                DatagramPacket pacoteResposta = new DatagramPacket(
                    resposta.getBytes(),
                    resposta.length(),
                    requisicao.getAddress(),
                    requisicao.getPort()
                );
                socket.send(pacoteResposta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
