package main.java;

import main.entity.Dfa;
import main.entity.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DfaAlgorithm {

    public Dfa complementationComputation(final Dfa dfa) {
        List<String> finalStates = dfa.getStates().stream().filter(
                state -> !dfa.getFinalStates().contains(state)
        ).collect(Collectors.toList());

        return new Dfa(dfa.getStates(), dfa.getStartState(), finalStates, dfa.getTransitions(), dfa.getAlphabet());
    }

    public Dfa intersectionComputation(Dfa dfa1, Dfa dfa2) {
        List<String> states = new ArrayList<>();
        String startStates = null;
        List<String> finalStates = new ArrayList<>();
        List<Transition> transitions = new ArrayList<>();

        for (String states1 : dfa1.getStates()) {
            for (String states2 : dfa2.getStates()) {
                String state = "(" + states1 + "," + states2 + ")";
                states.add(state);

                if (dfa1.getStartState().contains(states1) && dfa2.getStartState().contains(states2)) {
                    if (startStates != null) {
                        throw new AssertionError("Duplicate start states");
                    }
                    startStates = state;
                }

                if (dfa1.getFinalStates().contains(states1) && dfa2.getFinalStates().contains(states2)) {
                    finalStates.add(state);
                }

                List<Transition> transitionsList1= dfa1.getTransitions().stream()
                        .filter(transition -> transition.getStartState().equals(states1))
                        .collect(Collectors.toList());

                List<Transition> transitionsList2 =dfa2.getTransitions().stream()
                        .filter(transition -> transition.getStartState().equals(states2))
                        .collect(Collectors.toList());

                for (Transition transition1 : transitionsList1) {
                    for (Transition transition2 : transitionsList2) {
                        if (transition1.getSymbol().equals(transition2.getSymbol())) {
                            transitions.add(new Transition(state, transition1.getSymbol(), "(" + transition1.getEndState() + "," + transition2.getEndState() + ")"));
                        }
                    }
                }
            }
        }

        return new Dfa(states,startStates,finalStates,transitions,dfa1.getAlphabet());
    }

    public Dfa unionComputation(List<Dfa> dfaPair) {
        return null;
    }

    public boolean emptinessVerification(Dfa dfa) {
        return false;
    }

}
