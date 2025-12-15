package ru.nsu.javagame;

interface Movement {
    boolean move(Vector movVect);
    boolean checkCollision(Object other);
}
