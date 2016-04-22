/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnepcport;
import java.util.*;
import java.io.*;

/**
 * THIS IS A PC PORT PROTOTYPE ONLY
 * IT ONLY SUPPORT TWO OPTIONS AT A TIME
 * IT PUT WHAT IS IN THE SCRIPT TO CONSOLE
 * IT SHOULD BE REPLACED BY AN ANDROID ACTIVITY
 * @author Yang
 */
public class Text {
    private String normalText = null;
    private String optionText1 = null;
    private String optionText2 = null;
    private String userInput = null;
    private int beginIdx;
    private int endIdx;
    private int skipLine;
    
    Scanner scanner = new Scanner(System.in);
    
    public int displayText(int textType, String textContent) {
        if (textType == 0) {
            /* display regular plain text */
            System.out.println(textContent);
            return 0;
        } else {
            optionText1 = textContent.substring(0, (textContent.indexOf("(")));
            optionText2 = textContent.substring((textContent.indexOf(";")) + 2, (textContent.indexOf("(", textContent.indexOf("(") + 1)));
            System.out.println("OPTION 1: " + optionText1);
            System.out.println("OPTION 2: " + optionText2);
            
            userInput = scanner.nextLine();
            
            if (Integer.parseInt(userInput) == 1) {
                beginIdx = textContent.indexOf("(");
                endIdx = textContent.indexOf(")");
                userInput = textContent.substring(beginIdx + 1, endIdx);
                skipLine = Integer.parseInt(userInput);
            } else if (Integer.parseInt(userInput) == 2) {
                beginIdx = (textContent.indexOf("(", textContent.indexOf("(") + 1));
                endIdx = (textContent.indexOf(")", textContent.indexOf(")") + 1));
                userInput = textContent.substring(beginIdx + 1, endIdx);
                skipLine = Integer.parseInt(userInput);
            } else {
                System.out.println("WRONG CHOICE");
                return 0;
            }
            return skipLine;
        }
    }
}
