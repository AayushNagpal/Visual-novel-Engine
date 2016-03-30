/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnepcport;
import java.util.*;
import java.io.*;

/**
 *
 * @author Yang
 */
public class VNEPCPort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner scanner = new Scanner(System.in);
        String inputBuffer = null;
        
        System.out.println("PLEASE ENTER YOUR GAME SCRIPT DIR > ");
        inputBuffer = scanner.nextLine();
        
        GameController newGame = new GameController();
        
        newGame.openGame(inputBuffer);
        newGame.runGame();
    }
    
}
