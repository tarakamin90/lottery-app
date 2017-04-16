package com.silanis.lottery.commandhandler;

import com.silanis.lottery.app.LotteryInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by TARAK on 2017-04-13.
 * Implementation for Purchase command
 * Command design Pattern
 */
public class Purchase implements Command{
    private static final Log logger = LogFactory.getLog(Purchase.class);
    private LotteryInterface lotteryApp;

    public Purchase(LotteryInterface lotteryApp){
        this.lotteryApp = lotteryApp;
    }

    /**
     * makes a call to the purchase method of SilanisLottery
     */
    @Override
    public void execute() {
        logger.debug("Start execute()");
        lotteryApp.purchase();
        logger.debug("End execute()");
    }
}
