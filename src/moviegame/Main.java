/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.FileNotFoundException;

/**
 *
 * @author carbafra
 */
public class Main {

    /**
     * @param args the command line arguments
     */
      public static void main(String[] args) throws FileNotFoundException {
         Game movie = new Game();
        System.out.println("Movie Gussing Game, start gussing letters to win"); 
        System.out.println("After 10 incorrect guesses, you lose");
        System.out.println("Good Luck!\n");
        movie.initGame();
    }
    
}
