package com.silanis.lottery.app;

import com.silanis.lottery.commandhandler.Command;
import com.silanis.lottery.commandhandler.Draw;
import com.silanis.lottery.commandhandler.Purchase;
import com.silanis.lottery.commandhandler.Winners;
import com.silanis.lottery.util.ConsoleInput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by TARAK on 2017-04-13.
 *
 * Class with main method to run the Application
 */
public class SilanisLotteryManager {
    private static final Log logger = LogFactory.getLog(SilanisLotteryManager.class);
    private Scanner consoleInput;
    private LotteryInterface lotteryApp;

    public SilanisLotteryManager() {
        consoleInput = ConsoleInput.getInstance();
        lotteryApp = new SilanisLottery();
    }

    public static void main(String args[]) {
        SilanisLotteryManager tommy = new SilanisLotteryManager();

        tommy.runApplication();
    }

    /**
     * This method is used to run the console UI for Lottery Application.
     */
    public void runApplication() {
        logger.debug("Start runApplication()");

        System.out.println("Welcome to Silanis LotteryApp !!!");
        boolean chooseOptionFlag = true;
        while (chooseOptionFlag)
        {
            System.out.println("1. Purchase New Ticket? ");
            System.out.println("2. Draw ");
            System.out.println("3. Print winning tickets ");
            System.out.println("4. Exit ");
            int optionSelected;
            try {
                 optionSelected = consoleInput.nextInt();
            } catch(InputMismatchException e)
            {
                //add logger here
                consoleInput.next();
                optionSelected = 0;
            }
            chooseOptionFlag = processCommand(optionSelected);
        }
        consoleInput.close();

        logger.debug("End runApplication()");
    }

    /**
     * Method processes the selected option and calls appropriate command
     *
     * @param optionSelected
     *
     * @return false if Exit option is selected, otherwise return true.
     *
     * Used Command design pattern
     */
    public boolean processCommand(int optionSelected) {
        logger.debug("Start processCommand()");

        boolean result = true;
        switch (optionSelected) {
            case 1: {
                Command purchase = new Purchase(lotteryApp);
                purchase.execute();
                break;
            }
            case 2: {
                Command draw = new Draw(lotteryApp);
                draw.execute();
                break;
            }
            case 3: {
                Command winners = new Winners(lotteryApp);
                winners.execute();
                break;
            }
            case 4: result = false;
                break;
            default:
                System.out.println("Please choose correct option");
        }
        logger.debug("End processCommand()");
        return result;
    }
}