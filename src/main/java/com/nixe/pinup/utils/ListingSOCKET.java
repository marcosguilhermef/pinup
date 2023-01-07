package com.nixe.pinup.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ListingSOCKET{
    private String Hostname;
    private int LocalPort;
    private String HostAddress;
    ServerSocket server;
    InetAddress inet;

    public ListingSOCKET(int port) throws IOException {
        server = new ServerSocket(port);
        inet = server.getInetAddress();

        Hostname = inet.getHostAddress();
        LocalPort = server.getLocalPort();
        HostAddress = inet.getHostName();

        System.out.println("variaveis setadas");


    }

    public void start() throws Exception {

        try {
            Socket cliente = server.accept();

            System.out.println("Cliente conectado do IP " + cliente.getInetAddress().
                    getHostAddress());
            Scanner entrada = new Scanner(cliente.getInputStream());

            while (entrada.hasNextLine()) {
                System.out.println(entrada.nextLine());
                System.out.println("audio");
                new PlayRinging().audio();
            }
            System.out.println("CONEXÃO FECHADA");
            entrada.close();
            server.close();
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public String getHostAddress() {
        return HostAddress;
    }

    public String getHostname() {
        return Hostname;
    }
    public int getLocalPort(){
        return LocalPort;
    }

}
