package ru.nsu.javagame;

public class Gun {
    private Direction shootDirection;
    private int gunDelay;
    private int bulletSpeed;
    private int timer = 0;
    private boolean isAbleToShoot = true;

    public Gun(Direction d, int delay, int bspeed) {
        gunDelay = delay;
        shootDirection = d;
        bulletSpeed = bspeed;
    }

    public void reload() {
        if (isAbleToShoot) return;

        if (timer == 0)
            isAbleToShoot = true;
        else
            timer--;
    }

    public void shoot(Vector topLeft, Vector bottomRight, int damage, Entity owner) {
        if (isAbleToShoot) {
            timer = gunDelay;
            isAbleToShoot = false;
            Bullet bullet = new Bullet(topLeft, bottomRight, shootDirection, damage);
            bullet.setBulletSpeed(bulletSpeed);
            bullet.spriteManager.setSprite(GameWindow.getSprite("pigBack1"));
            bullet.setOwner(owner);

            GameManager.addBullet(bullet);
            GameManager.bulletList.add(bullet);
            GameWindow.objects.add(bullet);
        }
    }
}
