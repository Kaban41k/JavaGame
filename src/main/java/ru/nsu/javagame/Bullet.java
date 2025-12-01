package ru.nsu.javagame;

enum Direction {
    LEFT,
    RIGHT
};

public class Bullet extends Entity{
    private Direction direction;
    private int damage;

    public Bullet(Vector topLeft, Vector bottomRight, Direction dir, int dam) {
        super(topLeft, bottomRight);
        direction = dir;
        damage = dam;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public boolean move() {
        switch (direction) {
            case LEFT:
                setTopLeft(new Vector(getTopLeft().x - 1, getTopLeft().y));
                setBottomRight(new Vector(getBottomRight().x - 1, getBottomRight().y));
                break;
            case RIGHT:
                setTopLeft(new Vector(getTopLeft().x + 1, getTopLeft().y));
                setBottomRight(new Vector(getBottomRight().x + 1, getBottomRight().y));
                break;
            default:
                throw new IllegalArgumentException("Invalid direction for bullet.");
        }
        return true;
    }

}
