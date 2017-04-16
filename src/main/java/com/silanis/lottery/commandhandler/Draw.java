package com.silanis.lottery.commandhandler;

import com.silanis.lottery.app.LotteryInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by TARAK on 2017-04-13.
 * Implementation for Draw command
 * Command design Pattern
 */
public class Draw implements Command {
    private static final Log logger = LogFactory.getLog(Draw.class);
    private LotteryInterface lotteryApp;

    public Draw(LotteryInterface lotteryApp){
        this.lotteryApp = lotteryApp;
    }

    /**
     * makes a call to the draw method of SilanisLottery
     */
    @Override
    public void execute() {
        logger.debug("Start execute()");
        lotteryApp.draw();
        logger.debug("End execute()");
    }
}
