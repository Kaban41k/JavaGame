package ru.nsu.javagame;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        GameWindow game = new GameWindow();

        game.startRegularUpdates();

        game.Init(stage);
    }
}
