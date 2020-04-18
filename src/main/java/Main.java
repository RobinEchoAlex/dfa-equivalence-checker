package main.java;

import main.entity.Dfa;
import main.entity.Transition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2 && args.length!=3) {
            System.out.println("Invalid parameters");
            System.exit(-1);
        }
        Main main = new Main();
        Dfa dfa1;
        Dfa dfa2;
        DfaAlgorithm dfaAlgorithm = new DfaAlgorithm();

        if (args[0].startsWith("-")) {
            dfa1 = main.readDfa(args[1]);
            switch (args[0]) {
                case "-complementation":
                    main.printDfaOnScreen(dfaAlgorithm.complementationComputation(dfa1));
                    break;
                case "-intersection":
                    dfa2 = main.readDfa(args[2]);
                    main.printDfaOnScreen(dfaAlgorithm.intersectionComputation(dfa1, dfa2));
                    break;
                case "-sd":
                    dfa2 = main.readDfa(args[2]);
                    break;
                case "-emptyness":
                    break;
                case "-equivalence":
                    dfa2 = main.readDfa(args[2]);
                    main.printDfaOnScreen(dfa1);
                    break;
                default:
                    System.out.println("Invalid parameters");
            }
        } else {
            dfa1 = main.readDfa(args[0]);
            dfa2 = main.readDfa(args[1]);
        }
    }

    public Dfa readDfa(String filePath) {
        File file = new File(filePath);

        try {
            Scanner sc = new Scanner(file);

            int numberOfStates = sc.nextInt();
            List<String> states = new ArrayList<>();
            for (int i = 0; i < numberOfStates; i++) {
                String stateName = sc.next();
                states.add(stateName);
            }


            int numberOfAlphabet = sc.nextInt();
            List<String> alphabets = new ArrayList<>();
            for (int i = 0; i < numberOfAlphabet; i++) {
                String alphabet = sc.next();
                alphabets.add(alphabet);
            }

            List<Transition> transitions = new ArrayList<>();
            for (int i = 0; i < numberOfStates; i++) {
                for (int j = 0; j< numberOfAlphabet; j++) {
                    Transition transition = new Transition(states.get(i), alphabets.get(j), sc.next());
                    transitions.add(transition);
                }
            }

            String startState = sc.next();
            int numberOfFinalStates = sc.nextInt();
            List<String> finalStates = new ArrayList<>();
            for (int i = 0; i < numberOfFinalStates; i++) {
                finalStates.add(sc.next());
            }

            return new Dfa(states, startState, finalStates, transitions, alphabets);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printDfaOnScreen(Dfa dfa) {
        System.out.println(dfa.getStates().size());
        for (String s: dfa.getStates()) {
            System.out.print(s+" ");
        }
        System.out.println();

        System.out.println(dfa.getAlphabet().size());
        for (String s: dfa.getAlphabet()) {
            System.out.print(s+" ");
        }
        System.out.println();

        for (int i =0 ;i<dfa.getStates().size();i++) {
            for (int j = 0; j< dfa.getAlphabet().size(); j++) {
                System.out.print(dfa.getTransitions().get(i*dfa.getAlphabet().size()+j).toString()+ " ");
            }
            System.out.println();
        }

        System.out.println(dfa.getStartState());

        System.out.println(dfa.getFinalStates().size());
        for (String s: dfa.getFinalStates()) {
            System.out.print(s+" ");
        }
    }
}
