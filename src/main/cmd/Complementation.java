package main.cmd;

import main.algorithm.DfaAlgorithm;
import main.entity.Dfa;

import static main.algorithm.Main.printDfaOnScreen;
import static main.algorithm.Main.readDfa;

public class Complementation {
    public static void main(String[] args) {
        if (args.length != 1 ) {
            System.out.println("Invalid parameters.");
            printInstruction();
            System.exit(-1);
        }
        Dfa dfa1;
        DfaAlgorithm dfaAlgorithm = new DfaAlgorithm();
        dfa1 = readDfa(args[0]);
        printDfaOnScreen(dfaAlgorithm.complementationComputation(dfa1));
    }

    public static void printInstruction() {
        System.out.println("Usage:java -jar complementation.jar fileName.txt");
    }
}
