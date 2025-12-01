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
        checkBounds();
    }

    private void checkBounds() {
        for (Entity entity : entityList) {
            Vector topLeft = entity.getTopLeft();
            Vector bottomRight = entity.getBottomRight();
            Vector screenTopLeft = entity.getTopLeftOnScreen();
            Vector screenBottomRight = entity.getBottomRightOnScreen();

            if (topLeft != screenTopLeft || bottomRight != screenBottomRight) {
                entity.changeScreenCoordinates(topLeft, bottomRight);
            }
            if (topLeft.x < 0) {
                Vector newTopLeft = new Vector((double) 0, topLeft.y);
                entity.changeScreenCoordinates(newTopLeft, bottomRight);
            }
            if (topLeft.y > screenH) {
                Vector newTopLeft = new Vector(topLeft.x, screenH);
                entity.changeScreenCoordinates(newTopLeft, bottomRight);
            }

            if (bottomRight.x > screenW) {
                Vector newBottomRight = new Vector(screenW, bottomRight.y);
                entity.changeScreenCoordinates(topLeft, newBottomRight);
            }

            if (bottomRight.y < 0) {
                Vector newBottomRight = new Vector(bottomRight.x, (double) 0);
                entity.changeScreenCoordinates(topLeft, newBottomRight);
            }

        }
    }
}
