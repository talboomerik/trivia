package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Player;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {

    @Test
    public void get_name() {
        String expected = "Erik";

        Player player = new Player("Erik");

        String name = player.getName();

        assertThat(name, is(expected));
    }
}
