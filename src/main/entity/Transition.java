package main.entity;

public class Transition {
    private final State startState;
    private final String symbol;
    private final State endState;

    public Transition(State startState,String symbol, State endState) {
        this.startState = startState;
        this.symbol = symbol;
        this.endState = endState;
    }
}
