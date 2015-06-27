package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class GoldenMasterTest {

    @Test
    public void verify_test_approval() throws Exception {
        String[] names = {"Llewellyn", "James", "Dan", "Jason", "Katrina"};
        Arrays.sort(names);
        Approvals.verifyAll("", names);
    }

    @Test
    public void verify_main() throws Exception {
        String name = "gameOutput.txt";
        PrintStream output = new PrintStream(new BufferedOutputStream(new FileOutputStream(name)), true);
        System.setOut(output);

        GameRunner.main(new String[0]);

        // Approvals.verifyAll("", names);
    }
}




