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
    private static final Canvas canvas = new Canvas(800, 600);
    private static final GraphicsContext gc = canvas.getGraphicsContext2D();
    public static Scene scene;

    private static final Map<String, Image> sprites = new HashMap<>();;
    private static final Map<String, ArrayList<Image>> anims = new HashMap<>();;
    public static final ArrayList<Entity> objects = new ArrayList<>();
    private static SpriteManager background = new SpriteManager();

    double x = 0;
    double y = 0;

    public static void init(Stage stage) {
        gc.setImageSmoothing(false);

        initSprites();
        initAnimations();

        setupBackground();
        startRegularUpdates();

        Pane root = new Pane(canvas);
        scene = new Scene(root);

        stage.setTitle("JavaGame");
        stage.setScene(scene);
        stage.show();
    }

    private static Image getImage(String name) {
        return new Image(Objects.requireNonNull(GameWindow.class.getResourceAsStream(name)));
    }

    private static void initSprites() {
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

    public static Image getSprite(String name) {
        return sprites.get(name);
    }

    private static void initAnimations() {
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

    public ArrayList<Image> getAnim(String name) {
        return anims.get(name);
    }

    public void setBackground(Image sprite) {
        background.setSprite(sprite);
    }

    public static void setupBackground() {
        background.setSprite(getSprite("background"));
        background.height = (int) (canvas.getHeight() * 2);
        background.width = (int) (canvas.getWidth() * 2);
    }

    public static void startRegularUpdates() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds((double) 1 / Game.FPS), event -> {
                    updateCanvas();
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
    }

    private static void updateCanvas() {
        gc.clearRect(0, 0, 800, 600);
        drawObjects();
    }

    private static void drawObjects() {
        gc.drawImage(background.getSprite(),
                background.x, background.y,
                background.width, background.height);

        for (Entity obj : objects) {
            obj.spriteManager.x = obj.getTopLeftOnScreen().x;
            obj.spriteManager.y = obj.getTopLeftOnScreen().y;

            obj.spriteManager.width = (int) (obj.getBottomRight().x - obj.getTopLeft().x);
            obj.spriteManager.height = (int) (obj.getBottomRight().y - obj.getTopLeft().y);

            gc.drawImage(obj.spriteManager.getSprite(),
                    obj.spriteManager.x, obj.spriteManager.y,
                    obj.spriteManager.width, obj.spriteManager.height);
        }
    }
}