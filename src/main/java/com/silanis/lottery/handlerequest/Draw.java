package com.silanis.lottery.handlerequest;

import com.silanis.lottery.app.LotteryInterface;

/**
 * Created by TARAK on 2017-04-13.
 */
public class Draw implements Command {
    LotteryInterface lotteryApp;

    public Draw(LotteryInterface lotteryApp){
        this.lotteryApp = lotteryApp;
    }

    @Override
    public void execute() {
        lotteryApp.draw();
    }
}
