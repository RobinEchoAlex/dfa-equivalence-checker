package main.entity;

public class Transition {
    private final State startState;
    private final State endState;

    public Transition(State startState, State endState) {
        this.startState = startState;
        this.endState = endState;
    }
}
