package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
	    Game game = new Game(input);
        if(!game.loadListOfMovies()){
            System.out.println("Couldn't load file with movies.");
            return;
        }
        while(true) {
            game.startGame();
            String yesOrNo = "";
            System.out.println("Do you want to start new game: [Y/N]");
            while(input.hasNextLine()){
                yesOrNo = input.nextLine();
                if(yesOrNo.toLowerCase().equals("n") || yesOrNo.toLowerCase().equals("y"))
                    break;
                System.out.println("Do you want to start new game: [Y/N]");
            }
            if(yesOrNo.toLowerCase().equals("n")) break;
        }
        input.close();
    }
}
