package ru.nsu.javagame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Game extends Application {
    GameManager gameManager = new GameManager();

    float TPS = 100;

    @Override
    public void start(Stage stage) {
        GameWindow gameWindow = new GameWindow();

        gameWindow.init(stage);

        Player player = new Player(new Vector(0, 0), new Vector(100, 100), new HPManager(100), new Gun(Direction.RIGHT, 1), gameManager);
        player.spriteManager.setSprite(gameWindow.getSprite("gnome"));
        player.spriteManager.x = 0;
        player.spriteManager.y = 0;

        gameWindow.objects.add(player);

        gameManager.initializeGame(player, new ArrayList<>());
        startRegularGameUpdates();
    }

    public void startRegularGameUpdates() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds((double) 1 / TPS), event -> {
                    gameManager.player.move(new Vector(1, 1));
                    gameManager.tick();
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
    }
}
