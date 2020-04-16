package main.entity;

public class Transition {
    private final String startState;
    private final String symbol;
    private final String endState;

    public Transition(String startState,String symbol, String endState) {
        this.startState = startState;
        this.symbol = symbol;
        this.endState = endState;
    }

    @Override
    public String toString() {
        return endState;
    }
}
