package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Movie movie;
    private int score;
    private List<String> moviesList = new ArrayList<>();
    private Scanner input;
    public Game(Scanner input){
        this.input = input;
    }

    /**
     * loadListOfMovies loads list movies from movies.txt in parent directory
     * @return true if list of movies was loaded correct
     */
    public boolean loadListOfMovies(){
        try{
            File parentDir = new File(".").getParentFile();
            File moviesListFile = new File(parentDir, "movies.txt");
            Scanner fileInput = new Scanner(moviesListFile);
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                moviesList.add(line);
            }
            fileInput.close();
            return true;
        }catch (FileNotFoundException e){
            System.out.println("File with movies not found.");
        }
        return false;
    }

    /**
     * processes single game
     */
    public void startGame() {
        this.score = 10;
        if (moviesList.size() <= 0) {
            return;
        }
        int movieIndex = (int) (Math.random() * moviesList.size());
        movie = new Movie(moviesList.get(movieIndex));

        System.out.println("You are guessing: " + movie.getGuessedLetters());
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.length() != 1) {
                System.out.println("Input should be single letter.");
                continue;
            }
            Character letter = line.charAt(0);
            if (movie.guessLetter(letter) == false) {
                score--;
                if (score <= 0) {
                    System.out.println("You lost.");
                    System.out.println("Correct answer was '" + movie.getMovieName() + "'.");
                    return;
                }
            }
            if (movie.checkWin()) {
                System.out.println("You won.");
                System.out.println("You have guessed '" + movie.getMovieName() + "' correctly.");
                return;
            }
            System.out.println("You are guessing: " + movie.getGuessedLetters());
            System.out.println("You have guessed (" + (10-score) + ") wrong:" + movie.getWrongGuesses());
        }
    }

}
