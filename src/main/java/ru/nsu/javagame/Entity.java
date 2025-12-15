package ru.nsu.javagame;

public class Entity extends Object implements Movement{

    public Entity(Vector topLeftCoordinates, Vector bottomRightCoordinates) {
        super(topLeftCoordinates, bottomRightCoordinates);
    }

    public Vector getMovVect() {
        return new Vector(0, 0);
    }

    @Override
    public boolean move(Vector movVect) {
        throw new UnsupportedOperationException("Movement is not implemented.");
    }

    @Override
    public boolean checkCollision(Object other) {
        return checkIntersects(other);
    }


}
