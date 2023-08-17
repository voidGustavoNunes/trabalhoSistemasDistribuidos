/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidorUDP;

/**
 *
 * @author Gustavo
 */
public class MatrizUDP {
    String[][] matriz = new String[10][20];
    
    private String[][] Matriz(){
        int linhas = 10;
        int colunas = 20;
      

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = "0";
            }
        }

        //PESSOAS
        matriz[0][0] = "Henrique";
        matriz[1][0] = "Edson";
        matriz[2][0] = "Gustavo";
        matriz[3][0] = "Priscila";
        matriz[4][0] = "Thiago";
        matriz[5][0] = "Vanderson";
        matriz[6][0] = "Asdrúbal";
        matriz[7][0] = "Daniel";
        matriz[8][0] = "Bianca";
        matriz[9][0] = "Lays";
        
        //FILMES
        matriz[0][1] = "O Último";
        matriz[0][2] = "Os Três Porquinhos";
        matriz[0][3] = "A Escuridão";
        matriz[0][4] = "Tubarão";
        matriz[0][5] = "Homem de Ferro";
        matriz[0][6] = "As tranças do Rei Careca";
        matriz[0][7] = "Barbie e o Castelo de Areia";
        matriz[0][8] = "Blade";
        matriz[0][9] = "Matrix";
        matriz[0][10] = "Homem Aranha 2";
        matriz[0][11] = "A volta dos que não foram";
        matriz[0][12] = "A perigosa mordida dos Cachorros sem Dentes";
        matriz[0][13] = "A dança do Créu";
        matriz[0][14] = "A fuga das Galinhas";
        matriz[0][15] = "A Branca de Neve";
        matriz[0][16] = "Piranhas em Alto Mar";
        matriz[0][17] = "Sítio do Pica Pau Amarelo";
        matriz[0][18] = "Bob Esponja";
        matriz[0][19] = "A Fantástica Fábrica de Chocolates";
        
        
        return matriz;
    }
    
    public String leMatriz(){
        
    
    
    }
}
