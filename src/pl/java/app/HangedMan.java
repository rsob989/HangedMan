package pl.java.app;

import pl.java.logic.ContactWithUser;
import pl.java.logic.GameLogic;
import pl.java.model.Database;

import java.io.IOException;
import java.util.InputMismatchException;

public class HangedMan {
    public static void main(String[] args) {
        ContactWithUser cwu = new ContactWithUser();
        mainLoop(cwu);

    }

    private static void mainLoop(ContactWithUser cwu) {
        Option option = null;
        do{
            option = getOption(cwu);
            switch(option){
                case PLAY:
                    play(cwu);
                    break;
                case EXIT:
                    exit(cwu);
            }
        } while (option!= Option.EXIT);
    }

    private static void play(ContactWithUser cwu){
        GameLogic gl = new GameLogic();
        gl.game(cwu);
    }

    private static void exit(ContactWithUser cwu){
        cwu.print("See you soon\n");
        cwu.closeScanner();
    }

    private static Option getOption(ContactWithUser cwu) {
        Option option = null;
        boolean correctNumber = false;
        while(!correctNumber)
        try {
            cwu.print("Choose an option:\n");
            for(Option o: Option.values()){
                cwu.print(o.toString() + "\n");
            }
            option = Option.numberIntoOption(cwu.getNumberFromUser());
            correctNumber = true;
        } catch (ArrayIndexOutOfBoundsException e){
            cwu.print("Wrong option. Try again!\n");
        } catch (InputMismatchException e){
            cwu.print("Wrong data type. Choose a number. Try again!\n");
        }
        return option;
    }

    enum Option{
        PLAY(0, "Start game"), EXIT(1, "Exit");

        private int number;
        private String description;

        Option(int number, String description) {
            this.number = number;
            this.description = description;
        }

        @Override
        public String toString() {
            return number + " - " + description;
        }

        public static Option numberIntoOption(int number){
            return Option.values()[number];
        }
    }


}
