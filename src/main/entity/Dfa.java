package main.entity;

import java.util.List;

public class Dfa {
    private final List<State> states;
    private final State startState;
    private final List<State> finalStates;
    private final List<Transition> transitions;
    private final List<String> alphabet;

    public Dfa(List<State> states, State startState, List<State> finalStates, List<Transition> transitions, List<String> alphabet) {
        this.states = states;
        this.startState = startState;
        this.finalStates = finalStates;
        this.transitions = transitions;
        this.alphabet = alphabet;
    }

}
