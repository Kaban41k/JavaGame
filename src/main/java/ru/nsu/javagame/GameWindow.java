package ru.nsu.javagame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameWindow {
    public static final Canvas canvas = new Canvas(800, 600);
    private static final GraphicsContext gc = canvas.getGraphicsContext2D();
    public static Scene scene;
    public static Pane root;

    private static final Map<String, Image> sprites = new HashMap<>();;
    private static final Map<String, ArrayList<Image>> anims = new HashMap<>();

    private static final ArrayList<SpriteManager> background = new ArrayList<>();
    public static final ArrayList<Entity> objects = new ArrayList<>();
    private static final ArrayList<SpriteManager> ui = new ArrayList<>();

    public static final int mainPanelHeight = 50;

    static Label healthLabel;

    double x = 0;
    double y = 0;

    public static void init(Stage stage) {
        gc.setImageSmoothing(false);

        initSprites();
        initAnimations();

        root = new Pane(canvas);
        scene = new Scene(root);

        setupBackground();
        setupUI();
        startRegularUpdates();

        stage.setTitle("JavaGame");
        stage.setScene(scene);
        stage.show();
    }

    private static Image getImage(String name) {
        return new Image(Objects.requireNonNull(GameWindow.class.getResourceAsStream(name)));
    }

    private static void initSprites() {
        sprites.put("background", getImage("/background/Background.png"));
        sprites.put("backgroundL1", getImage("/background/BackgroundL1.png"));
        sprites.put("backgroundL2", getImage("/background/BackgroundL2.png"));

        sprites.put("gnomeStay", getImage("/gnomePlane/GnomePlaneStay.png"));
        sprites.put("gnomeW", getImage("/gnomePlane/GnomePlaneUp.png"));
        sprites.put("gnomeA", getImage("/gnomePlane/GnomePlaneBack.png"));
        sprites.put("gnomeD", getImage("/gnomePlane/GnomePlaneForward.png"));
        sprites.put("gnomeS", getImage("/gnomePlane/GnomePlaneDown.png"));

        sprites.put("enemy", getImage("/Enemy1.png"));

        sprites.put("gnomeGo1", getImage("/anims/GnomePlane1.png"));
        sprites.put("gnomeGo2", getImage("/anims/GnomePlane2.png"));
        sprites.put("gnomeGo3", getImage("/anims/GnomePlane3.png"));

        sprites.put("bullet", getImage("/Bullet.png"));

        sprites.put("pig", getImage("/pig.png"));
        sprites.put("pigBack", getImage("/anims/pigBack.png"));
        sprites.put("pigBack1", getImage("/anims/pigBack1.png"));

        sprites.put("UIPanel", getImage("/UIPanel.png"));
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
        background.getFirst().setSprite(sprite);
    }

    public static void setupBackground() {
        background.add(new SpriteManager());
        background.getFirst().setSprite(getSprite("background"));
        background.getFirst().width = (int) (canvas.getWidth() * 2);
        background.getFirst().height = (int) (canvas.getHeight() * 2);

        background.add(new SpriteManager());
        background.get(1).setSprite(getSprite("backgroundL1"));
        background.get(1).width = 3200;
        background.get(1).height = 150;
        background.get(1).y = canvas.getHeight() - background.get(1).height - 50;

        background.add(new SpriteManager());
        background.get(2).setSprite(getSprite("backgroundL2"));
        background.get(2).width = 3200;
        background.get(2).height = 100;
        background.get(2).y = canvas.getHeight() - background.get(2).height;
    }

    public static void setupUI() {
        ui.add(new SpriteManager());
        ui.getFirst().setSprite(getSprite("UIPanel"));
        ui.getFirst().width = (int) canvas.getWidth();
        ui.getFirst().height = mainPanelHeight;

        healthLabel = new Label("HP: 100");
        healthLabel.setLayoutX(80);
        healthLabel.setLayoutY(15);
        healthLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        root.getChildren().add(healthLabel);
    }

    public static void moveBackground() {
        background.get(1).x -= 2;

        if (background.get(1).x <= -2400) {
            background.get(1).x = 0;
        }


        background.get(2).x -= 4;

        if (background.get(2).x <= -2400) {
            background.get(2).x = 0;
        }
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
        for (SpriteManager back : background) {
            gc.drawImage(back.getSprite(),
                    back.x, back.y,
                    back.width, back.height);
        }

        for (Entity obj : objects) {
            obj.spriteManager.x = obj.getTopLeft().x;
            obj.spriteManager.y = obj.getTopLeft().y;

            obj.spriteManager.width = (int) (obj.getBottomRight().x - obj.getTopLeft().x);
            obj.spriteManager.height = (int) (obj.getBottomRight().y - obj.getTopLeft().y);

            gc.drawImage(obj.spriteManager.getSprite(),
                    obj.spriteManager.x, obj.spriteManager.y,
                    obj.spriteManager.width, obj.spriteManager.height);
        }

        for (SpriteManager panel : ui) {
            gc.drawImage(panel.getSprite(),
                    panel.x, panel.y,
                    panel.width, panel.height);
        }

        healthLabel.setText("HP: " + GameManager.player.getHpManager().getHP());
    }
}