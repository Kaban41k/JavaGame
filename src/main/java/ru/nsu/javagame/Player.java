package ru.nsu.javagame;

public class Player extends Entity implements Movement{
    public HPManager hpMan;
    public Gun gun;
    private Vector bulletSize = new Vector((double) 2, (double) 2);
    private int playerShootDamage = 2;

    public Player(Vector topLeft, Vector bottomRight, HPManager hp, Gun g) {
        super(topLeft, bottomRight);
        hpMan = hp;
        gun = g;
    }

    public boolean move(Vector movVect) {
        setTopLeft(new Vector(getTopLeft().x + movVect.x, getTopLeft().y + movVect.y));
        setBottomRight(new Vector(getBottomRight().x + movVect.x, getBottomRight().y + movVect.y));
        return true;
    }

    public void fire() {
        Vector centerPoint = new Vector((getTopLeft().x + getBottomRight().x) / 2,
                (getTopLeft().y + getBottomRight().y) / 2);
        gun.shoot(new Vector(centerPoint.x - (bulletSize.x / 2),
                centerPoint.y - (bulletSize.y / 2)),
                new Vector(centerPoint.x + (bulletSize.x / 2),
                centerPoint.y + (bulletSize.y / 2)), playerShootDamage, OwnerType.PLAYER);

    }

    public HPManager getHpManager() {
        return hpMan;
    }
}
