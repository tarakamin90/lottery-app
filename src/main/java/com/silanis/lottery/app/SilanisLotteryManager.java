package com.silanis.lottery.app;

import com.silanis.lottery.commandhandler.Command;
import com.silanis.lottery.commandhandler.Draw;
import com.silanis.lottery.commandhandler.Purchase;
import com.silanis.lottery.commandhandler.Winners;
import com.silanis.lottery.util.ConsoleInput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

    private boolean isDrawStarted = false; // stores the status of the draw
    private boolean winnersDisplayed = false; // stores the status of the winners announcement


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
                String strInput = consoleInput.nextLine();
                while("".equals(strInput)) {
                    strInput = consoleInput.nextLine();
                }
                optionSelected = Integer.parseInt(strInput);
            } catch (NumberFormatException e) {
                logger.error("Error in runApplication(): " + e);
                optionSelected = 0;
            }
            chooseOptionFlag = processCommand(optionSelected);
        }
        consoleInput.close();

        logger.debug("End runApplication()");
    }

    /**
     * Method processes the selected option and calls appropriate command
     * @param optionSelected
     * @return false if Exit option is selected, otherwise return true.
     *
     * Used Command design pattern
     */
    public boolean processCommand(int optionSelected) {
        logger.debug("Start processCommand()");

        boolean result = true;
        switch (optionSelected) {
            case 1: {
                if(!isDrawStarted) {
                    Command purchase = new Purchase(lotteryApp);
                    purchase.execute();
                }
                else {
                    System.out.println("Cannot purchase a ticket. Draw is done. Please announce the winners to start new Lottery.");
                }
                break;
            }
            case 2: {
                if(!isDrawStarted) {
                    Command draw = new Draw(lotteryApp);
                    this.isDrawStarted = draw.execute();
                } else {
                    System.out.println("Draw is done. Please announce the winners to start new Lottery.");
                }
                break;
            }
            case 3: {
                if (isDrawStarted) {
                    Command winners = new Winners(lotteryApp);
                    winners.execute();

                    isDrawStarted = false;
                } else {
                    System.out.println("Cannot display winners. Please perform the draw first.");
                }
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