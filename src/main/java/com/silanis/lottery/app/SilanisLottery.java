package com.silanis.lottery.app;

import com.silanis.lottery.util.BallNumberGenerator;
import com.silanis.lottery.util.Config;
import com.silanis.lottery.util.ConsoleInput;
import com.silanis.lottery.util.Utility;
import java.util.*;

/**
 * Created by TARAK on 2017-04-13.
 */
public class SilanisLottery implements LotteryInterface {
    public static int FIRST_PRIZE_PERCENTAGE = 75;
    public static int SECOND_PRIZE_PERCENTAGE = 15;
    public static int THIRD_PRIZE_PERCENTAGE = 10;

    private boolean isDrawStarted = false;
    private boolean winnersDisplayed = false;

    // variable for total amount present inside pot. Initializing it to 200$.
    private double moneyInPot;

    // map to save details of user along with their ball and ticket series selected.
    private List<Ticket> soldTicketList;

    // map to save winner details for current month.
    private Map<Integer, Ticket> winnerDtlsMap;


    public SilanisLottery() {
        moneyInPot = Config.INITIAL_AMT_IN_POT;
        soldTicketList = new ArrayList<>();
        winnerDtlsMap = new HashMap<>();
    }

    @Override
    public void purchase() {
        if(!isDrawStarted) {
            System.out.println("Please Enter your First Name");
            String name = ConsoleInput.getInstance().next();

            Player player = new Player(name);
            Ball ball = new Ball();
            Ticket ticket = new Ticket(player, ball);
            soldTicketList.add(ticket);

            moneyInPot += 10;

            System.out.println("Hello " + name + "! Your Ball number is: " + ball.getBallNo()
                    + " and your Ticket number is: " + ticket.getTicketNo());
        }
        else {
            System.out.println("Draw is done. Please announce the winners to start new Lottery.");
        }
    }

    @Override
    public void draw() {
        if(soldTicketList.size() >= 3 && !isDrawStarted) {

            this.isDrawStarted = true;
            double availPrizeMoney = moneyInPot / 2;
            int firstPrizeAmt = (int) Math.round((availPrizeMoney * 75) / 100);
            int secondPrizeAmt = (int) Math.round((availPrizeMoney * 15) / 100);
            int thirdPrizeAmt = (int) Math.round((availPrizeMoney * 10) / 100);
            moneyInPot = moneyInPot - (firstPrizeAmt + secondPrizeAmt + thirdPrizeAmt);

            Set<Integer> winners = new HashSet<>();
            int firstPrizeBallNo = BallNumberGenerator.getWinnerBallNumber();
            winners.add(firstPrizeBallNo);

            int temp = BallNumberGenerator.getWinnerBallNumber();
            while (winners.contains(temp)) {
                temp = BallNumberGenerator.getWinnerBallNumber();
            }
            int secondPrizeBallNo = temp;

            temp = BallNumberGenerator.getWinnerBallNumber();
            while (winners.contains(temp)) {
                temp = BallNumberGenerator.getWinnerBallNumber();
            }
            int thirdPrizeBallNo = temp;

            Ticket winningTicket1 = soldTicketList.stream().filter(f -> f.getBall().getBallNo() == firstPrizeBallNo).findFirst().orElse(null);
            winningTicket1.setWinningAmt(firstPrizeAmt);
            Ticket winningTicket2 = soldTicketList.stream().filter(f -> f.getBall().getBallNo() == secondPrizeBallNo).findFirst().orElse(null);
            winningTicket2.setWinningAmt(secondPrizeAmt);
            Ticket winningTicket3 = soldTicketList.stream().filter(f -> f.getBall().getBallNo() == thirdPrizeBallNo).findFirst().orElse(null);
            winningTicket3.setWinningAmt(thirdPrizeAmt);

            winnerDtlsMap.put(1, winningTicket1);
            winnerDtlsMap.put(2, winningTicket2);
            winnerDtlsMap.put(3, winningTicket3);

            System.out.println("Amount in pot after draw : " + moneyInPot);
        }
        else {
            System.out.println("Draw cannot be done. Tickets sold are less than 3");
        }
    }

    @Override
    public void winners() {
        if (!isDrawStarted) {
            System.out.println("Sorry!! Draw has not started for " + Utility.getUserReadableMonthYearSeries());

            if(!winnerDtlsMap.isEmpty()) {
                System.out.println("\nWinners for last Draw ( "
                        + Utility.getUserReadableMonthYearSeries(winnerDtlsMap.get(1).getTicketSeries()) + " ) are below:");
                displayWinners();
            }
        } else {
            System.out.println(" Winners for " + winnerDtlsMap.get(1).getTicketSeries() + " are: ");
            displayWinners();

           resetSilanisLottery();
        }
    }

    private void displayWinners() {
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
    }

    private void resetSilanisLottery() {
        winnersDisplayed = true;
        soldTicketList = new ArrayList<>();
        BallNumberGenerator.resetBallNumberGenerator();
        isDrawStarted = false;
    }
}
