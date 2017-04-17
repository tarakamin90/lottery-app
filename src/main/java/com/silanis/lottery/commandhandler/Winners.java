package com.silanis.lottery.commandhandler;

import com.silanis.lottery.app.LotteryInterface;
import com.silanis.lottery.app.Ticket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

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
     * displays the winners
     * @return true false for method executed successfully
     */
    @Override
    public boolean execute() {
        logger.debug("Start execute()");
        Map<Integer, Ticket> winnerDtlsMap = lotteryApp.winners();

        displayWinners(winnerDtlsMap);

        System.out.println("Welcome to Silanis LotteryApp !!!");
        logger.debug("End execute()");
        return true;
    }

    /**
     * Displays the winners in required format
     */
    private void displayWinners(Map<Integer, Ticket> winnerDtlsMap) {
        logger.debug("Start displayWinners()");

        System.out.println(" Winners for " + winnerDtlsMap.get(1).getTicketSeries() + " are: ");

        Ticket winner1 = winnerDtlsMap.get(1);
        Ticket winner2 = winnerDtlsMap.get(2);
        Ticket winner3 = winnerDtlsMap.get(3);

        System.out.printf("%15s \t %15s \t %15s \n", "1st ball ", "2nd ball", "3rd ball");
        System.out.printf("%15s \t %15s \t %15s \n", winner1.getPlayer().getName() + ": $" + winner1.getWinningAmt(),
                winner2.getPlayer().getName() + ": $" + winner2.getWinningAmt(),
                winner3.getPlayer().getName() + ": $" + winner3.getWinningAmt());
        System.out.printf("%15s \t %15s \t %15s \n", winner1.getTicketNo(), winner2.getTicketNo(),
                winner3.getTicketNo());
        System.out.printf("%15s \t %15s \t %15s \n", winner1.getBall().getBallNo(), winner2.getBall().getBallNo(),
                winner3.getBall().getBallNo());

        logger.debug("End displayWinners()");
    }
}
