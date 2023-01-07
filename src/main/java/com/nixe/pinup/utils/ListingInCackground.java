package com.nixe.pinup.utils;

import java.io.IOException;

public class ListingInCackground extends Thread{
    private ListingSOCKET socket;
    public ListingInCackground() throws IOException {
        socket = new ListingSOCKET(30010);
    }
    @Override
    public void run(){
        try {
            socket.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
