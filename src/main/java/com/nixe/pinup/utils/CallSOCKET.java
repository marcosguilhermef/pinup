package com.nixe.pinup.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class CallSOCKET {
    private Socket socket;
    public CallSOCKET(String address, int port) throws IOException {
        Socket cliente = new Socket(address, port);
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        saida.println("Chamando");
        cliente.close();
    }

    public CallSOCKET(String address, int port, String text) throws IOException {
        Socket cliente = new Socket(address, port);
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        saida.println(text);
    }
}
