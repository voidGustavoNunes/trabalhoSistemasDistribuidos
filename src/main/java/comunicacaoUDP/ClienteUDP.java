/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comunicacaoUDP;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Gustavo
 */
public class ClienteUDP {
    private String nomeDNS;
    private int serverPort;
    private byte[] meuIP;
    
    
    public ClienteUDP(){
        try{

            InetAddress endereco = InetAddress.getLocalHost();

            nomeDNS = endereco.getHostName();

            meuIP = endereco.getAddress();


        }catch (UnknownHostException e){
            System.out.println("Host Desconhecido: "+e.getMessage( ));
        }
        serverPort = 6789;
    }
    
    
    public ClienteUDP(String nomeDNSServidor){
        //Metodo construtor para quando SERVIDOR e CLIENTE rodarem em
        //maquinas diferentes
    
        nomeDNS = "SIGEX-B06"; //Introduza o nome do computador em que seu 
                                //servidor for executado
        
        meuIP = null;
        serverPort = 6789;
    }
    
    public String getNomeDNS(){
        return nomeDNS;
    
    }
    public int getServerPort(){
        return serverPort;
    
    }
    public String getMeuIP(){
        String s = new String(meuIP);
        return s;
    
    }
    
    
}
