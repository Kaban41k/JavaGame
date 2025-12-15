package ru.nsu.javagame;

import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

public class Keyboard {
    public static final Set<Character> pressedKeys = new HashSet<Character>();

    public static void keyPressed(char key) {
        pressedKeys.add(key);
    }

    public static void keyReleased(char key) {
        pressedKeys.remove(key);
    }

    public static boolean isKeyPressed(char key) {
        return pressedKeys.contains(key);
    }


}
