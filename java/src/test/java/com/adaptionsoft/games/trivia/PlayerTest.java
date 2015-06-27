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

    @Test
    public void get_content_in_empty_purse(){
        int expected = 0;
        Player player = new Player("Erik");

        int actual = player.getPurse();

        assertThat(actual, is(expected));
    }

    @Test
    public void add_content_to_purse(){
        int expected = 1;
        Player player = new Player("Erik");

        player.increasePurse();

        int actual = player.getPurse();

        assertThat(actual, is(expected));
    }
}
