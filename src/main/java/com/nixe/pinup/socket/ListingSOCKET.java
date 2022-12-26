package com.nixe.pinup.socket;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
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

        Socket cliente = server.accept();

        System.out.println("Cliente conectado do IP "+cliente.getInetAddress().
                getHostAddress());
        Scanner entrada = new Scanner(cliente.getInputStream());

        while(entrada.hasNextLine()){
            System.out.println(entrada.nextLine());
            System.out.println("audio");
            audio();
        }
        System.out.println("CONEXÃO FECHADA");
        entrada.close();
        server.close();
    }


    public void audio() throws Exception {
        URI audio = getClass().getResource("/audio/cachorro.wav").toURI();
        File f = new File(audio);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
        //clip.close();
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
