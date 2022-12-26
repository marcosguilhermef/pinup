package com.nixe.pinup.view.home;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    @Override
    public void start(Stage palco) throws Exception {
        Parent fxmlParent = FXMLLoader.load(getClass().getResource("/view/home/home_layout.fxml"));
        palco.setScene(new Scene(fxmlParent));
        palco.setTitle("pinup");
        palco.getIcons().add(new Image("/icons/pinup.png"));
        palco.show();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }
}
