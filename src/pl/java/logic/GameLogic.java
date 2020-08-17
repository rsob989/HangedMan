package pl.java.logic;

import pl.java.model.Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameLogic {

    public final static int MAX_ERRORS = 8;
    Database db = new Database();
    Random rn = new Random();

    public void game(ContactWithUser cwu){
        char[] word = loadWord(cwu).toUpperCase().toCharArray();
        char[] password = generatePassword(word, cwu);
        hangedMan(word, password, cwu);
    }

    public void hangedMan(char[] word, char[] password, ContactWithUser cwu){
        boolean goodChoice = false;
        boolean endOfTheGame = false;
        boolean win = false;
        boolean theSameChoice;
        int wrongChoice = 0;
        List<Character> choices = new ArrayList<>();

        do {
            showPassword(password, cwu);
            cwu.print("Choose a letter:\n");
            char letter = cwu.getLetterFromUser();
            goodChoice = false;

            for (int i = 0; i < word.length; i++) {
                if (word[i] == letter) {
                    password[i] = word[i];
                    goodChoice = true;
                }
            }
            theSameChoice = isTheSameChoice(choices, letter);

            if(!goodChoice && !theSameChoice){
                wrongChoice++;
                cwu.print("Number of bad choices: " + wrongChoice + "\n");
            }

            if(Arrays.equals(password, word)){
                win = true;
                endOfTheGame = true;
            } else if(wrongChoice >= MAX_ERRORS){
                win = false;
                endOfTheGame = true;
            }

        } while (!endOfTheGame);

        printResult(cwu, win);

    }

    private void printResult(ContactWithUser cwu, boolean win) {
        if(win)
            cwu.print("You won!\n");
        else
            cwu.print("You lose!\n");
    }

    private boolean isTheSameChoice(List<Character> choices, char letter) {
        if(choices.contains(letter)){
            return true;
        } else{
            choices.add(letter);
            return false;
        }
    }

    private void showPassword(char[] password, ContactWithUser cwu) {
        cwu.print("Password to guess:\n");
        for (char c : password) {
            cwu.print(String.valueOf(c));
        }
        cwu.print("\n");
    }


    private char[] generatePassword(char[] word, ContactWithUser cwu){
        char[] password = new char[word.length];
        for (int i = 0; i < word.length; i++) {
            if(Character.isWhitespace(word[i])){
                password[i] = ' ';
            } else
                password[i] = '*';
        }
        return password;
    }

    private String loadWord(ContactWithUser cwu) {
        try{
            db.loadTheListOfWords();
        } catch (IOException e){
            cwu.print("Load words database error!\n");
        }
        String[] words = db.getWordsToGuess();
        return words[rn.nextInt(words.length)];
    }


}
