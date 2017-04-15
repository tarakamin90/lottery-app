package com.silanis.lottery.app;

/**
 * Created by TARAK on 2017-04-13.
 */
public interface LotteryInterface {
    /**
     * This method is used to purchase a new ticket for the draw by providing
     * first name and displays the ball number selected on the screen. It saves
     * details of user i.e user name, ticket series, ball number selected and
     * add ticket amount i.e 10$ in total money whenever new ticket is
     * purchased.
     *
     * @return true to ensure that method is completely executed.
     */

    public void purchase();

    /**
     * This method is used to start a draw and contains logic for choosing
     * winners of Draw and calculation of their prize money.
     *
     * @return true to ensure that method is completely executed.
     */
    public void draw();

    public void winners();
}
