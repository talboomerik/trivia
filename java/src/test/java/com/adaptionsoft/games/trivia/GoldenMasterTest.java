package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GoldenMasterTest {

    @Test
    public void randomInputsCheck() throws Exception {
        ArrayList<Long> seeds  = new ArrayList<>();
        seeds.add(12341234L);
        seeds.add(1234256336L);
        seeds.add(4667687L);
        seeds.add(435345L);
        seeds.add(7657757L);
        seeds.add(9800890890L);
        seeds.add(3453253L);
        seeds.add(576757L);

        List<String> systemOutput = seeds.stream().map(
                seed ->
                {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    PrintStream myPrintStream = new PrintStream(baos);
                    PrintStream old = System.out;
                    System.setOut(myPrintStream);

                    GameRunner.runGame(seed);

                    System.out.flush();
                    System.setOut(old);
                    return baos.toString();
                }
        ).collect(Collectors.toList());

        Approvals.verifyAll("", systemOutput);
    }
}
