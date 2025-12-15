package ru.nsu.javagame;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    List<Entity> entityList = new ArrayList<>();
    private Player player;
    private HPManager playerHealth;
    private CollisionManager collisionManager;
    private boolean gameOver = false;
    private int enemyDamage;

    public GameManager(HPManager health) {
        playerHealth = health;
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
        for (Entity entity : entityList) {
            if (entity instanceof Enemy) {
                entity.cleanMove();
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
        int prevPlayerHP = playerHealth.getHP();
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
