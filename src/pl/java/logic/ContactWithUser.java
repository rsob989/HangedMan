package pl.java.logic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactWithUser {

    Scanner scan = new Scanner(System.in);

    public int getNumberFromUser() {
        try {
            return scan.nextInt();
        } finally {
            scan.nextLine();
        }
    }

    public void print(String info) {
        System.out.print(info);
    }

    public void closeScanner(){
        scan.close();
    }

    public char getLetterFromUser() {
        return scan.nextLine().toUpperCase().charAt(0);
    }

}
