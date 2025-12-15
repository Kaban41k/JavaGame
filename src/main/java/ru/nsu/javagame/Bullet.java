package ru.nsu.javagame;

enum Direction {
    LEFT,
    RIGHT
};

public class Bullet extends Entity{
    private Direction direction;
    private int bulletSpeed = 1;
    private int damage;
    private Entity owner;

    public Bullet(Vector topLeft, Vector bottomRight, Direction dir, int dam) {
        super(topLeft, bottomRight);
        direction = dir;
        damage = dam;
    }

    public void setOwner(Entity ownr) {
        owner = ownr;
    }

    public boolean isOwner(Entity ownr) {
        return ownr.equals(owner);
    }

    public Entity getOwner() {
        return owner;
    }

    public void setBulletSpeed(int speed) {
        bulletSpeed = speed;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public boolean move(Vector movVect) {
        switch (direction) {
            case LEFT:
                setTopLeft(new Vector(getTopLeft().x - bulletSpeed, getTopLeft().y));
                setBottomRight(new Vector(getBottomRight().x - bulletSpeed, getBottomRight().y));
                break;
            case RIGHT:
                setTopLeft(new Vector(getTopLeft().x + bulletSpeed, getTopLeft().y));
                setBottomRight(new Vector(getBottomRight().x + bulletSpeed, getBottomRight().y));
                break;
            default:
                throw new IllegalArgumentException("Invalid direction for bullet.");
        }
        return true;
    }

}
