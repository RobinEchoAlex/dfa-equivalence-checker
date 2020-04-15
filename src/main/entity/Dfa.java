package main.entity;

import java.util.List;

public class Dfa {
    private final List<State> states;
    private final State startState;
    private final List<State> finalStates;

    public Dfa(List<State> states, State startState, List<State> finalStates) {
        this.states = states;
        this.startState = startState;
        this.finalStates = finalStates;
    }
}
