package pl.java.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Database {

    public static final String wordsFile = "words.txt";
    private String[] wordsToGuess;

    public void loadTheListOfWords() throws IOException {
        try(
                FileReader fr = new FileReader(new File(wordsFile));
                BufferedReader br = new BufferedReader(fr);
                ){
                String words = br.readLine();
                wordsToGuess = words.split(";");
        }
    }

    public String[] getWordsToGuess() {
        return wordsToGuess;
    }
}
