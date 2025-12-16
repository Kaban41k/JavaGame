package ru.nsu.javagame;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    static ArrayList<Entity> entityList = new ArrayList<>();
    static ArrayList<Enemy> enemyList = new ArrayList<>();
    static ArrayList<Bullet> bulletList = new ArrayList<>();
    public static Player player;
    private static HPManager playerHealth;
    private static CollisionManager collisionManager;
    private static boolean gameOver = false;
    private static int enemyDamage = 1;

    public void setEnemyDamage(int damage) {
        enemyDamage = damage;
    }

    public static void initializeGame(Player plr, List<Entity> enemies) {
        player = plr;
        entityList.addAll(enemies);
        collisionManager = new CollisionManager(entityList);
        playerHealth = new HPManager(100);
    }

    private static void moveAll() {
        for (Bullet bullet : bulletList) {
            bullet.move(new Vector(1, 0));
        }

        for (Entity entity : entityList) {
            if (entity instanceof Enemy) {
                Vector movVect = entity.getMovVect();
                entity.move(movVect);
            }
        }
    }

    private static void checkEnemyOrPlayerKill() {
        List<Bullet> bulletsToRemove = new ArrayList<>();
        List<Bullet> bulletsToRemovePlayer = new ArrayList<>();
        List<Entity> entitiesToRemove = new ArrayList<>();

        for (Bullet bullet : bulletList) {
            for (Entity entity : entityList) {
                if (entity.checkIntersects(bullet)) {
                    entitiesToRemove.add(entity);
                    bulletsToRemove.add(bullet);
                    break;
                }
            }
        }

        entityList.removeAll(entitiesToRemove);
        bulletList.removeAll(bulletsToRemove);

        for (Bullet bullet : bulletList) {
            if (player.checkIntersects(bullet)) {
                bulletsToRemovePlayer.add(bullet);
                if (playerHealth.getHP() <= bullet.getDamage()) {
                    playerHealth.damage(playerHealth.getHP() - bullet.getDamage());
                    gameOver = true;
                    bulletList.removeAll(bulletsToRemovePlayer);
                    break;
                }
            }
        }

        bulletList.removeAll(bulletsToRemovePlayer);
    }

    private static void fireAll() {
        //player.fire();
        player.gun.reload();
        for (Enemy enemy : enemyList) {
            enemy.fire();
            enemy.gun.reload();
        }
    }

    public static void tick() {
        moveAll();
        fireAll();
        checkEnemyOrPlayerKill();
        handleCollisions();
        updateScore();
        checkWinLose();
        GameWindow.moveBackground();
    }

    private static void handleCollisions() {
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
