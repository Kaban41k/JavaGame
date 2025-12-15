package ru.nsu.javagame;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    static ArrayList<Entity> entityList = new ArrayList<>();
    static ArrayList<Bullet> bulletList = new ArrayList<>();
    public static Player player;
    private static HPManager playerHealth;
    private CollisionManager collisionManager;
    private static boolean gameOver = false;
    private static int enemyDamage = 1;

    public GameManager() {
        playerHealth = new HPManager(100);
        collisionManager = new CollisionManager(entityList);
    }

    public void setEnemyDamage(int damage) {
        enemyDamage = damage;
    }

    public static void initializeGame(Player plr, List<Entity> enemies) {
        player = plr;
        entityList.addAll(enemies);
    }

    public static void tick() {
        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);

            bullet.move(new Vector(1, 0));
            for (Entity entity : entityList) {
                if (entity.checkIntersects(bullet)) {
                    entityList.remove(entity);
                    bulletList.remove(bullet);
                    break;
                }
            }
        }

        for (Entity entity : entityList) {
            if (entity instanceof Enemy) {
                Vector movVect = entity.getMovVect();
                entity.move(movVect);
            }
        }

        //handleCollisions();
        //updateScore();
        //checkWinLose();
    }

    private void handleCollisions() {
        collisionManager.checkForCollisions();
    }

    private static void _updateScore(int damage) {
        if (playerHealth.getHP() > damage) {
            playerHealth.damage(damage);
        }
        else {
            gameOver = true;
        }
    }

    private static void updateScore() {
        for (Entity entity : entityList) {
            if (player.checkIntersects(entity)) {
                _updateScore(enemyDamage);
            }
        }
    }

    private static void checkWinLose() {
        if (playerHealth.getHP() <= 0) {
            gameOver = true;
        }
    }

    public static void addEntity(Entity entity) {
        entityList.add(entity);
    }

    public boolean isGameOver(){
        return gameOver;
    }
}
