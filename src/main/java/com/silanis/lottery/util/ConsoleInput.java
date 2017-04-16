package com.silanis.lottery.util;

import com.silanis.lottery.app.SilanisLottery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Scanner;

/**
 * Created by TARAK on 2017-04-13.
 * Console input with a scanner object
 */
public class ConsoleInput {
    private static final Log logger = LogFactory.getLog(ConsoleInput.class);
    public static Scanner consoleInput;

    private ConsoleInput() {};

    /** Singleton design pattern
     * @return same instance of the scanner class
     */
    public static Scanner getInstance() {
        logger.debug("Start getInstance()");
        if(consoleInput == null){
            consoleInput = new Scanner(System.in);
        }
        logger.debug("End getInstance()");
        return consoleInput;
    }
}
