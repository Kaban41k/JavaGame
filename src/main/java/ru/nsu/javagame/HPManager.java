package ru.nsu.javagame;

public class HPManager {
    private int currentHP;
    private int maxHP;

    public HPManager(int intialHP) {
        currentHP = intialHP;
        maxHP = intialHP;
    }

    public int getHP() {
        return currentHP;
    }

    public boolean damage(int damage) {
        currentHP -= damage;
        return currentHP <= 0;
    }

    public boolean heal(int heal) {
        currentHP += heal;
        if (currentHP > maxHP) {
            currentHP = maxHP;
        }
        return true;
    }
}
