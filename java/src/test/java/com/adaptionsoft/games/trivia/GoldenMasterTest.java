package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class GoldenMasterTest {

    private long seed;

    public GoldenMasterTest(long seed) {
        this.seed = seed;
    }

    @Parameterized.Parameters
    public static Collection<Long[]> seeds() {
        return arrayToListOfLongArrays(new long[]{12341234L, 12341234L, 1234256336L, 4667687L, 435345L, 7657757L, 9800890890L, 3453253L, 576757L});
    }

    @Test
    public void randomInputsCheck() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream myPrintStream = new PrintStream(byteArrayOutputStream);

        GameRunner.runGame(seed, myPrintStream);

        String systemOutput = byteArrayOutputStream.toString();
        Approvals.verify(systemOutput);
    }

    private static List<Long[]> arrayToListOfLongArrays(long[] array) {
        List<Long[]> seeds = new ArrayList<>();
        for (long value : array) {
            seeds.add(new Long[]{value});
        }
        return seeds;
    }
}
