package ru.nsu.javagame;

import javafx.scene.Scene;

public class GameController {
    public static void setupKeyboardControls(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch(event.getCode()) {
                case W:
                    Keyboard.keyPressed('W');
                    break;
                case S:
                    Keyboard.keyPressed('S');
                    break;
                case A:
                    Keyboard.keyPressed('A');
                    break;
                case D:
                    Keyboard.keyPressed('D');
                    break;
                case SPACE:
                    Keyboard.keyPressed(' ');
                    break;
                default:
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch(event.getCode()) {
                case W:
                    Keyboard.keyReleased('W');
                    break;
                case S:
                    Keyboard.keyReleased('S');
                    break;
                case A:
                    Keyboard.keyReleased('A');
                    break;
                case D:
                    Keyboard.keyReleased('D');
                    break;
                case SPACE:
                    Keyboard.keyReleased(' ');
                    break;
                default:
                    break;
            }
        });
    }

    public static void keyEvent() {
        GameManager.player.spriteManager.setSprite(GameWindow.getSprite("gnomeStay"));

        if (Keyboard.isKeyPressed('W')) {
            GameManager.player.move(new Vector(0, -2));
            GameManager.player.spriteManager.setSprite(GameWindow.getSprite("gnomeW"));
        }

        if (Keyboard.isKeyPressed('A')) {
            GameManager.player.move(new Vector(-2, 0));
            GameManager.player.spriteManager.setSprite(GameWindow.getSprite("gnomeA"));
        }

        if (Keyboard.isKeyPressed('S')) {
            GameManager.player.move(new Vector(0, 2));
            GameManager.player.spriteManager.setSprite(GameWindow.getSprite("gnomeS"));
        }

        if (Keyboard.isKeyPressed('D')) {
            GameManager.player.move(new Vector(2, 0));
            GameManager.player.spriteManager.setSprite(GameWindow.getSprite("gnomeD"));
        }

        if (Keyboard.isKeyPressed(' ')) {
            GameManager.player.fire();
        }
    }
}
