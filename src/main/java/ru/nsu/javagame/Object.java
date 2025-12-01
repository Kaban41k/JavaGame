package ru.nsu.javagame;

abstract class Object {
    private Vector topLeftCoordinates;
    private Vector bottomRightCoordinates;

    protected Object (Vector topLeftCoordinates, Vector bottomRightCoordinates) {
        setTopLeft(topLeftCoordinates);
        setBottomRight(bottomRightCoordinates);
    }

    public void setTopLeft(Vector topLeftCoordinates){
        this.topLeftCoordinates = topLeftCoordinates;
    }

    public void setBottomRight(Vector bottomRightCoordinates) {
        this.bottomRightCoordinates = bottomRightCoordinates;
    }

    public Vector getTopLeft() {
        return this.topLeftCoordinates;
    }

    public Vector getBottomRight() {
        return this.bottomRightCoordinates;
    }

    public boolean checkIntersects(Object other) {
        Vector tl1 = getTopLeft();
        Vector br1 = getBottomRight();
        Vector tl2 = other.getTopLeft();
        Vector br2 = other.getBottomRight();

        return !(tl1.x > br2.x || tl2.x > br1.x || tl1.y > br2.y || tl2.y > br1.y);
    }
}

