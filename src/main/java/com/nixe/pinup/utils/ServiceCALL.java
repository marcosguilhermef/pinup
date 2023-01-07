package com.nixe.pinup.utils;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ServiceCALL extends Service<CallSOCKET>{

    private String ip;
    private int port;
    private String text;

    CallSOCKET client;
    public ServiceCALL(String ip, int port){
        ip = this.ip;
        port = this.port;
    }

    public ServiceCALL(String ip, int port, String text){
        ip   = this.ip;
        port = this.port;
        text = this.text;
    }
    @Override
    protected Task<CallSOCKET> createTask() {
        return new Task() {
            @Override
            protected CallSOCKET call() throws Exception {
                if(text.isEmpty()){
                    client = new CallSOCKET(
                            ip,
                            port
                    );
                }else{
                    client = new CallSOCKET(
                            ip,
                            port,
                            text
                    );
                }

                return client;
            }
        };
    }
}
