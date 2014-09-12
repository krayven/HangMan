/*
Kevin Ray
CSIS 2450
Assignment A01
HangMan in the console
send word as arguments in running program or can be hardcoded

 */

package hangman;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Kevin Ray
 * @version     1.01
 */
public class HangMan {
    protected static int lives = 6;
    private static ArrayList<String> guessedLetters = new ArrayList<>();
    //hard code word here or pass as argument
    private static String hangmanWord = "";
    protected static boolean gameOver = false;

    private static void show_Word(){
        boolean test;
        System.out.println("Word to Guess:");

        for (int cnt=0; cnt<hangmanWord.length(); cnt++){
            System.out.print(" ");
            test = false;
            if (guessedLetters.contains(hangmanWord.substring(cnt, cnt+1).toUpperCase())){
                test = true;
            }
            if (test){
                 System.out.print(hangmanWord.charAt(cnt));
            }else{
               System.out.print("_"); 
            }        
        }
        System.out.println("");
    }
    
    private static void add_Letter(String guess){
        guessedLetters.add(guess.substring(0,1).toUpperCase());
    }
    
    /**
     * Show hangman results based on lives left 
     * <p>
     * Uses lives global variable to show hangman results,  
     * displays in text / ascii style
     * <p>
     * 
     * @return void
     */
    
    private static void show_Hangman(){
        System.out.println("Number of Lives: "+lives);
        System.out.println("");
        System.out.println("  ;--------,           ");
        if (lives <=5){
            System.out.println("  |        *           ");
        }else{
            System.out.println("  |");
        }
        if (lives<=4){
            if(lives<=2){
                System.out.println("  |       /|\\");
            }else if (lives <=3){
                System.out.println("  |       /|");
            }else if (lives<=4){
                System.out.println("  |       /");
            }
        }else{
            System.out.println("  |");
        }
        if (lives <=1){
            if (lives<=0){
                System.out.println("  |       / \\");
            }else if(lives<=1){
                System.out.println("  |       /");
            }
        }else{
            System.out.println("  |");
        }        
        System.out.println("  |");
        System.out.println("  |");
        System.out.println(" _|____________________");
        System.out.println("");
    }
    
    private static void add_Space(){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    private static void getGuess(){
        Scanner input = new Scanner(System.in);
        System.out.print("What is your next letter to Guess:");
        String guess = input.next();
        //error check to make sure a letter?()
        char c = guess.charAt(0);
        if (Character.isLetter(c)){
            if (!isLetterIn(guess)){
                //duplicates guesses do not cost twice - be nice!
                if (!guessedLetters.contains(guess.toUpperCase())){
                    lives --;
                }
            }
            add_Letter(guess);
        }else{
            System.out.println("Sorry that was a number!  Try again!");
        }
    }
    private static boolean isLetterIn(String guess){
        return hangmanWord.toUpperCase().contains(guess.toUpperCase());
    }
    
    private static boolean has_Won(){
        if (lives > 0){
            //see if guessed word
            int test = 0;
            for (int cnt=0; cnt< hangmanWord.length(); cnt++){
                if (guessedLetters.contains(hangmanWord.substring(cnt, cnt+1).toUpperCase())){
                    test++;
                }
            }
            if (test == hangmanWord.length()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        if (hangmanWord.length()==0){
            hangmanWord = args[0];
        }
        do{
            show_Hangman();
            show_Word();
            System.out.println("");
            getGuess();
            
            add_Space();
            if (lives<=0){
                gameOver = true;
                System.out.println("");
                System.out.println("Sorry you have been hung!  Better luck next time!");
                System.out.println("");
                System.out.println("The Word was "+hangmanWord.toUpperCase());
            }
            if (has_Won()){
                System.out.println("");
                System.out.println("Congratulations! You won! ");
                System.out.println("You guessed the word was "+hangmanWord.toUpperCase());
                
                gameOver = true;
            }
            
        }while(!gameOver);
        add_Space();
    }
    
}
