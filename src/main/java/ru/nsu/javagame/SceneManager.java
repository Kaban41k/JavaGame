package ru.nsu.javagame;

import java.util.ArrayList;

public class SceneManager {
    public static ArrayList<Entity> level1() {
        ArrayList<Entity> result = new ArrayList<>();

        Player player = new Player(new Vector(0, GameWindow.mainPanelHeight), new Vector(100, GameWindow.mainPanelHeight + 50), new HPManager(100), new Gun(Direction.RIGHT, (int) (Game.TPS / 10), 3));
        player.spriteManager.setSprite(GameWindow.getSprite("gnomeStay"));

        GameWindow.objects.add(player);
        result.add(player);

        Enemy enemy = new Enemy(new Vector(300, 200), new Vector(350, 250), new Vector(0, 1));
        enemy.spriteManager.setSprite(GameWindow.getSprite("enemy"));

        GameWindow.objects.add(enemy);
        GameManager.enemyList.add(enemy);
        result.add(enemy);

        enemy = new Enemy(new Vector(400, 100), new Vector(450, 150), new Vector(0, -1));
        enemy.spriteManager.setSprite(GameWindow.getSprite("enemy"));

        GameWindow.objects.add(enemy);
        GameManager.enemyList.add(enemy);
        result.add(enemy);

        enemy = new Enemy(new Vector(500, 400), new Vector(550, 450), new Vector(0, 1));
        enemy.spriteManager.setSprite(GameWindow.getSprite("enemy"));

        GameWindow.objects.add(enemy);
        GameManager.enemyList.add(enemy);
        result.add(enemy);

        return result;
    }
}
