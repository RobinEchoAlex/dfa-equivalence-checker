package main.algorithm;

import main.entity.Dfa;
import main.entity.State;
import main.entity.Transition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2 && args.length != 3) {
            System.out.println("Invalid parameters");
            System.exit(-1);
        }
        Dfa dfa1;
        Dfa dfa2;
        DfaAlgorithm dfaAlgorithm = new DfaAlgorithm();

        if (args[0].startsWith("-")) {
            dfa1 = readDfa(args[1]);
            switch (args[0].toLowerCase()) {
                case "-complementation":
                    printDfaOnScreen(dfaAlgorithm.complementationComputation(dfa1));
                    break;
                case "-intersection":
                    dfa2 = readDfa(args[2]);
                    printDfaOnScreen(dfaAlgorithm.intersectionComputation(dfa1, dfa2));
                    break;
                case "-sd":
                    dfa2 = readDfa(args[2]);
                    printDfaOnScreen(dfaAlgorithm.symmetricDifference(dfa1, dfa2));
                    break;
                case "-emptyness":
                case "-emptiness":
                    if (dfaAlgorithm.emptinessVerification(dfa1, DfaAlgorithm::printRoute)) {
                        System.out.println("language empty");
                    } else {
                        System.out.println("language non-empty");
                    }
                    break;
                case "-equivalence":
                    dfa2 = readDfa(args[2]);
                    if (dfaAlgorithm.equivalence(dfa1, dfa2)) {
                        System.out.println("equivalent");
                    } else {
                        System.out.println("not equivalent");
                    }
                    break;
                default:
                    System.out.println("Invalid parameters");
                    printInstruction();
            }
        } else {
            printInstruction();
        }
    }

    private static void printInstruction() {
        String information = "usage: -option file1 [file2] \n" +
                "options include: \n" +
                " -complementation \n" +
                " -intersection \n" +
                " -sd \n"+
                " -emptyness \n" +
                " -equivalence ";
        System.out.println(information);
    }

    public static Dfa readDfa(String filePath) {
        File file = new File(filePath);

        try {
            Scanner sc = new Scanner(file);

            //Read states
            int numberOfStates = sc.nextInt();
            List<State> states = new ArrayList<>();
            for (int i = 0; i < numberOfStates; i++) {
                String stateName = sc.next();
                states.add(new State(stateName));
            }

            //Read alphabets
            int numberOfAlphabet = sc.nextInt();
            List<String> alphabets = new ArrayList<>();
            for (int i = 0; i < numberOfAlphabet; i++) {
                String alphabet = sc.next();
                alphabets.add(alphabet);
            }

            //Read transitions
            for (int i = 0; i < numberOfStates; i++) {
                State startState = states.get(i);
                for (int j = 0; j < numberOfAlphabet; j++) {
                    State finalState = Util.findStateById(states, sc.next());
                    Transition transition = new Transition(startState, alphabets.get(j), finalState);
                    startState.addTransition(transition);
                }
            }

            //Read start and final states
            State startState = Util.findStateById(states, sc.next());
            int numberOfFinalStates = sc.nextInt();
            List<State> finalStates = new ArrayList<>();
            for (int i = 0; i < numberOfFinalStates; i++) {
                State finalState = Util.findStateById(states, sc.next());
                finalStates.add(finalState);
            }

            return new Dfa(states, startState, finalStates, alphabets);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InputMismatchException e) {
            e.printStackTrace();
            System.out.println("Invalid DFA file format for file " + filePath);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("The start state/end state doesn't match state list");
        }
        return null;
    }

    public static void printDfaOnScreen(Dfa dfa) {
        //Print state information
        System.out.println(dfa.getStates().size());
        for (State s : dfa.getStates()) {
            System.out.print(s.getId() + " ");
        }
        System.out.println();

        //Print Alphabet information
        System.out.println(dfa.getAlphabet().size());
        for (String s : dfa.getAlphabet()) {
            System.out.print(s + " ");
        }
        System.out.println();

        //Print transitions table
        for (int i = 0; i < dfa.getStates().size(); i++) {
            for (int j = 0; j < dfa.getAlphabet().size(); j++) {
                State transStartState = dfa.getStates().get(i);
                System.out.print(transStartState.getTransitions().get(j).toString() + " ");
            }
            System.out.println();
        }

        //Print start state
        System.out.println(dfa.getStartState().getId());

        //Print final states
        System.out.println(dfa.getFinalStates().size());
        for (State s : dfa.getFinalStates()) {
            System.out.print(s.getId() + " ");
        }
        System.out.println();
    }
}
