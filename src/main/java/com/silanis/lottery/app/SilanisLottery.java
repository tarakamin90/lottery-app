package com.silanis.lottery.app;

import com.silanis.lottery.util.BallNumberGenerator;
import com.silanis.lottery.config.Config;
import com.silanis.lottery.util.ConsoleInput;
import com.silanis.lottery.util.Utility;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

/**
 * Created by TARAK on 2017-04-13.
 * Implements the LotteryInterface to create a Silanis Lottery.
 * All the configuration for the Application can be changed in com/silanis/lottery/config/Config.java
 */
public class SilanisLottery implements LotteryInterface {
    private static final Log logger = LogFactory.getLog(SilanisLottery.class); // logger

    private boolean isDrawStarted = false; // stores the status of the draw
    private boolean winnersDisplayed = false; // stores the status of the winners announcement

    private double moneyInPot; // variable for total amount present inside pot.

    private List<Ticket> soldTicketList; // map to save details of user along with their ball and ticket series selected.

    private Map<Integer, Ticket> winnerDtlsMap; // map to save winner details for current lottery


    public SilanisLottery() {
        moneyInPot = Config.INITIAL_AMT_IN_POT; // Initializing pot to 200$.
        soldTicketList = new ArrayList<>();
        winnerDtlsMap = new HashMap<>();
    }

    /**
     * Method is used to purchase ticket for lottery
     * Takes name from std input and assigns a ball no to it
     * Adds 10$ to the pot
     * Displays the user name ticket number and the ball number after purchase
     */
    @Override
    public void purchase() {
        logger.debug("Start purchase()");
        if(!isDrawStarted) {
            System.out.println("Please Enter your First Name");
            String name = ConsoleInput.getInstance().next();

            Player player = new Player(name); // creates player object with name
            Ball ball = new Ball(); // creates ball object with random ball no.
            Ticket ticket = new Ticket(player, ball); // creates ticket object with player and ball
            soldTicketList.add(ticket); // adding the sold ticket in list

            moneyInPot += Config.TICKET_PRICE; // adding the ticket price to the pot

            System.out.println("Hello " + name + "! Your Ball number is: " + ball.getBallNo()
                    + " and your Ticket number is: " + ticket.getTicketNo());
        }
        else {
            System.out.println("Draw is done. Please announce the winners to start new Lottery.");
        }
        logger.debug("End purchase()");
    }

    /**
     * Method is used to purchase ticket for lottery
     * Takes name from std input and assigns a ball no to it
     * Adds 10$ to the pot
     * Displays the user name ticket number and the ball number after purchase
     */
    @Override
    public void draw() {
        logger.debug("Start draw()");
        if(soldTicketList.size() >= Config.MIN_TICKET_SOLD_TO_ALLOW_DRAW && !isDrawStarted) // checks the conditions to perform the draw
        {

            this.isDrawStarted = true; // set the draw status to true

            double availPrizeMoney = moneyInPot * Config.AVAIL_PRIZE_MONEY_PERCENTAGE / 100; // calculate available prize money

            int firstPrizeAmt = (int) Math.round((availPrizeMoney * Config.FIRST_PRIZE_PERCENTAGE) / 100); // calculate first prize money
            int secondPrizeAmt = (int) Math.round((availPrizeMoney * Config.SECOND_PRIZE_PERCENTAGE) / 100); // calculate second prize money
            int thirdPrizeAmt = (int) Math.round((availPrizeMoney * Config.THIRD_PRIZE_PERCENTAGE) / 100); // calculate third prize money

            moneyInPot = moneyInPot - (firstPrizeAmt + secondPrizeAmt + thirdPrizeAmt); // removing amount from pot after rounding it

            // Draw starts here to get the winning balls
            Set<Integer> winners = new HashSet<>(); // check that all winning balls are unique
            int firstPrizeBallNo = BallNumberGenerator.getWinnerBallNumber();
            winners.add(firstPrizeBallNo);

            int temp = BallNumberGenerator.getWinnerBallNumber();
            while (winners.contains(temp)) // check that all winning balls are unique
            {
                temp = BallNumberGenerator.getWinnerBallNumber();
            }
            int secondPrizeBallNo = temp;

            temp = BallNumberGenerator.getWinnerBallNumber();
            while (winners.contains(temp)) // check that all winning balls are unique
            {
                temp = BallNumberGenerator.getWinnerBallNumber();
            }
            int thirdPrizeBallNo = temp;

            // finding the winning tickets for the respective winning balls from the soldTicketList -- Used lambda expressions
            Ticket winningTicket1 = soldTicketList.stream().filter(f -> f.getBall().getBallNo() == firstPrizeBallNo).findFirst().orElse(null);
            winningTicket1.setWinningAmt(firstPrizeAmt);
            Ticket winningTicket2 = soldTicketList.stream().filter(f -> f.getBall().getBallNo() == secondPrizeBallNo).findFirst().orElse(null);
            winningTicket2.setWinningAmt(secondPrizeAmt);
            Ticket winningTicket3 = soldTicketList.stream().filter(f -> f.getBall().getBallNo() == thirdPrizeBallNo).findFirst().orElse(null);
            winningTicket3.setWinningAmt(thirdPrizeAmt);

            // adding the winning tickets in map
            winnerDtlsMap.put(1, winningTicket1);
            winnerDtlsMap.put(2, winningTicket2);
            winnerDtlsMap.put(3, winningTicket3);

            System.out.println("Amount in pot after draw : " + moneyInPot);
        }
        else {
            System.out.println("Draw cannot be done. Tickets sold are less than 3");
        }
        logger.debug("End draw()");
    }

    /**
     * Displays the winners and resets the application to start a new lottery session
     */
    @Override
    public void winners() {
        logger.debug("Start winners()");
        if (!isDrawStarted) // displays winners of last draw if the draw for current session is not yet done
        {
            System.out.println("Sorry!! Draw has not started for " + Utility.getUserReadableMonthYearSeries());

            if(!winnerDtlsMap.isEmpty()) // check whether it is the first session
            {
                System.out.println("\nWinners for last Draw ( "
                        + Utility.getUserReadableMonthYearSeries(winnerDtlsMap.get(1).getTicketSeries()) + " ) are below:");
                displayWinners();
            }
        } else {
            System.out.println(" Winners for " + winnerDtlsMap.get(1).getTicketSeries() + " are: ");
            displayWinners();

           resetSilanisLottery();
        }
        logger.debug("End winners()");
    }

    /**
     * Displays the winners in required format
     */
    private void displayWinners() {
        logger.debug("Start displayWinners()");

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

    /**
     * resets the SilanisLottery application to start a new session
     */
    private void resetSilanisLottery() {
        logger.debug("Start resetSilanisLottery()");

        winnersDisplayed = true;
        soldTicketList = new ArrayList<>();
        BallNumberGenerator.resetBallNumberGenerator();
        isDrawStarted = false;

        logger.debug("End resetSilanisLottery()");
    }
}
