/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comunicacaoUDP;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public class MatrizUDP {
    private static int[][] matrizAvaliacoes;

    public MatrizUDP() {
        if(matrizAvaliacoes == null){
            matrizAvaliacoes = new int[10][20];
        }
        // Inicialize a matriz com valores 0
    }

    public int getAvaliacao(int usuario, int filme) {
        return matrizAvaliacoes[usuario][filme];
    }

    public void registrarAvaliacao(int usuario, int filme, int nota) {
        matrizAvaliacoes[usuario][filme] = nota;
    }

    public List<Integer> getFilmesNaoAvaliados(int usuario) {
        List<Integer> filmesNaoAvaliados = new ArrayList<>();
        for (int filme = 0; filme < matrizAvaliacoes[usuario].length; filme++) {
            if (matrizAvaliacoes[usuario][filme] == 0) {
                filmesNaoAvaliados.add(filme);
            }
        }
        return filmesNaoAvaliados;
    }
    
    public List<Integer> getFilmesAvaliados(int usuario) {
        List<Integer> filmesAvaliados = new ArrayList<>();
        for (int filme = 0; filme < matrizAvaliacoes[usuario].length; filme++) {
            if (matrizAvaliacoes[usuario][filme] > 0) {
                filmesAvaliados.add(filme);
            }
        }
        return filmesAvaliados;
    }

    public int recomendarFilme(int usuario) {
        List<Integer> filmesNaoAvaliados = getFilmesNaoAvaliados(usuario);

        int filmeRecomendado = -1;
        double menorDistancia = Double.MAX_VALUE;

        for (int outroUsuario = 0; outroUsuario < matrizAvaliacoes.length; outroUsuario++) {
            if (outroUsuario != usuario) {
                double distancia = calcularDistanciaEuclidiana(usuario, outroUsuario);
                if (distancia < menorDistancia) {
                    for (int filme : filmesNaoAvaliados) {
                        if (matrizAvaliacoes[outroUsuario][filme] > 0) {
                            filmeRecomendado = filme;
                            menorDistancia = distancia;
                        }
                    }
                }
            }
        }

        return filmeRecomendado;
    }

    public double calcularDistanciaEuclidiana(int usuario1, int usuario2) {
        int numFilmes = matrizAvaliacoes[0].length;
        double somaQuadrados = 0.0;

        for (int filme = 0; filme < numFilmes; filme++) {
            int avaliacaoUsuario1 = matrizAvaliacoes[usuario1][filme];
            int avaliacaoUsuario2 = matrizAvaliacoes[usuario2][filme];

            somaQuadrados += Math.pow(avaliacaoUsuario1 - avaliacaoUsuario2, 2);
        }

        return Math.sqrt(somaQuadrados);
    }
}
