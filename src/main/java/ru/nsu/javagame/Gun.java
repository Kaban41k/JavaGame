package ru.nsu.javagame;

public class Gun {
    private int gunSpeed;
    private Direction shootDirection;

    public Gun(Direction d, int speed) {
        gunSpeed = speed;
        shootDirection = d;
    }


    public int getGunSpeed() {
        return gunSpeed;
    }

    public Bullet shoot(Vector topLeft, Vector bottomRight, int damage) {
        return new Bullet(topLeft, bottomRight, shootDirection, damage);
    }
}
