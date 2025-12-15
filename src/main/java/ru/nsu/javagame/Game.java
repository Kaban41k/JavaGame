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
    private final Canvas canvas = new Canvas(800, 600);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();

    GameManager gameManager = new GameManager();
    GameWindow gameWindow = new GameWindow();
    GameController gameController = new GameController();
    
    float TPS = 100;

    @Override
    public void start(Stage stage) {
        gameWindow.init(stage);
        GameController.setupKeyboardControls(gameWindow.scene);

        setupKeyboardHandling(gameWindow.scene);

        Player player = new Player(new Vector(0, 0), new Vector(100, 100), new HPManager(100), new Gun(Direction.RIGHT, 1), gameManager);
        player.spriteManager.setSprite(gameWindow.getSprite("gnome"));

        gameWindow.objects.add(player);

        Enemy enemy = new Enemy(new Vector(300, 200), new Vector(400, 300), 10, new Vector(-0.1, 0.1));
        enemy.spriteManager.setSprite(gameWindow.getSprite("enemy"));

        gameWindow.objects.add(enemy);

        ArrayList<Entity> enemies = new ArrayList<>();
        enemies.add(enemy);

        GameManager.initializeGame(player, enemies);
        startRegularGameUpdates();
    }

    private void setupKeyboardHandling(Scene scene) {
        KeyListener KeyboardState = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                Keyboard.keyPressed(keyEvent.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                Keyboard.keyReleased(keyEvent.getKeyChar());
            }
        };
    }

    public void startRegularGameUpdates() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds((double) 1 / TPS), event -> {
                    GameController.keyEvent();
                    GameManager.tick();
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
    }
}
