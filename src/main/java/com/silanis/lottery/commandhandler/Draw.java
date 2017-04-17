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
     * Display the pot amount after the draw or the appropriate message
     * @return true false for method executed successfully
     */
    @Override
    public boolean execute() {
        logger.debug("Start execute()");
        boolean result = false;

        Double moneyInPot = lotteryApp.draw();

        if (moneyInPot != null) {
            result = true;
            System.out.println("Amount in pot after draw : " + moneyInPot);
        }
        else {
            System.out.println("Draw cannot be done. Tickets sold are less than 3");
        }

        logger.debug("End execute()");
        return result;
    }
}
