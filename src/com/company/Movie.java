package com.company;

public class Movie {

    private String movieName;
    private String guessedLetters;
    private String wrongGuesses;

    public Movie(String name){
        this.movieName = name.toLowerCase();
        StringBuilder sb = new StringBuilder(movieName.length());
        for(int i=0; i<movieName.length(); i++){
            if(Character.isLowerCase(movieName.charAt(i)))
                sb.append('_');
            else
                sb.append(movieName.charAt(i));
        }
        this.guessedLetters = sb.toString();
        this.wrongGuesses = "";
    }
    public String getMovieName() {
        return movieName;
    }

    public String getGuessedLetters() {
        return guessedLetters;
    }

    public String getWrongGuesses(){ return wrongGuesses; }

    /**
     * Guesses single letter in movie name
     * @param letter
     * @return true if guess is correct or letter was used earlier
     */
    public boolean guessLetter(Character letter){
        letter = Character.toLowerCase(letter);
        if(!Character.isLowerCase(letter)) {
            System.out.println("Invalid letter.");
            return true;
        }
        if(wrongGuesses.indexOf(letter) >= 0 || guessedLetters.indexOf(letter) >= 0){
            System.out.println("Letter was already guessed.");
            return true;
        }

        boolean guessed = false;
        StringBuilder result = new StringBuilder("");
        for(int i=0; i<movieName.length(); i++){
            if(Character.toLowerCase(movieName.charAt(i)) == letter){
                guessed = true;
                result.append(movieName.charAt(i));
            }
            else{
                result.append(guessedLetters.charAt(i));
            }
        }
        this.guessedLetters = result.toString();
        if(!guessed){
            wrongGuesses += " " + letter;
        }
        return guessed;
    }

    /**
     * checks win conditions
     * @return true if won all letter were guessed
     */
    public boolean checkWin(){
        if(movieName.equals(guessedLetters)){
            return true;
        }
        return false;
    }
}
