package comunicaoUDP;

public class MatrizUDP {
    int[][] matriz = new int[10][20];
    String[] users = new String[10];
    String[] filmes = new String[20];
    
    public MatrizUDP() {
        matriz = insereMatriz();
    }
    
    private int[][] insereMatriz(){
        int linhas = 10;
        int colunas = 20;
      

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = 0;
            }
        }

        //PESSOAS
        users[1] = "Edson";
        users[2] = "Lays";
        users[3] = "Priscila";
        users[4] = "Thiago";
        users[5] = "Vanderson";
        users[6] = "Asdrúbal";
        users[7] = "Daniel";
        users[8] = "Bianca";
        users[9] = "Henrique";
        
        //FILMES
        filmes[1] = "O Último";
        filmes[2] = "Os Três Porquinhos";
        filmes[3] = "A Escuridão";
        filmes[4] = "Tubarão";
        filmes[5] = "Homem de Ferro";
        filmes[6] = "As tranças do Rei Careca";
        filmes[7] = "Barbie e o Castelo de Areia";
        filmes[8] = "Blade";
        filmes[9] = "Matrix";
        filmes[10] = "Homem Aranha 2";
        filmes[11] = "A volta dos que não foram";
        filmes[12] = "A perigosa mordida dos Cachorros sem Dentes";
        filmes[13] = "A dança do Créu";
        filmes[14] = "A fuga das Galinhas";
        filmes[15] = "A Branca de Neve";
        filmes[16] = "Piranhas em Alto Mar";
        filmes[17] = "Sítio do Pica Pau Amarelo";
        filmes[18] = "Bob Esponja";
        filmes[19] = "A Fantástica Fábrica de Chocolates";
        
        return matriz;
    }
    
    public String proximoFilmeNaoAvaliado(String nomeUsuario) {
        String mensagem = "\n";
        int indiceUsuario = -1;
        for (int i = 0; i < 10; i++) {
            if (users[i].equals(nomeUsuario)) {
                indiceUsuario = i;
                break;
            }
        }

        if (indiceUsuario == -1) {
            mensagem = "Usuário " + nomeUsuario + " não existe.\n";
            return null;
        }

        int indiceFilme = -1;
        for (int i = 1; i < 20; i++) {
            if (matriz[indiceUsuario][i] == 0) {
                indiceFilme = i;
                mensagem = filmes[indiceFilme];
                break;
            }
        }

        if (indiceFilme == -1) {
            mensagem = "Usuário " + nomeUsuario + " já avaliou todos os filmes.\n";
            return null;
        }

        return mensagem;
    }
    
    public String avaliaFilme (String nomeUsuario, String tituloFilme, int nota) {
        String mensagem = "\n";
        int indiceUsuario = -1;
        for (int i = 0; i < 10; i++) {
            if (users[i].equals(nomeUsuario)) {
                indiceUsuario = i;
                break;
            }
        }

        if (indiceUsuario == -1) {
            mensagem = "Usuário " + nomeUsuario + " não existe.\n";
            return null;
        }

        int indiceFilme = -1;
        for (int i = 1; i < 20; i++) {
            if (filmes[i].equals(tituloFilme)) {
                indiceFilme = i;
                matriz[indiceUsuario][indiceFilme] = nota;
                mensagem = users[indiceUsuario] + " avaliou o filme '" + filmes[indiceFilme] + "' com " + matriz[indiceUsuario][indiceFilme] + ";\n";
                break;
            }
        }

        if (indiceFilme == -1) {
            mensagem = "Filme " + tituloFilme + " não existe.\n";
            return null;
        }

        return mensagem;
    }

    public String recomendaFilme(String nomeUsuario) {
        String mensagem = "\n";
        int indiceUsuario = -1;
        for (int i = 0; i < 10; i++) {
            if (users[i].equals(nomeUsuario)) {
                indiceUsuario = i;
                break;
            }
        }

        if (indiceUsuario == -1) {
            mensagem = "Usuário " + nomeUsuario + " não existe.\n";
            return null;
        }

        // Calcula a distância euclidiana entre o usuário atual e todos os outros usuários.
        // O usuário mais próximo do usuário atual será o que mais provavelmente terá gostos semelhantes.
        int[] distancias = new int[10];
        for (int i = 0; i < 10; i++) {
            distancias[i] = 0;
            for (int j = 1; j < 20; j++) {
                if (matriz[indiceUsuario][j] != 0 && matriz[i][j] != 0) {
                    distancias[i] += Math.pow(matriz[indiceUsuario][j] - matriz[i][j], 2);
                }
            }
        }

        // Encontra o usuário mais próximo do usuário atual.
        int indiceUsuarioMaisProximo = -1;
        int distanciaMinima = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            if (distancias[i] < distanciaMinima) {
                distanciaMinima = distancias[i];
                indiceUsuarioMaisProximo = i;
            }
        }

        // Retorna o filme com a maior nota do usuário mais próximo.
        int notaMaior = 0;
        for (int i = 1; i < 20; i++) {
            if (matriz[indiceUsuarioMaisProximo][i] > notaMaior) {
                notaMaior = matriz[indiceUsuarioMaisProximo][i];
                mensagem = filmes[i];
            }
        }

        return mensagem;
    }
    
    public String listaAvaliacoes(String nomeUsuario) {
        String mensagem = "\n";
        int indiceUsuario = -1;
        for (int i = 0; i < 10; i++) {
            if (users[i].equals(nomeUsuario)) {
                indiceUsuario = i;
                break;
            }
        }

        if (indiceUsuario == -1) {
            mensagem = "Usuário " + nomeUsuario + " não existe.\n";
            return null;
        }

        for (int i = 1; i < 20; i++) {
            if (matriz[indiceUsuario][i] != 0) {
                mensagem = mensagem + users[indiceUsuario] + " avaliou o filme '" + filmes[i] + "' com " + matriz[indiceUsuario][i] + ";\n";
            }
        }

        return mensagem;
    }
}
