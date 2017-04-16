package com.silanis.lottery.util;

import com.silanis.lottery.config.Config;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Random;

/**
 * Created by TARAK on 2017-04-13.
 */
public class BallNumberGenerator {
    private static final Log logger = LogFactory.getLog(BallNumberGenerator.class);

    public static int range = Config.BALL_NUMBER_RANGE_MAX; // Max balls in the lottery

    private static boolean generatedBallNos[] = new boolean[range]; // stores the status of the ball nos generated
    private static Random random = new Random(); // used to generate random numbers

    /**
     * generate random ball no in range
     * @return random ball no
     */
    public static int getRandomBallNumber() {
        logger.debug("Start getRandomBallNumber()");

        int randBallNo = random.nextInt(range); // generates random number from 0 to range

        // checks whether the generated random number is already generated before
        while(generatedBallNos[randBallNo]) {
            randBallNo = random.nextInt(range);
        }

        // makes the flag true for the generated number
        generatedBallNos[randBallNo] = true;

        logger.debug("End getRandomBallNumber()");
        return randBallNo;
    }

    /**
     * draws winning ball no from the generated ball nos
     * @return winning ball number
     */
    public static int getWinnerBallNumber() {
        logger.debug("Start getWinnerBallNumber()");

        int winnerBallNo = random.nextInt(range); // generates random number from 0 to range

        // checks whether the generated random number is from the list of generated ball nos
        while(!generatedBallNos[winnerBallNo]) {
            winnerBallNo = random.nextInt(range);
        }

        logger.debug("End getWinnerBallNumber()");
        return winnerBallNo;
    }

    /**
     * resets the status array to start a new lottery session
     */
    public static void resetBallNumberGenerator() {
        logger.debug("Start resetBallNumberGenerator()");

        generatedBallNos = new boolean[range];

        logger.debug("End resetBallNumberGenerator()");
    }
}

