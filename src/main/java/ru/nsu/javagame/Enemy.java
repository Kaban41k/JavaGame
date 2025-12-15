package ru.nsu.javagame;

public class Enemy extends StaticEnemy implements Movement{
    private Vector movVect;


    public Enemy(Vector topLeft, Vector bottomRight, Vector mov) {
        super(topLeft, bottomRight);
        movVect = mov;
    }

    @Override
    public Vector getMovVect() {
        return movVect;
    }

    @Override
    public boolean move(Vector movVector) {
        if (this.getTopLeft().y < 0)
            movVect = new Vector(0, 1);
        if (this.getBottomRight().y > GameWindow.canvas.getHeight())
            movVect = new Vector(0, -1);

        setTopLeft(new Vector(getTopLeft().x + movVect.x, getTopLeft().y + movVect.y));
        setBottomRight(new Vector(getBottomRight().x + movVect.x, getBottomRight().y + movVect.y));
        return true;
    }
}
