package main.entity;

import java.util.List;

public class Dfa {
    private final List<State> states;
    private final State startState;
    private final List<State> finalStates;
    private final List<String> alphabet;

    public Dfa(List<State> states, State startState, List<State> finalStates,List<String> alphabet) {
        this.states = states;
        this.startState = startState;
        this.finalStates = finalStates;
        this.alphabet = alphabet;
    }

    public List<State> getStates() {
        return states;
    }

    public State getStartState() {
        return startState;
    }

    public List<State> getFinalStates() {
        return finalStates;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }
}
