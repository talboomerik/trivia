package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
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

        for (int index = 1; index < 5; index++) {
            Random random = new OurRandom(index);

            new GameRunner(random);
            GameRunner.main(new String[0]);
        }

        String[] actual = baout.toString("UTF-8").split("\\n");

        Approvals.verifyAll("", actual);
    }

    private class OurRandom extends Random {
        private int nextInt;

        public OurRandom(int nextInt) {
            this.nextInt = nextInt;
        }

        @Override
        public int nextInt(int bound) {
            return nextInt;
        }
    }

}




