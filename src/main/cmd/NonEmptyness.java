package main.cmd;

import main.algorithm.DfaAlgorithm;
import main.entity.Dfa;

import static main.algorithm.CommandLineIo.readDfa;

public class NonEmptyness {
    public static void main(String[] args) {
        if (args.length != 1 ) {
            System.out.println("Invalid parameters.");
            printInstruction();
            System.exit(-1);
        }
        Dfa dfa1;
        DfaAlgorithm dfaAlgorithm = new DfaAlgorithm();
        dfa1 = readDfa(args[0]);
        if (dfaAlgorithm.emptinessVerification(dfa1, DfaAlgorithm::printRoute)) {
            System.out.println("language empty");
        } else {
            System.out.println("language non-empty");
        }
    }

    public static void printInstruction() {
        System.out.println("Usage:java -jar nonEmptyness.jar fileName1.txt");
    }
}
