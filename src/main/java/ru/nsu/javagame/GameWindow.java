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
    private final int FPS = 100;

    private final Canvas canvas = new Canvas(800, 600);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();

    private final Map<String, Image> sprites = new HashMap<>();;
    private final Map<String, ArrayList<Image>> anims = new HashMap<>();;
    public final ArrayList<Entity> objects = new ArrayList<>();

    double x = 0;
    double y = 0;

    public void init(Stage stage) {
        startRegularUpdates();
        gc.setImageSmoothing(false);

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
        sprites.put("background", getImage("/Background.png"));
        sprites.put("gnome", getImage("/GnomePlane.png"));
        sprites.put("enemy", getImage("/Enemy1.png"));

        sprites.put("gnomeGo1", getImage("/anims/GnomePlane1.png"));
        sprites.put("gnomeGo2", getImage("/anims/GnomePlane2.png"));
        sprites.put("gnomeGo3", getImage("/anims/GnomePlane3.png"));

        sprites.put("pig", getImage("/pig.png"));
        sprites.put("pigBack", getImage("/anims/pigBack.png"));
        sprites.put("pigBack1", getImage("/anims/pigBack1.png"));
    }

    public Image getSprite(String name) {
        return sprites.get(name);
    }

    private void initAnimations() {
        ArrayList<Image> pigBack = new ArrayList<>();
        pigBack.add(sprites.get("pigBack"));
        pigBack.add(sprites.get("pigBack1"));
        anims.put("pigBack", pigBack);

        ArrayList<Image> gnomeGo = new ArrayList<>();
        gnomeGo.add(sprites.get("gnomeGo1"));
        gnomeGo.add(sprites.get("gnomeGo2"));
        gnomeGo.add(sprites.get("gnomeGo3"));
        anims.put("gnomeGo", gnomeGo);
    }

    public void startRegularUpdates() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds((double) 1 / FPS), event -> {
                    updateCanvas();
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
    }

    private void updateCanvas() {
        gc.clearRect(0, 0, 800, 600);
        drawObjects();
    }

    int frame = 0;
    boolean flag = false;

    private void gameUpdate() {
        if (frame % (FPS * 8) == 0) {
            objects.get(2).spriteManager.startAnimation(anims.get("pigBack"), false, FPS);
        }

        if (frame == FPS * 2) {
            objects.get(1).spriteManager.startAnimation(anims.get("gnomeGo"), true, FPS / 10);
        }

        if (flag)
            objects.get(1).spriteManager.y += 1;
        else
            objects.get(1).spriteManager.y -= 1;

        if (objects.get(1).spriteManager.y < 0) {
            flag = true;
        }

        if (objects.get(1).spriteManager.y > canvas.getHeight() - objects.get(1).spriteManager.height) {
            flag = false;
        }


        frame++;
    }

    private void drawObjects() {
        for (Entity obj : objects) {
            obj.spriteManager.x = obj.getTopLeftOnScreen().x;
            obj.spriteManager.y = obj.getTopLeftOnScreen().y;

            obj.spriteManager.width = (int) (obj.getBottomRightOnScreen().x - obj.getTopLeftOnScreen().x);
            obj.spriteManager.height = (int) (obj.getBottomRightOnScreen().y - obj.getTopLeftOnScreen().y);

            gc.drawImage(obj.spriteManager.getSprite(),
                    obj.spriteManager.x, obj.spriteManager.y,
                    obj.spriteManager.width, obj.spriteManager.height);
        }
    }
}