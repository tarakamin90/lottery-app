package com.silanis.lottery.handlerequest;

import com.silanis.lottery.app.LotteryInterface;

/**
 * Created by TARAK on 2017-04-13.
 */
public class Purchase implements Command{
    LotteryInterface lotteryApp;

    public Purchase(LotteryInterface lotteryApp){
        this.lotteryApp = lotteryApp;
    }

    @Override
    public void execute() {
        lotteryApp.purchase();
    }
}
