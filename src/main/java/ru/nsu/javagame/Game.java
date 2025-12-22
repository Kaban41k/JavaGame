package ru.nsu.javagame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game extends Application {
    public static final float TPS = 100;
    public static final float FPS = 75;

    @Override
    public void start(Stage stage) {
        GameWindow.init(stage);
        GameController.setupKeyboardControls(GameWindow.scene);

        ArrayList<Entity> entities = SceneManager.level1();
        Entity player = entities.getFirst();
        entities.removeFirst();

        GameManager.initializeGame((Player) player, entities);
        startRegularGameUpdates();
    }

    public void startRegularGameUpdates() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds((double) 1 / TPS), event -> {
                    if (!GameManager.isGameOver()) {
                        GameController.keyEvent();
                        GameManager.tick();
                    }
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
    }
}
