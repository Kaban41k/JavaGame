package ru.nsu.javagame;

import java.util.ArrayList;

public class SceneManager {
    public static ArrayList<Entity> level1() {
        ArrayList<Entity> result = new ArrayList<>();

        Player player = new Player(new Vector(0, 0), new Vector(100, 50), new HPManager(100), new Gun(Direction.RIGHT, 1));
        player.spriteManager.setSprite(GameWindow.getSprite("gnome"));

        GameWindow.objects.add(player);
        result.add(player);

        Enemy enemy = new Enemy(new Vector(300, 200), new Vector(350, 250), 10, new Vector(-0.1, 0.1));
        enemy.spriteManager.setSprite(GameWindow.getSprite("enemy"));

        GameWindow.objects.add(enemy);
        result.add(enemy);

        return result;
    }
}
