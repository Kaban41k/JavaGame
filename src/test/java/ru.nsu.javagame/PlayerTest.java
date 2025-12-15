package ru.nsu.javagame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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


    @Test
    public void testPlayerMove() {
        Vector topLeft = new Vector(0, 0);
        Vector bottomRight = new Vector(2, 2);
        HPManager hpMgr = new HPManager(100);
        Gun gun = new Gun(Direction.RIGHT, 10);
        GameManager gameManager = new GameManager();

        Player player = new Player(topLeft, bottomRight, hpMgr, gun);
        player.move(new Vector(2, 2));
        assertEquals(2, player.getTopLeft().x);
        assertEquals(2, player.getTopLeft().y);
        assertEquals(4, player.getBottomRight().x);
        assertEquals(4, player.getBottomRight().y);
    }

}
