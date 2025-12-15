package ru.nsu.javagame;

public class Enemy extends StaticEnemy implements Movement{
    private Vector movVect;
    private int gunSpeed;
    private Gun gun;

    public Enemy(Vector topLeft, Vector bottomRight, int points, Vector mov) {
        super(topLeft, bottomRight);
        movVect = mov;
    }

    @Override
    public Vector getMovVect() {
        return movVect;
    }

    @Override
    public boolean move(Vector movVect) {
        setTopLeft(new Vector(getTopLeft().x + movVect.x, getTopLeft().y + movVect.y));
        setBottomRight(new Vector(getBottomRight().x + movVect.x, getBottomRight().y + movVect.y));
        setTopLeftOnScreen(getTopLeft());
        setBottomRightOnScreen(getBottomRight());
        return true;
    }
}
