/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviegame;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 *
 * @author rbowlen
 */
public class Game {
    
    
    /**
     * Reads movie.txt from the project folder and returns each title within 
     * as an ArrayList.
     * @return ArrayList<String> movieList
     */
    private ArrayList<String> listMovies() {
        // To store each movie title in movies.txt
        ArrayList<String> movieList = new ArrayList<>();
        
        try {
            Scanner scan = new Scanner(new File("movies.txt"));
 
            while(scan.hasNextLine()) {
                movieList.add(scan.nextLine());
            } 
            
        } catch(FileNotFoundException e) {
                    System.out.println("Movies text file was not found. Please"
                            + " add and try again.");
                    }
        return movieList;
    }
    
    /**
     * Selects a movie randomly from the movies text file.
     * @return String randomMovie
     */
    private String getRandMovie() {
        ArrayList<String> movieList = listMovies();
        int index = (int)(Math.random() * movieList.size());
        return movieList.get(index);
        
    }
    
    /**
     * Replaces all movie titles with underscores
     * @param movieTitle
     * @return 
     */
    public String obscureTitle(String movieTitle, String correctLetters) {
        if(correctLetters == "") {
            return movieTitle.replaceAll("[a-zA-Z]", "_");
        } else {
            return movieTitle.replaceAll("[a-zA-Z&&[^" + correctLetters + "]]", "_");
        }
    }
    
    public String inputLetter() {
        System.out.println("Guess a letter:");
        //Create a scanner to get user's guess.
        Scanner scan = new Scanner(System.in);
        //Store user guess for processing
        String letter = scan.nextLine().toLowerCase();
        
        if(!letter.matches("[a-z]")) {
            System.out.println("This is not a letter. Try again.");
            return inputLetter();
        }
        
        return letter;
        
    }
    
    /**
     * Checks if the user's guess was correct
     * @param movieTitle
     * @param guess
     * @return boolean
     */
    public boolean checkGuess(String movieTitle, String guess) {
        return movieTitle.contains(guess);
    }
    
    public void initGame() {
        // Store correct guesses
        String correctLetters = "";
        // Store incorrect guesses
        String wrongLetters = "";
        // User chances
        int livesLeft = 3;
        // Get a movie title
        String movie = getRandMovie();
        // Game over
        boolean gameOver = false;
        
        while(!gameOver) {
            // Obscure the title
            System.out.println(obscureTitle(movie, correctLetters));


            // Get a user guess
            String guess = inputLetter();
            //Check to see if the users guess is in the title
            if(checkGuess(movie, guess)) {
                correctLetters += guess;
                System.out.println("Correct!");
            } else {
                wrongLetters += guess;
                System.out.println("Sorry, try again!");
                livesLeft--;
            }
        }   
        
       
        
    }
    
}
