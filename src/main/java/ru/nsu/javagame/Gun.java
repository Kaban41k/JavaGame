package ru.nsu.javagame;

public class Gun {
    private int bulletSpeed;
    private Direction shootDirection;

    public Gun(Direction d, int speed) {
        bulletSpeed = speed;
        shootDirection = d;
    }

    public Bullet shoot(Vector topLeft, Vector bottomRight, int damage) {
        return new Bullet(topLeft, bottomRight, shootDirection, damage);
    }
}
