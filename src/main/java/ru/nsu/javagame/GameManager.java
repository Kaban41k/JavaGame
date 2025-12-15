package ru.nsu.javagame;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    List<Entity> entityList = new ArrayList<>();
    List<Bullet> bulletList = new ArrayList<>();
    public Player player;
    private HPManager playerHealth;
    private CollisionManager collisionManager;
    private boolean gameOver = false;
    private int enemyDamage = 1;

    public GameManager() {
        playerHealth = new HPManager(100);
        collisionManager = new CollisionManager(this.entityList);
    }

    public void setEnemyDamage(int damage) {
        enemyDamage = damage;
    }

    public void initializeGame(Player player, List<Entity> enemies) {
        this.player = player;
        entityList.addAll(enemies);
    }

    public void tick() {
        for (Bullet bullet : bulletList) {
            boolean move = bullet.move(new Vector(0, 0));
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

        handleCollisions();
        updateScore();
        checkWinLose();
    }

    private void handleCollisions() {
        collisionManager.checkForCollisions();
    }

    private void _updateScore(int damage) {
        if (playerHealth.getHP() > damage) {
            playerHealth.damage(damage);
        }
        else {
            gameOver = true;
        }
    }

    private void updateScore() {
        for (Entity entity : entityList) {
            if (player.checkIntersects(entity)) {
                _updateScore(enemyDamage);
            }
        }
    }

    private void checkWinLose() {
        if (playerHealth.getHP() <= 0) {
            gameOver = true;
        }
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
    }

    public boolean isGameOver(){
        return gameOver;
    }
}
