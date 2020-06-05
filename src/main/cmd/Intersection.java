package main.cmd;

import main.algorithm.DfaAlgorithm;
import main.algorithm.CommandLineIo;
import main.entity.Dfa;

import static main.algorithm.CommandLineIo.printDfaOnScreen;
import static main.algorithm.CommandLineIo.readDfa;

public class Intersection {
    public static void main(String[] args) {
        if (args.length != 2 ) {
            System.out.println("Invalid parameters.");
            printInstruction();
            System.exit(-1);
        }
        Dfa dfa1;
        Dfa dfa2;
        DfaAlgorithm dfaAlgorithm = new DfaAlgorithm();
        dfa1 = readDfa(args[0]);
        dfa2 = readDfa(args[1]);
        CommandLineIo.checkAlphabetEquivalence(dfa1,dfa2);
        printDfaOnScreen(dfaAlgorithm.intersectionComputation(dfa1, dfa2));
    }

    public static void printInstruction() {
        System.out.println("Usage:java -jar intersection.jar.jar fileName1.txt fileName2.txt");
    }
}
