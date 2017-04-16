package com.silanis.lottery.app;

import com.silanis.lottery.util.Utility;

/**
 * Created by TARAK on 2017-04-13.
 *
 * Stores the ticket information
 */
public class Ticket {
    private Player player; // player who owns the tickets
    private String ticketSeries; // ticket series for the Lottery
    private String ticketNo; // ticket no.
    private Ball ball; // ball no.
    private int winningAmt; // winning amt if the ball no wins the lottery

    public Ticket(Player player, Ball ball) {
        this.player = player;
        this.ball = ball;
        this.ticketSeries = Utility.getMonthYearSeriesNo();
        this.ticketNo = this.ticketSeries + "-" + ball.getBallNo();
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Player getPlayer() {
        return player;
    }

    public String getTicketSeries() {
        return ticketSeries;
    }

    public int getWinningAmt() {
        return winningAmt;
    }

    public void setWinningAmt(int winningAmt) {
        this.winningAmt = winningAmt;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getTicketDtls() {
        return "Name:" + player.getName() + ", Ticket No.:" + ticketNo + ", Ball No.:" + ball.getBallNo();
    }
}
