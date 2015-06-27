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
        List<Long> seeds = arrayToList(new long[]{12341234L, 12341234L, 1234256336L, 4667687L, 435345L, 7657757L, 9800890890L, 3453253L, 576757L});

        List<String> systemOutput = seeds.stream().map(
                seed ->
                {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    PrintStream myPrintStream = new PrintStream(baos);

                    GameRunner.runGame(seed, myPrintStream);

                    return baos.toString();
                }
        ).collect(Collectors.toList());

        Approvals.verifyAll("", systemOutput);
    }

    private List<Long> arrayToList(long[] array) {
        List<Long> seeds  = new ArrayList<>();
        for(long value : array) {
            seeds.add(value);
        }
        return seeds;
    }
}
