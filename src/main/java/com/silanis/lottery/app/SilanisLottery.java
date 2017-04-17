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
     * Adds 10$ to the pot
     * @param name of the player
     * @return return ticket object
     */
    @Override
    public Ticket purchase(String name) {
        logger.debug("Start purchase()");

        Player player = new Player(name); // creates player object with name
        Ball ball = new Ball(); // creates ball object with random ball no.
        Ticket ticket = new Ticket(player, ball); // creates ticket object with player and ball
        soldTicketList.add(ticket); // adding the sold ticket in list

        moneyInPot += Config.TICKET_PRICE; // adding the ticket price to the pot

        logger.debug("End purchase()");
        return ticket;
    }

    /**
     * Method is used to draw winning balls and compute the prize money for lottery
     * @return amount in pot after draw else null
     */
    @Override
    public Double draw() {
        logger.debug("Start draw()");
        Double result = null;

        if(soldTicketList.size() >= Config.MIN_TICKET_SOLD_TO_ALLOW_DRAW) // checks the conditions to perform the draw
        {
            double availPrizeMoney = moneyInPot * Config.AVAIL_PRIZE_MONEY_PERCENTAGE / 100; // calculate available prize money

            int firstPrizeAmt = (int) Math.round((availPrizeMoney * Config.FIRST_PRIZE_PERCENTAGE) / 100); // calculate first prize money
            int secondPrizeAmt = (int) Math.round((availPrizeMoney * Config.SECOND_PRIZE_PERCENTAGE) / 100); // calculate second prize money
            int thirdPrizeAmt = (int) Math.round((availPrizeMoney * Config.THIRD_PRIZE_PERCENTAGE) / 100); // calculate third prize money

            moneyInPot = moneyInPot - (firstPrizeAmt + secondPrizeAmt + thirdPrizeAmt); // removing amount from pot after rounding it

           /* // Old Code for Draw starts here to get the winning balls
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
            {lambda
                temp = BallNumberGenerator.getWinnerBallNumber();
            }
            int thirdPrizeBallNo = temp;

            // finding the winning tickets for the respective winning balls from the soldTicketList -- Used LAMBDA expressions
            Ticket winningTicket1 = soldTicketList.stream().filter(f -> f.getBall().getBallNo() == firstPrizeBallNo).findFirst().orElse(null);
            winningTicket1.setWinningAmt(firstPrizeAmt);
            Ticket winningTicket2 = soldTicketList.stream().filter(f -> f.getBall().getBallNo() == secondPrizeBallNo).findFirst().orElse(null);
            winningTicket2.setWinningAmt(secondPrizeAmt);
            Ticket winningTicket3 = soldTicketList.stream().filter(f -> f.getBall().getBallNo() == thirdPrizeBallNo).findFirst().orElse(null);
            winningTicket3.setWinningAmt(thirdPrizeAmt);
            */

            // new optimized code

            Collections.shuffle(soldTicketList); // randomly shuffles the elements of the list and swaps them

            Ticket winningTicket1 = soldTicketList.get(0);
            Ticket winningTicket2 = soldTicketList.get(1);
            Ticket winningTicket3 = soldTicketList.get(2);

            winningTicket1.setWinningAmt(firstPrizeAmt);
            winningTicket2.setWinningAmt(secondPrizeAmt);
            winningTicket3.setWinningAmt(thirdPrizeAmt);

            // adding the winning tickets in map
            winnerDtlsMap.put(1, winningTicket1);
            winnerDtlsMap.put(2, winningTicket2);
            winnerDtlsMap.put(3, winningTicket3);

            result = moneyInPot;
        }

        logger.debug("End draw()");
        return result;
    }

    /**
     * resets the application to start a new lottery session
     * @return the winner tickets map
     */
    @Override
    public Map<Integer, Ticket> winners() {
        logger.debug("Start winners()");

        //resets the SilanisLottery application to start a new session
        soldTicketList = new ArrayList<>();
        BallNumberGenerator.resetBallNumberGenerator();

        logger.debug("End winners()");

        return winnerDtlsMap;
    }
}
