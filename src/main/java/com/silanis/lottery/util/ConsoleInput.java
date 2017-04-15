package com.silanis.lottery.util;

import java.util.Scanner;

/**
 * Created by TARAK on 2017-04-13.
 */
public class ConsoleInput {
    public static Scanner consoleInput;

    private ConsoleInput() {};

    public static Scanner getInstance() {
        if(consoleInput == null){
            consoleInput = new Scanner(System.in);
        }
        return consoleInput;
    }
}
