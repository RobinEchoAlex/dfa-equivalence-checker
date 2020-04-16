package main.entity;

import java.util.List;

public class Dfa {
    private final List<String> states;
    private final String startState;
    private final List<String> finalStates;
    private final List<Transition> transitions;
    private final List<String> alphabet;

    public Dfa(List<String> states, String startState, List<String> finalStates, List<Transition> transitions, List<String> alphabet) {
        if ( states.isEmpty() || startState==null || transitions.isEmpty() || alphabet.isEmpty()) {
            throw new AssertionError("Invalid DFA received");
        }
        this.states = states;
        this.startState = startState;
        this.finalStates = finalStates;
        this.transitions = transitions;
        this.alphabet = alphabet;
    }

    public List<String> getStates() {
        return states;
    }

    public String getStartState() {
        return startState;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }
}
