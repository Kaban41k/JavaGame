package ru.nsu.javagame;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    List<Entity> entityList = new ArrayList<>();
    private Player player;
    private HPManager playerHealth;
    private CollisionManager collisionManager;
    private boolean gameOver = false;

    public GameManager(HPManager health) {
        playerHealth = health;
        collisionManager = new CollisionManager(this.entityList);
    }

    public void initializeGame(Player player, List<Entity> enemies) {
        this.player = player;
        entityList.addAll(enemies);
    }

    public void tick() {
        if (player instanceof Movement) {
            ((Movement)player).move();
        }
        for (Entity entity : entityList) {
            if (entity instanceof Movement) {
                ((Movement)entity).move();
            }
        }
        handleCollisions();
        updateScore();
        checkWinLose();
    }

    private void handleCollisions() {
        collisionManager.checkForCollisions();
    }

    private void updateScore() {

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
