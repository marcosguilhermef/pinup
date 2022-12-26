package com.nixe.pinup.socket;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;

public class ServiceListing extends Service<ListingSOCKET>{
    int port;
    ListingSOCKET server;
    public ServiceListing(int port){
        this.port = port;
    }
    @Override
    protected Task<ListingSOCKET> createTask() {
        return new Task() {
            @Override
            protected ListingSOCKET call() throws Exception {
                server = new ListingSOCKET(port);
                server.start();
                return server;
            }
        };
    }


    public ListingSOCKET getListing(){
        return server;
    }

    @Override
    public void succeeded(){
        System.out.println("succeeded");
    }
    @Override
    protected void failed() {
        System.out.println("failed");
    }
    @Override
    protected void ready() {
        System.out.println("ready");
    }

    @Override
    protected void scheduled() {
        System.out.println("scheduled");
    }

    @Override
    protected void running() {
        System.out.println("running");
    }
}
