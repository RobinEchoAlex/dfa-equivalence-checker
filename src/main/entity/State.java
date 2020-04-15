package main.entity;

import java.util.List;

public class State {
    private final String id;
    private final List<Transition> transitions;

    public State(String id, List<Transition> transitions) {
        this.id = id;
        this.transitions = transitions;
    }

    //小心不要轻易override equals()--两个不同dfa有可能有完全一样的状态
}
