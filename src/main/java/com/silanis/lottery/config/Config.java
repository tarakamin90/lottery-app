package com.silanis.lottery.config;

/**
 * Created by TARAK on 2017-04-14.
 * Configuration file for SilanisLottery
 */
public class Config {
    public static final int BALL_NUMBER_RANGE_MAX = 50; // Max balls in the lottery
    public static final double INITIAL_AMT_IN_POT = 200.0; // Initial pot amount

    public static int FIRST_PRIZE_PERCENTAGE = 75; // First Prize share in percentage
    public static int SECOND_PRIZE_PERCENTAGE = 15; // Second Prize share in percentage
    public static int THIRD_PRIZE_PERCENTAGE = 10; // Third Prize share in percentage

    public static int TICKET_PRICE = 10; // Price per ticket

    // minimum value 3
    public static int MIN_TICKET_SOLD_TO_ALLOW_DRAW = 3; // Minimum tickets sold to allow a draw

    public static int AVAIL_PRIZE_MONEY_PERCENTAGE = 50; // Available prize money in percentage for given amount in pot
}
