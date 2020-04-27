package main.entity;

import java.util.ArrayList;
import java.util.List;

public class State {
    private final String id;
    private List<Transition> transitions = new ArrayList<>();

    public State(String id) {
        this.id = id;
    }

    public State(String id, List<Transition> transitions) {
        this.id = id;
        this.transitions = transitions;
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

    //小心不要轻易override equals()--两个不同dfa有可能有完全一样的状态
}
