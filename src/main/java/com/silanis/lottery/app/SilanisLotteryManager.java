package com.silanis.lottery.app;

import com.silanis.lottery.handlerequest.Command;
import com.silanis.lottery.handlerequest.Draw;
import com.silanis.lottery.handlerequest.Purchase;
import com.silanis.lottery.handlerequest.Winners;
import com.silanis.lottery.util.ConsoleInput;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by TARAK on 2017-04-13.
 *
 * Class with main method to run the Application
 */
public class SilanisLotteryManager {
    Scanner consoleInput;
    LotteryInterface lotteryApp;

    public SilanisLotteryManager() {
        consoleInput = ConsoleInput.getInstance();
        lotteryApp = new SilanisLottery();
    }

    public static void main(String args[]) {
        SilanisLotteryManager tommy = new SilanisLotteryManager();

        tommy.runApplication();
    }

    /**
     * This method is used to run the Lottery Application.
     *
     * @return false if Exit option is selected, otherwise return true.
     */
    public void runApplication() {
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
    }

    /**
     * This method is used to process command for the option selected from displayed menu.
     *
     * @param optionSelected
     *
     * @return false if Exit option is selected, otherwise return true.
     */
    public boolean processCommand(int optionSelected) {
        switch (optionSelected) {
            case 1: {
                Command purchase = new Purchase(lotteryApp);
                purchase.execute();
                return true;
            }
            case 2: {
                Command draw = new Draw(lotteryApp);
                draw.execute();
                return true;
            }
            case 3: {
                Command winners = new Winners(lotteryApp);
                winners.execute();
                return true;
            }
            case 4: return false;
            default:
                System.out.println("Please choose correct option");
                return true;
        }
    }
}