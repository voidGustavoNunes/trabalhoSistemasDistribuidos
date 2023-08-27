package comunicacaoUDP;

import java.net.*;

public class ClienteUDP {

    private static final String SERVER_IP = "127.0.0.1"; // IP do servidor (localhost no exemplo)
    private static final int SERVER_PORT = 6789; // Porta do servidor

    public static String enviarRequisicao(String requisicao) throws Exception {

        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName(SERVER_IP);;

        byte[] requestData = requisicao.getBytes();
        DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, serverAddress, SERVER_PORT);
        socket.send(requestPacket);

        byte[] buffer = new byte[1024];
        DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(responsePacket);

        String resposta = new String(responsePacket.getData(), 0, responsePacket.getLength());
//        System.out.println("Resposta do servidor: " + resposta);

        return resposta;
    }
}
