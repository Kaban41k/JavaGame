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
            }
        });
    }

    public static void keyEvent() {
        if (Keyboard.isKeyPressed('W')) {
            GameManager.player.move(new Vector(0, -1));
        }

        if (Keyboard.isKeyPressed('A')) {
            GameManager.player.move(new Vector(-1, 0));
        }

        if (Keyboard.isKeyPressed('S')) {
            GameManager.player.move(new Vector(0, 1));
        }

        if (Keyboard.isKeyPressed('D')) {
            GameManager.player.move(new Vector(1, 0));
        }
    }
}
