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

        gameWindow.objects.add(player);

        Enemy enemy = new Enemy(new Vector(300, 200), new Vector(400, 300), 10, new Vector(-0.1, 0.1));
        enemy.spriteManager.setSprite(gameWindow.getSprite("enemy"));

        gameWindow.objects.add(enemy);

        ArrayList<Entity> enemies = new ArrayList<>();
        enemies.add(enemy);

        gameManager.initializeGame(player, enemies);
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
