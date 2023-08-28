package comunicacaoUDP;

/**
 *
 * @author Gustavo
 */
import java.net.*;
import java.util.List;

public class MeuServidorUDP {

    private static final int PORTA = 6789;

    private static String processarRequisicao(String requisicao) {
        String[] partes = requisicao.split(";");

        int tipoRequisicao = Integer.parseInt(partes[0]);
        String nomeUsuario = partes[1];

        MatrizUDP matrizUDP = new MatrizUDP();
        switch (tipoRequisicao) {
            case 1:
                // Solicitar título de filme para avaliação
                int usuario = obterIndiceUsuario(nomeUsuario);
                if (usuario == -1) {
                    return "Nenhum usuário encontrado";
                }
                    List<Integer> filmesNaoAvaliados = matrizUDP.getFilmesNaoAvaliados(usuario);

                    if (filmesNaoAvaliados.isEmpty()) {
                        return "Nenhum filme para avaliação";
                    }

                    int filmeSelecionado = filmesNaoAvaliados.get(0); // Seleciona o primeiro filme não avaliado
                    return obterTituloFilme(filmeSelecionado);

            case 2:
                // Registrar avaliação do usuário para um filme específico
                String tituloFilme = partes[2];
                int nota = Integer.parseInt(partes[3]);

                int filme = obterIndiceFilme(tituloFilme);
                if(filme == -1){
                    return "Nenhum filme encontrado";
                }
                
                usuario = obterIndiceUsuario(nomeUsuario);
                if(usuario == -1){
                    return "Nenhum usuário encontrado";
                }
                
                matrizUDP.registrarAvaliacao(usuario, filme, nota);

                return "Avaliação registrada com sucesso";

            case 3:
                // Solicitar recomendação de filme ou série
                usuario = obterIndiceUsuario(nomeUsuario);
                
                if(usuario == -1){
                    return "Nenhum usuário encontrado";
                }
                
                filmeSelecionado = matrizUDP.recomendarFilme(usuario);
                if (filmeSelecionado == -1) {
                    return "Não foi possível recomendar um filme";
                }

                return obterTituloFilme(filmeSelecionado);

            case 4:
                // Solicitar lista de avaliações feitas pelo usuário
                usuario = obterIndiceUsuario(nomeUsuario);
                if(usuario == -1){
                    return "Nenhum usuário encontrado";
                }
                
                StringBuilder listaAvaliacoes = new StringBuilder("Suas avaliações:\n");

                List<Integer> filmesAvaliados = matrizUDP.getFilmesAvaliados(usuario);

                if (filmesAvaliados.isEmpty()) {
                    listaAvaliacoes.append("Nenhuma avaliação feita");
                } else {
                    for (int filmeAvaliado : filmesAvaliados) {
                        int avaliacao = matrizUDP.getAvaliacao(usuario, filmeAvaliado);
                        String titulo = obterTituloFilme(filmeAvaliado);

                        (listaAvaliacoes).append(titulo).append(": ").append(avaliacao).append("\n");
                    }
                }

                return listaAvaliacoes.toString();

            default:
                return "Requisição inválida";
        }
    }

    private static int obterIndiceUsuario(String nomeUsuario) {
        String[] usuarios = {"Edson", "Lays", "Priscila", "Thiago", "Vanderson", "Asdrúbal", "Daniel",
            "Bianca", "Henrique"}; // Lista de nomes de usuários

        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i].equals(nomeUsuario)) {
                return i; // Retorna o índice do usuário
            }
        }

        return -1; // Usuário não encontrado
    }

    private static int obterIndiceFilme(String tituloFilme) {
        String[] titulos = {"O Último", "Os Três Porquinhos", "A Escuridão", "Tubarão", "Homem de Ferro",
            "As tranças do Rei Careca", "Barbie e o Castelo de Areia", "Blade", "Matrix",
            "Homem Aranha 2", "A volta dos que não foram", "A perigosa mordida dos Cachorros sem Dentes",
            "A dança do Créu", "A fuga das Galinhas", "A Branca de Neve", "Piranhas em Alto Mar",
            "Sítio do Pica Pau Amarelo", "Bob Esponja", "A Fantástica Fábrica de Chocolates"}; // Lista de títulos de filmes

        for (int i = 0; i < titulos.length; i++) {
            if (titulos[i].equals(tituloFilme)) {
                return i; // Retorna o índice do filme
            }
        }

        return -1; // Filme não encontrado
    }

    private static String obterTituloFilme(int indiceFilme) {
        String[] titulos = {"O Último", "Os Três Porquinhos", "A Escuridão", "Tubarão", "Homem de Ferro",
            "As tranças do Rei Careca", "Barbie e o Castelo de Areia", "Blade", "Matrix",
            "Homem Aranha 2", "A volta dos que não foram", "A perigosa mordida dos Cachorros sem Dentes",
            "A dança do Créu", "A fuga das Galinhas", "A Branca de Neve", "Piranhas em Alto Mar",
            "Sítio do Pica Pau Amarelo", "Bob Esponja", "A Fantástica Fábrica de Chocolates"}; // Lista de títulos de filmes

        if (indiceFilme >= 0 && indiceFilme < titulos.length) {
            return titulos[indiceFilme];
        }

        return "Filme não encontrado";
    }

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORTA);

            byte[] buffer = new byte[1024]; //recebendo a requisição do cliente

            while (true) {
                DatagramPacket requisicaoPacket = new DatagramPacket(buffer, buffer.length); //requisicao do cliente
                socket.receive(requisicaoPacket);

                String requisicao = new String(requisicaoPacket.getData(), 0, requisicaoPacket.getLength());
                String resposta = processarRequisicao(requisicao);

                InetAddress clientAddress = requisicaoPacket.getAddress();
                int clientPort = requisicaoPacket.getPort();
                byte[] responseData = resposta.getBytes();
                DatagramPacket respostaPacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);

                socket.send(respostaPacket); // enviando a resposta de volta ao cliente
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}