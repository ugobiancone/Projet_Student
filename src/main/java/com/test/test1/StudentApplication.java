package com.test.test1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //System.out.println(System.getProperty("user.dir")) ;
        //System.out.println(System.getProperty("java.class.path"));
        FXMLLoader fxmlLoader = new FXMLLoader(StudentApplication.class.getResource("strudent_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}