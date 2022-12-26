package com.nixe.pinup.view.home;
import com.nixe.pinup.socket.ListingSOCKET;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import javafx.application.Platform;
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
    public void sair(){

    }
    public void abrirConexao(){
        Task task = new Task<ListingSOCKET>() {
            @Override

            public ListingSOCKET call() throws IOException{
                try{

                    server = new ListingSOCKET(1724);

                    enabledisableAbrirConexao(true);

                    String status = String.format(
                            "Conexão estabelecida \n ip: %s:%d",
                            server.getHostAddress(),
                            server.getLocalPort()
                    );

                    System.out.println(status);
                    statusConection(status);

                    server.start();

                }catch (Exception ex){

                    System.out.println("error: "+ ex.getCause());
                    System.out.println("error: "+ ex.getMessage());
                    enabledisableAbrirConexao(false);

                }
                return server;
            }

            @Override
            public void succeeded(){

            }
        };

        System.out.println(task.getState().toString());

        new Thread(task).start();


    }

    public void statusConection(String st){
        status.setText("st");
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
}