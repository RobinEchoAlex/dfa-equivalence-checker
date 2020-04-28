package main.entity;

import java.util.ArrayList;
import java.util.List;

public class State {
    private final String id;
    private final List<Transition> transitions = new ArrayList<>();

    public State(String id) {
        this.id = id;
    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    public String getId() {
        return id;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

}
