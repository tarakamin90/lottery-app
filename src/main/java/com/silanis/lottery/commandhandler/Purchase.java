package com.silanis.lottery.commandhandler;

import com.silanis.lottery.app.LotteryInterface;
import com.silanis.lottery.app.Ticket;
import com.silanis.lottery.util.ConsoleInput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Scanner;

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
     * Takes name from std input and assigns a ball no to it
     * makes a call to the purchase method of SilanisLottery
     * Displays the user name ticket number and the ball number after purchase
     * @return true false for method executed successfully
     */
    @Override
    public boolean execute() {
        logger.debug("Start execute()");

        Scanner consoleInput = ConsoleInput.getInstance();

        System.out.println("Please Enter your First Name");
        String name = consoleInput.nextLine();
        while("".equals(name)) {
            name = consoleInput.nextLine();
        }
        Ticket ticket = lotteryApp.purchase(name);

        System.out.println("Hello " + ticket.getPlayer().getName() + "! Your Ball number is: " + ticket.getBall().getBallNo()
                + " and your Ticket number is: " + ticket.getTicketNo());

        logger.debug("End execute()");
        return  true;
    }
}
