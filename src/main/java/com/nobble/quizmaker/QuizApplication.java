package com.nobble.quizmaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class QuizApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(QuizApplication.class.getResource("quizGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Quiz Maker");
        stage.setWidth(600);
        stage.setHeight(350);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}