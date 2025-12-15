package ru.nsu.javagame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {

    @Test
    public void testPlayerCreation() {
        Vector topLeft = new Vector(0, 0);
        Vector bottomRight = new Vector(10, 10);
        HPManager hpMgr = new HPManager(100);
        Gun gun = new Gun(Direction.RIGHT, 10);
        GameManager gameManager = new GameManager();

        Player player = new Player(topLeft, bottomRight, hpMgr, gun);
        assertNotNull(player);
    }

//    @Test
//    public void testFireMethod() {
//        Vector topLeft = new Vector(0, 0);
//        Vector bottomRight = new Vector(10, 10);
//        HPManager hpMgr = new HPManager(100);
//        Gun gun = new Gun(Direction.RIGHT, 10);
//        GameManager gameManager = new GameManager(hpMgr);
//
//        Player player = new Player(topLeft, bottomRight, hpMgr, gun);
//
//        player.fire();
//    }
}
