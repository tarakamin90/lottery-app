package com.silanis.lottery.test;

import com.silanis.lottery.app.LotteryInterface;
import com.silanis.lottery.app.SilanisLottery;
import com.silanis.lottery.app.Ticket;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by TARAK on 2017-04-16.
 */
public class SilanisLotteryTest {

    /**
     * Testcase for checking the amount in pot after draw
     */
    @Test
    public void testcaseMoneyInPotAfterDraw() {
        LotteryInterface silanisLottery = new SilanisLottery();

        silanisLottery.purchase("Player1");
        silanisLottery.purchase("Player2");
        silanisLottery.purchase("Player3");
        silanisLottery.purchase("Player4");

        // money in pot = 240

        // Expected 120, For Config.AVAIL_PRIZE_MONEY_PERCENTAGE = 50
        Double moneyInPotAfterDraw = silanisLottery.draw();

        Assert.assertEquals(moneyInPotAfterDraw.doubleValue(),120.0, 0.0);
    }

    /**
     * Testcase for checking the first prize amount
     */
    @Test
    public void testcaseFirstPrizeAmt() {
        LotteryInterface silanisLottery = new SilanisLottery();

        silanisLottery.purchase("Player1");
        silanisLottery.purchase("Player2");
        silanisLottery.purchase("Player3");
        silanisLottery.purchase("Player4");

        // money in pot = 240

        silanisLottery.draw(); // 120

        Map<Integer, Ticket> winnerDtlsMap = silanisLottery.winners();

        int firstPrizeAmt = winnerDtlsMap.get(1).getWinningAmt(); // 90

        Assert.assertEquals(firstPrizeAmt, 90);
    }

    /**
     * Testcase for checking the second prize amount
     */
    @Test
    public void testcaseSecondPrizeAmt() {
        LotteryInterface silanisLottery = new SilanisLottery();

        silanisLottery.purchase("Player1");
        silanisLottery.purchase("Player2");
        silanisLottery.purchase("Player3");
        silanisLottery.purchase("Player4");

        // money in pot = 240

        silanisLottery.draw(); // 120

        Map<Integer, Ticket> winnerDtlsMap = silanisLottery.winners();

        int secondPrizeAmt = winnerDtlsMap.get(2).getWinningAmt(); // 18

        Assert.assertEquals(secondPrizeAmt, 18);
    }

    /**
     * Testcase for checking the third prize amount
     */
    @Test
    public void testcaseThirdPrizeAmt() {
        LotteryInterface silanisLottery = new SilanisLottery();

        silanisLottery.purchase("Player1");
        silanisLottery.purchase("Player2");
        silanisLottery.purchase("Player3");
        silanisLottery.purchase("Player4");

        silanisLottery.draw(); // 120

        Map<Integer, Ticket> winnerDtlsMap = silanisLottery.winners();

        int thirdPrizeAmt = winnerDtlsMap.get(3).getWinningAmt(); // 12

        Assert.assertEquals(thirdPrizeAmt, 12);
    }
}
