package com.silanis.lottery.util;

import java.util.Random;

/**
 * Created by TARAK on 2017-04-13.
 */
public class BallNumberGenerator {
    public static int range = Config.BALL_NUMBER_RANGE_MAX;

    private static boolean generatedBallNos[] = new boolean[range];
    private static Random random = new Random();

    public static int getRandomBallNumber() {
        int randBallNo = random.nextInt(range);

        while(generatedBallNos[randBallNo]) {
            randBallNo = random.nextInt(range);
        }

        generatedBallNos[randBallNo] = true;
        return randBallNo;
    }

    public static int getWinnerBallNumber() {
        int winnerBallNo = random.nextInt(range);

        while(!generatedBallNos[winnerBallNo]) {
            winnerBallNo = random.nextInt(range);
        }

        return winnerBallNo;
    }

    public static void resetBallNumberGenerator() {
        generatedBallNos = new boolean[range];
    }
}

