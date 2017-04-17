package com.silanis.lottery.app;

import java.util.Map;

/**
 * Created by TARAK on 2017-04-13.
 *
 * Lottery interface containing the basic method needed to implement
 *
 */
public interface LotteryInterface {

    public Ticket purchase(String name);

    public Double draw();

    public Map<Integer, Ticket> winners();
}
