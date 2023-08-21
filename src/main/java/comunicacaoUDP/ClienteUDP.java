package comunicacaoUDP;

import java.net.*;
import java.io.*;

public class ClienteUDP {

    private String nomeDNS;
    private int serverPort;
    private byte[] meuIP;

    public ClienteUDP() {
        try {
            InetAddress endereco = InetAddress.getLocalHost();
            nomeDNS = endereco.getHostName();
            meuIP = endereco.getAddress();
        } catch (UnknownHostException e) {
            System.out.println("Host Desconhecido: " + e.getMessage());
        }

        serverPort = 6789;
    }

    public String enviaMensagem(String mensagem) {
        DatagramSocket aSocket = null;
        String resposta = "";

        try {
            aSocket = new DatagramSocket();
            byte[] m = mensagem.getBytes();
            InetAddress aHost = InetAddress.getByName(nomeDNS);

            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[600];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);

            resposta = new String(reply.getData());
            resposta = resposta + "\n";
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input Output: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();
        }
        return resposta;
    }

    public ClienteUDP(String nomeDNSServidor) {
        nomeDNS = "SIGEX-B06"; // Introduza o nome do computador em que seu servidor for executado
        meuIP = null;
        serverPort = 6789;
    }

    public String getNomeDNS() {
        return nomeDNS;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getMeuIP() {
        String s = new String(meuIP);
        return s;
    }

    public static void main(String[] args) {
        ClienteUDP cliente = new ClienteUDP();

        // Requisição 1: Solicitar título de filme não avaliado
        String resposta1 = cliente.enviaMensagem("1;Edson");
        System.out.println("Resposta 1: " + resposta1);

        // Requisição 2: Registrar avaliação do usuário
        String resposta2 = cliente.enviaMensagem("2;Edson;Homem de Ferro;3");
        System.out.println("Resposta 2: " + resposta2);

        // Requisição 3: Solicitar recomendação de filme
        String resposta3 = cliente.enviaMensagem("3;Edson");
        System.out.println("Resposta 3: " + resposta3);

        // Requisição 4: Solicitar lista de avaliações do usuário
        String resposta4 = cliente.enviaMensagem("4;Edson");
        System.out.println("Resposta 4: " + resposta4);
    }
}
