package com.nixe.pinup.view.home;
import com.nixe.pinup.utils.CallSOCKET;
import com.nixe.pinup.utils.ListingInCackground;
import com.nixe.pinup.utils.ListingSOCKET;
import com.nixe.pinup.utils.ServiceListing;
import javafx.concurrent.Service;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Home{


    @FXML
    MenuItem sair, sobre;
    @FXML
    Button abrir_conexao, sinalizar, ocultar;

    ListingSOCKET server;
    @FXML
    Label status;
    @FXML
    TextField port, ip;
    @FXML
    TextArea texto;
    @FXML
    ComboBox<String> sons;

    public static String toque = "cachorro";

    public void initialize() {
        sons.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Home.this.toque = sons.getSelectionModel().getSelectedItem();
            }
        });

        sobre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Clicou");
                Alert alert = new Alert(
                        Alert.AlertType.NONE
                        );

                alert.setTitle("Aviso!");
                WebView webView = new WebView();
                webView.setPrefSize(500,125);
                webView.getEngine().loadContent("<body bgcolor=\"#f6f6f6\">" +
                        "<p align=\"justified\">\n" +
                        "Este aplicativo não pode ser distribuído comercialmente sem autorização previa. Para mais informações sobre termos e licenças, entre em contato por: <br>" +
                        "Email: <a href=\"contato@mgjobs.cf\">contato@mgjobs.cf</a><br>" +
                        "Github: <a href=\"https://github.com/marcosguilhermef\">marcosguilhermef</a>" +
                        "</p></body>");


                Window window = alert.getDialogPane().getScene().getWindow();
                window.setOnCloseRequest(e -> alert.hide());

                alert.getDialogPane().setContent(webView);;
                alert.showAndWait();

                //alert.show();
            }
        });

    }
    public void sair(){

    }
    public void abrirConexao(){
        Service<ListingSOCKET> task = new ServiceListing(30010);
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

        task.start();

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

    public void escolherSons(){

    }
}