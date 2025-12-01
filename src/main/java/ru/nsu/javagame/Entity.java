package ru.nsu.javagame;

public class Entity extends Object implements Movement{
    private Vector topLeftOnScreen;
    private Vector bottomRightOnScreen;

    public Entity(Vector topLeftCoordinates, Vector bottomRightCoordinates) {
        super(topLeftCoordinates, bottomRightCoordinates);
        this.topLeftOnScreen = topLeftCoordinates;
        this.bottomRightOnScreen = bottomRightCoordinates;
    }

    @Override
    public boolean move() {
        throw new UnsupportedOperationException("Movement is not implemented.");
    }

    @Override
    public boolean checkCollision(Object other) {
        return checkIntersects(other);
    }

    public Vector getTopLeftOnScreen() {
        return topLeftOnScreen;
    }

    public Vector getBottomRightOnScreen() {
        return bottomRightOnScreen;
    }

    public void changeScreenCoordinates(Vector topLeftOnScreen, Vector bottomRightOnScreen) {
        this.topLeftOnScreen = topLeftOnScreen;
        this.bottomRightOnScreen = bottomRightOnScreen;
    }

    public boolean fullEntityOnScreen() {
        return (topLeftOnScreen.equals(this.getTopLeft())
                && bottomRightOnScreen.equals(this.getBottomRight()));
    }
}
