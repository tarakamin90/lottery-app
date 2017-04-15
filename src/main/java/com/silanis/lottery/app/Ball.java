package com.silanis.lottery.app;

import com.silanis.lottery.util.BallNumberGenerator;

/**
 * Created by TARAK on 2017-04-13.
 */
public class Ball {
    private int ballNo;

    public Ball() {
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
