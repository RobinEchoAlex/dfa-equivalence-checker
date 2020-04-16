package main.java;

import main.entity.Dfa;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2 || args.length!=3) {
            System.out.println("Invalid parameters");
        }
        Main main = new Main();

        if (args.length == 3) {
            main.readDfa(args[1]);
            switch (args[0]) {
                case "-complementation":
                    break;
                case "-intersection":
                    main.readDfa(args[2]);
                    break;
                case "-sd":
                    main.readDfa(args[2]);
                    break;
                case "-emptyness":
                    break;
                case "-equivalence":
                    main.readDfa(args[2]);
                    break;
            }
        } else {
            main.readDfa(args[0]);
            main.readDfa(args[1]);
        }
    }

    public void readDfa(String filePath) {

    }

    public void printDfaOnScreen(Dfa dfa) {

    }
}
