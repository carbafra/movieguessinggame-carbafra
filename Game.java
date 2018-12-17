/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author carbafra
 */
public class Game {
  
    /**
     * Method to pick a movie from the list
     * @return 
     */
     boolean won = false; 
     String randMovie;
    public ArrayList<String> readMovies(){
        ArrayList<String> movies = new ArrayList<>(); 
       
        try {
            Scanner scan = new Scanner(new File("movieList.txt"));
            
            while(scan.hasNextLine()){
                movies.add(scan.nextLine()); 
                
            }      
        } catch (FileNotFoundException e) {
            out.println("A movies file was not found. Please put a file called movieList.txt in your "
                    + "project folder. "); 
        }
        
        return movies; 
        
    }
    
    private String getRandomMovie() {
        ArrayList<String>movies = readMovies();
        int movieIndex = (int)(Math.random()*((movies.size() - 0) + 1)) + 0;  
        return movies.get(movieIndex); 
    }
    
    private String obscureMovie(String movieTitle, String correctLetters) {
        if (correctLetters == "") {
            return movieTitle.replaceAll("[a-zA-Z]", "_");            
        } else{
            return movieTitle.replaceAll("[a-zA-Z&&[^" + correctLetters + "]]", "_");
        }
    }
    
    public String inputLetter() {
        System.out.println("Guess a letter:"); 
        Scanner scan = new Scanner(System.in); 
        
        String letter = scan.next().toLowerCase();
        
        if(!letter.matches("[a-z]")){
            out.println("This is not a letter"); 
            return inputLetter();
        }
        
        return letter; 
    }
    
    /**
     * Checks if user guess is in movie title
     * @param movieTitle
     * @param guess
     * @return boolean
     */
    public boolean checkGuess(String movieTitle, String guess){
        return movieTitle.contains(guess); 
    }
    
    public void gameOver(){
        if(won){
            System.out.println("You Won! Congrats");
        }else{
            System.out.println("You lost :/ try building next time"); 
        }
    }
    
    public void initGame(){
        boolean gameOver = false; 
        String movie = getRandomMovie();
        String wrongLetters = "";
        String correctLetters = "";   
        int badGuesses = 0; 
       
        while (!gameOver) {            
                     
            System.out.printf("You have guessed %d wrong letters\n", badGuesses);
            System.out.println("You are guessing: "+obscureMovie(movie, correctLetters));            
            
            String guess = inputLetter();
            
            if(correctLetters.contains(guess) || wrongLetters.contains(guess)){
                System.out.println("You already guessed that letter"); 
                guess = inputLetter();
            }
            
            if(checkGuess(movie, guess)){
                correctLetters += guess;
            }else{
                wrongLetters += guess; 
                badGuesses += 1; 
            }
            
            if (!obscureMovie(movie, correctLetters).contains("_")){
                gameOver = true; 
                won = true; 
                System.out.println(obscureMovie(movie, correctLetters)); 
                gameOver(); 
            }else if(badGuesses == 10){
                gameOver = true; 
                gameOver(); 
            }
        }
    }
}

