package ru.nsu.javagame;

interface Movement {
    boolean move();
    boolean checkCollision(Object other);
}
