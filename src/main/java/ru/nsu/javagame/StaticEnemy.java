package ru.nsu.javagame;

public class StaticEnemy extends Entity{
    private int killDamage;

    public StaticEnemy(Vector topLeft, Vector bottomRight, int points) {
        super(topLeft, bottomRight);
        killDamage = points;
    }

    public int getKillDamage() {
        return killDamage;
    }
}
