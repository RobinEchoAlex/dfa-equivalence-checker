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

    public State getStartState() {
        return startState;
    }

    public String getSymbol() {
        return symbol;
    }

    public State getEndState() {
        return endState;
    }

    @Override
    public String toString() {
        return endState.getId();
    }
}
