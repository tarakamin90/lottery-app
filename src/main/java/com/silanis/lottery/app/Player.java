package com.silanis.lottery.app;

/**
 * Created by TARAK on 2017-04-13.
 *
 * Stores the information of the player
 */
public class Player {
    // stores the name of the player
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
