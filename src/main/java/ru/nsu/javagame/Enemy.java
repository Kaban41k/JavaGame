package ru.nsu.javagame;

public class Enemy extends StaticEnemy implements Movement{
    private Vector movVect;

    public Enemy(Vector topLeft, Vector bottomRight, int points, Vector mov) {
        super(topLeft, bottomRight, points);
        movVect = mov;
    }

    @Override
    public boolean move() {
        setTopLeft(new Vector(getTopLeft().x + movVect.x, getTopLeft().y + movVect.y));
        setBottomRight(new Vector(getBottomRight().x + movVect.x, getBottomRight().y + movVect.y));
        return true;
    }
}
