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

    public String getStartState() {
        return startState;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getEndState() {
        return endState;
    }

    @Override
    public String toString() {
        return endState;
    }
}
