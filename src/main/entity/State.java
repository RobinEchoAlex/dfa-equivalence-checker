package main.entity;

import java.util.List;

public class State {
    private final String id;

    public State(String id) {
        this.id = id;
    }

    //小心不要轻易override equals()--两个不同dfa有可能有完全一样的状态
}
