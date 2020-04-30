package main.algorithm;

import main.entity.State;

import java.util.List;

public class Util {

    /**
     * Find matching state object in a list of states by ID.
     * @param states states to search in
     * @param ID the ID of the state
     * @return the state object
     */
    public static State findStateById(List<State> states, String ID) {
        return states.stream().filter(state -> state.getId().equals(ID)).findFirst().orElseThrow(RuntimeException::new);
    }
}
