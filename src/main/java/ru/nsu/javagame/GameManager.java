package ru.nsu.javagame;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    static ArrayList<Entity> entityList = new ArrayList<>();
    static ArrayList<Enemy> enemyList = new ArrayList<>();
    static ArrayList<Bullet> bulletList = new ArrayList<>();
    public static Player player;
    private static CollisionManager collisionManager;
    private static boolean gameOver = false;
    private static boolean isWin = false;
    private static int enemyDamage = 2;
    private static int intersectDamage = 3;

    public void setEnemyDamage(int damage) {
        enemyDamage = damage;
    }

    public static void initializeGame(Player plr, List<Entity> enemies) {
        player = plr;
        entityList.addAll(enemies);
        collisionManager = new CollisionManager(entityList);
        player.hpMan = new HPManager(9);
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

        List<Entity> entitiesToRemovePlayer = new ArrayList<>();

        for (Bullet bullet : bulletList) {
            for (Entity entity : entityList) {
                if (bullet.isOwner(OwnerType.PLAYER) && entity.checkIntersects(bullet)) {
                    if (!entitiesToRemove.contains(entity)) {
                        entitiesToRemove.add(entity);
                    }
                    if (!bulletsToRemove.contains(bullet)) {
                        bulletsToRemove.add(bullet);
                    }

                }
            }
        }

        GameWindow.objects.removeAll(entitiesToRemove);
        GameWindow.objects.removeAll(bulletsToRemove);

        enemyList.removeAll(entitiesToRemove);
        entityList.removeAll(entitiesToRemove);
        bulletList.removeAll(bulletsToRemove);

        for (Bullet bullet : bulletList) {
            if (bullet.isOwner(OwnerType.ENEMY) && player.checkIntersects(bullet)) {
                bulletsToRemovePlayer.add(bullet);
                if (player.hpMan.damage(bullet.getDamage())) {
                    gameOver = true;
                    bulletList.removeAll(bulletsToRemovePlayer);
                    break;
                }
            }
        }

        for (Enemy enemy : enemyList) {
            if (player.checkIntersects(enemy)) {
                entitiesToRemovePlayer.add(enemy);
                if (player.hpMan.damage(intersectDamage)) {
                    gameOver = true;
                    enemyList.removeAll(entitiesToRemovePlayer);
                    entityList.removeAll(entitiesToRemovePlayer);
                    break;
                }
            }
        }


        enemyList.removeAll(entitiesToRemovePlayer);
        entityList.removeAll(entitiesToRemovePlayer);
        bulletList.removeAll(bulletsToRemovePlayer);
        GameWindow.objects.removeAll(entitiesToRemovePlayer);
        GameWindow.objects.removeAll(bulletsToRemovePlayer);
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
        if (player.hpMan.getHP() > damage) {
            player.hpMan.damage(damage);
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
        if (player.hpMan.getHP() <= 0) {
            gameOver = true;
        }

        if (enemyList.isEmpty()) {
            gameOver = true;
            isWin = true;
        }
    }

    public static void addEntity(Entity entity) {
        entityList.add(entity);
    }

    public static void addBullet(Bullet bullet) {
        bulletList.add(bullet);
    }

    public static boolean isGameOver(){
        return gameOver;
    }

    public static boolean isWin(){
        return isWin;
    }
}
