package com.silanis.lottery.commandhandler;

import com.silanis.lottery.app.LotteryInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by TARAK on 2017-04-13.
 * Implementation for the Winners command
 * Command design Pattern
 */
public class Winners implements Command{
    private static final Log logger = LogFactory.getLog(Winners.class);
    private LotteryInterface lotteryApp;

    public Winners(LotteryInterface lotteryApp){
        this.lotteryApp = lotteryApp;
    }

    /**
     * makes a call to the winners method of SilanisLottery
     */
    @Override
    public void execute() {
        logger.debug("Start execute()");
        lotteryApp.winners();
        logger.debug("End execute()");
    }
}
