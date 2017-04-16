package com.silanis.lottery.app;

import com.silanis.lottery.util.BallNumberGenerator;

/**
 * Created by TARAK on 2017-04-13.
 *
 * Ball class to store the ball no.
 *
 * we can add some funtionality in future if we want
 */
public class Ball {
    // stores the ball number
    private int ballNo;

    public Ball() {

        // Generates a random number in the range and assigns to the ballNo
        this.ballNo = BallNumberGenerator.getRandomBallNumber();
    }

    public int getBallNo() {
        return ballNo;
    }

    @Override
    public String toString() {
        return "Ball{" + "ballNo=" + ballNo + '}';
    }

}
