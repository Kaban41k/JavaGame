package ru.nsu.javagame;

public class StaticEnemy extends Entity {
    private Vector bulletSize = new Vector((double) 3, (double) 3);
    public Gun gun = new Gun(Direction.LEFT, (int) (Game.TPS * 3), 1);
    private int enemyShootDamage = 1;

    public StaticEnemy(Vector topLeft, Vector bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public void fire() {
        Vector centerPoint = new Vector((getTopLeft().x + getBottomRight().x) / 2,
                (getTopLeft().y + getBottomRight().y) / 2);
        gun.shoot(new Vector(centerPoint.x - (bulletSize.x / 2),
                        centerPoint.y - (bulletSize.y / 2)),
                new Vector(centerPoint.x + (bulletSize.x / 2),
                        centerPoint.y + (bulletSize.y / 2)), enemyShootDamage, OwnerType.ENEMY);
    }
}
