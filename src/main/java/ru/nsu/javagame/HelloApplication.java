package ru.nsu.javagame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;

public class HelloApplication extends Application {
    private final Canvas canvas = new Canvas(800, 600);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();

    private final ArrayList<SpriteManager> objects = new ArrayList<>();

    int x = 0;
    int y = 0;

    @Override
    public void start(Stage stage) {
        startRegularUpdates();

        Init(stage);
    }

    private void Init(Stage stage) {
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        stage.setTitle("JavaGame");
        stage.setScene(scene);
        stage.show();
    }

    private void startRegularUpdates() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    updateCanvas();
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
    }

    private void updateCanvas() {
        if (objects.size() < 10) {
            SpriteManager obj = new SpriteManager();
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pig.png")));
            obj.setSprite(image);
            obj.x = x;
            obj.y = y;
            x += 30;
            y += 20;

            objects.add(obj);
        }

        drawObjects();
    }

    private void drawObjects() {
        for (SpriteManager obj : objects)
            gc.drawImage(obj.getSprite(), obj.x, obj.y, obj.width, obj.height);
    }
}
