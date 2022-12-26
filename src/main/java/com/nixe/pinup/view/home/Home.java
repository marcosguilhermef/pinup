package com.nixe.pinup.view.home;
import com.nixe.pinup.socket.CallSOCKET;
import com.nixe.pinup.socket.ListingSOCKET;
import com.nixe.pinup.socket.ServiceListing;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Home {

    @FXML
    TextField ip;
    @FXML
    Button sinalizar;
    @FXML
    Menu sair;
    @FXML
    Menu sobre;
    @FXML
    Button abrir_conexao;

    ListingSOCKET server;
    @FXML
    Label status;
    @FXML
    TextField port;
    @FXML
    TextArea texto;
    public void sair(){

    }
    public void abrirConexao(){
        Service<ListingSOCKET> task = new ServiceListing(9521);
        task.start();

        task.setOnSucceeded((e) -> {
            enabledisableAbrirConexao(true);
            try {
                statusConection(String.format(
                        "Conexão estabelecida com sucesso:\n"+
                        "ip: %s:%d\n"+
                        "ip: " + InetAddress.getLocalHost().getHostAddress()+":%d"
                ,
                            task.getValue().getHostAddress(),
                            task.getValue().getLocalPort(),
                            task.getValue().getLocalPort()
                        )
                );
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                statusConection(String.format(
                                "Erro:\n"
                                ,
                                "Conexão não pode ser estabelecida.\n",
                                "As causas podem ser devido a porta, firewall ou permissões necessárias.\n"
                        )
                );
            }
            abrirConexao();
        } );

        task.setOnFailed((e) -> {
            statusConection(String.format(
                            "Erro:\n"
                            ,
                            "Conexão não pode ser estabelecida.\n",
                            "As causas podem ser devido a porta, firewall ou permissões necessárias.\n"
                    )
            );
        });


    }

    public void statusConection(String st){
        status.setText(st);
    }
    public void enabledisableAbrirConexao(Boolean b){
        System.out.println("CONEXAO FOI ABERTA COM SUCESSO!");
        abrir_conexao.setDisable(b);
    }

    public void mensagemErro(String causa, String erro){
        System.out.println(causa);
        System.out.println(erro);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(
                String.format(
                        "Infelizmente a coneção não pode ser estabelecida:\n%s\n%s",
                        causa,
                        erro
                )
        );
        alert.show();
    }
    public void sobre(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sobre");
        alert.setHeaderText("Todos os créditos para https://github.com/marcosguilhermef");
        alert.setContentText("Este software não pode ser distribuído comercialmente sem permissão expressa. \n" +
                "Email: contato@mgjobs.cf");
        alert.show();
    }

    public void chamar() throws IOException {
            CallSOCKET client = new CallSOCKET(
                    ip.getText(),
                    Integer.valueOf(port.getText())
            );

    }
}