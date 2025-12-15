package ru.nsu.javagame;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CollisionManagerTest {
    @Test
    public void testCheckForCollisions() {
        List<Entity> entityList = new ArrayList<>();
        entityList.add(new StaticEnemy(new Vector(0, 0), new Vector(10, 10)));
        entityList.add(new StaticEnemy(new Vector(5, 5), new Vector(15, 15)));

        CollisionManager cm = new CollisionManager(entityList);
        cm.checkForCollisions();
    }

}
