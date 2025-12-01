package ru.nsu.javagame;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Player extends Entity implements Movement{
    private HPManager hpMan;
    private Gun gun;
    private GameManager gameManager;
    private Vector bulletSize = new Vector((double) 2, (double) 2);
    private int playerShootDamage = 2;
    private  final int BUFFER_SIZE = 10;
    private Vector[] buffer = new Vector[BUFFER_SIZE];
    private int head = 0;
    private int tail = 0;
    private Lock lock = new ReentrantLock();

    public Player(Vector topLeft, Vector bottomRight, HPManager hp, Gun g, GameManager gm) {
        super(topLeft, bottomRight);
        hpMan = hp;
        gun = g;
        gameManager = gm;
    }


    public boolean move(Vector movVect) {
        setTopLeft(new Vector(getTopLeft().x + movVect.x, getTopLeft().y + movVect.y));
        setBottomRight(new Vector(getBottomRight().x + movVect.x, getBottomRight().y + movVect.y));
        return true;
    }

    public void fire(Bullet b) {
        Vector centerPoint = new Vector((getTopLeft().x + getBottomRight().x) / 2,
                (getTopLeft().y + getBottomRight().y) / 2);
        Bullet bullet = gun.shoot(new Vector(centerPoint.x - (bulletSize.x / 2),
                centerPoint.y - (bulletSize.y / 2)),
                new Vector(centerPoint.x + (bulletSize.x / 2),
                centerPoint.y + (bulletSize.y / 2)), playerShootDamage);
        gameManager.addEntity(bullet);

    }

    public HPManager getHpManager() {
        return hpMan;
    }
}
