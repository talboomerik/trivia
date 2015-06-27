package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.junit.Test;

import javax.naming.LinkLoopException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GoldenMasterTest {

    @Test
    public void verify_test_approval() throws Exception {
        String[] names = {"Llewellyn", "James", "Dan", "Jason", "Katrina"};
        Arrays.sort(names);
        Approvals.verifyAll("", names);
    }

    @Test
    public void verify_main() throws Exception {
        ByteArrayOutputStream baout = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(baout);
        System.setOut(output);

        List<Integer> seeds = new LinkedList<Integer>();
        seeds.add(17);
        seeds.add(42);

        for (long seed : seeds) {
            Random random = new Random(seed);

            new GameRunner(random);
            GameRunner.main(new String[0]);
        }

        String[] actual = baout.toString("UTF-8").split("\\n");

        Approvals.verifyAll("", actual);
    }
}
