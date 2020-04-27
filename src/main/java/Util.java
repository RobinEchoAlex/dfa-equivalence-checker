package main.java;

import main.entity.State;

import java.util.List;

public class Util {

    public static State findState(List<State> states, String ID) {
        return states.stream().filter(state -> state.getId().equals(ID)).findFirst().orElseThrow();
    }
}
