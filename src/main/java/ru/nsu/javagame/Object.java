package ru.nsu.javagame;

abstract class Object {
    private Vector topLeft;
    private Vector bottomRight;

    protected Object (Vector topLeftCoordinates, Vector bottomRightCoordinates) {
        setTopLeft(topLeftCoordinates);
        setBottomRight(bottomRightCoordinates);
    }

    public void setTopLeft(Vector topLeftCoordinates){
        this.topLeft = topLeftCoordinates;
    }

    public void setBottomRight(Vector bottomRightCoordinates) {
        this.bottomRight = bottomRightCoordinates;
    }

    public Vector getTopLeft() {
        return this.topLeft;
    }

    public Vector getBottomRight() {
        return this.bottomRight;
    }

    public boolean checkIntersects(Object other) {
        Vector tl1 = getTopLeft();
        Vector br1 = getBottomRight();
        Vector tl2 = other.getTopLeft();
        Vector br2 = other.getBottomRight();

        return !(tl1.x > br2.x || tl2.x > br1.x || tl1.y > br2.y || tl2.y > br1.y);
    }
}

