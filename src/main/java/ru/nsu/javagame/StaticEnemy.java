package ru.nsu.javagame;

public class StaticEnemy extends Entity{
    private int gunSpeed;
    private Vector bulletSize = new Vector((double) 2, (double) 2);
    private Gun gun;
    private GameManager gameManager;
    private int enemyShootDamage = 2;

    public StaticEnemy(Vector topLeft, Vector bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public Bullet fire() {
        Vector centerPoint = new Vector((getTopLeft().x + getBottomRight().x) / 2,
                (getTopLeft().y + getBottomRight().y) / 2);
        Bullet bullet = gun.shoot(new Vector(centerPoint.x - (bulletSize.x / 2),
                        centerPoint.y - (bulletSize.y / 2)),
                new Vector(centerPoint.x + (bulletSize.x / 2),
                        centerPoint.y + (bulletSize.y / 2)), enemyShootDamage);
        return bullet;

    }




}
