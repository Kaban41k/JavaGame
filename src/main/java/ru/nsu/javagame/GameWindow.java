package ru.nsu.javagame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameWindow {
    private final float FPS = 60;

    private final Canvas canvas = new Canvas(800, 600);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();

    private final Map<String, Image> sprites = new HashMap<>();;
    private final Map<String, ArrayList<Image>> anims = new HashMap<>();;
    private final ArrayList<SpriteManager> objects = new ArrayList<>();

    int x = 0;
    int y = 0;

    public void Init(Stage stage) {
        initSprites();
        initAnimations();

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        stage.setTitle("JavaGame");
        stage.setScene(scene);
        stage.show();
    }

    private Image getImage(String name) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(name)));
    }

    private void initSprites() {
        sprites.put("pig", getImage("/pig.png"));
        sprites.put("pigBack", getImage("/anims/pigBack.png"));
        sprites.put("pigBack1", getImage("/anims/pigBack1.png"));
    }

    private void initAnimations() {
        ArrayList<Image> pigBack = new ArrayList<>();
        for (int i = 0; i < FPS; i++)
            pigBack.add(sprites.get("pigBack"));

        for (int i = 0; i < FPS; i++)
            pigBack.add(sprites.get("pigBack1"));

        anims.put("pigBack", pigBack);
    }

    public void startRegularUpdates() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1/FPS), event -> {
                    updateCanvas();
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
    }

    private void updateCanvas() {
        gameUpdate();

        gc.clearRect(0, 0, 800, 600);
        drawObjects();
    }

    int frame = 0;

    private void gameUpdate() {
        if (objects.size() < 3) {
            SpriteManager obj = new SpriteManager();
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pig.png")));
            obj.setSprite(image);
            obj.x = x;
            obj.y = y;
            x += 30;
            y += 20;

            objects.add(obj);
        } else {
            if (frame == FPS * 2) {
                objects.get(2).startAnimation(anims.get("pigBack"), false);
            }

            objects.get(2).x += 1;
        }


        frame++;
    }

    private void drawObjects() {
        for (SpriteManager obj : objects)
            gc.drawImage(obj.getSprite(), obj.x, obj.y, obj.width, obj.height);
    }
}