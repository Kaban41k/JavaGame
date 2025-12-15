package ru.nsu.javagame;

import java.util.List;

public class CollisionManager {
    private List<Entity> entityList;
    private double screenW;
    private double screenH;

    public CollisionManager(List<Entity> entityList) {
        this.entityList = entityList;
    }

    public void setScreenW(double w) {
        screenW = w;
    }

    public void setScreenH(double h) {
        screenH = h;
    }

    public void checkForCollisions() {
        for (int i = 0; i < entityList.size(); i++) {
            for (int j = i + 1; j < entityList.size(); j++) {
                boolean intersect = entityList.get(i).checkCollision(entityList.get(j));

                if (intersect) {
                    System.out.println("Enemies collision: " + i + " and " + j +" collapsed.");
                }
            }
        }
    }


}
