package com.silanis.lottery.handlerequest;

import com.silanis.lottery.app.LotteryInterface;

/**
 * Created by TARAK on 2017-04-13.
 */
public class Winners implements Command{
    LotteryInterface lotteryApp;

    public Winners(LotteryInterface lotteryApp){
        this.lotteryApp = lotteryApp;
    }

    @Override
    public void execute() {
        lotteryApp.winners();
    }
}
