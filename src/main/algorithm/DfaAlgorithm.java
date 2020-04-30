package main.algorithm;

import main.entity.Dfa;
import main.entity.State;
import main.entity.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static main.algorithm.Util.findStateById;

public class DfaAlgorithm {
    private boolean isEmptiness =true;

    /**
     * Derive the complementation of a given DFA.
     * The complementation will accept and only accept strings rejected by given DFA.
     * The method turns every non-accepted state into accept state, vice versa.
     * @param dfa DFA to compute
     * @return the complementation of given DFA
     */
    public Dfa complementationComputation(final Dfa dfa) {
        // Derive new accept state list
        List<State> finalStates = dfa.getStates().stream().filter(
                state -> !dfa.getFinalStates().contains(state)
        ).collect(Collectors.toList());

        return new Dfa(dfa.getStates(), dfa.getStartState(), finalStates, dfa.getAlphabet());
    }

    /**
     * Calculate the intersection of two DFAs.
     * The intersection will accept and only accept all string that is accepted by both DFAs.
     * @param dfa1 the first DFA
     * @param dfa2 the second DFA
     * @return the intersection
     */
    public Dfa intersectionComputation(Dfa dfa1, Dfa dfa2) {
        //Initialise attributes for new DFA
        List<State> states = new ArrayList<>();
        State startStates = null;
        List<State> finalStates = new ArrayList<>();

        for (State state1 : dfa1.getStates()) {
            for (State state2 : dfa2.getStates()) {
                //Create new state whose name is generated by id concatenation
                State state = new State("(" + state1.getId() + "," + state2.getId() + ")");
                states.add(state);

                //If both state is the start state in its DFA, declare this compound state is the SS for new DFA.
                if (dfa1.getStartState().equals(state1) && dfa2.getStartState().equals(state2)) {
                    //Paradox arises if there is two start states for new DFA
                    if (startStates != null) {
                        throw new AssertionError("Duplicate start states");
                    }
                    startStates = state;
                }

                //If both state is final state in respective DFA, this compound state is a final state.
                if (dfa1.getFinalStates().contains(state1) && dfa2.getFinalStates().contains(state2)) {
                    finalStates.add(state);
                }
            }
        }

        //Set up transitions for new DFA
        //For each compound state (r,s)...
        for (State state1 : dfa1.getStates()) {
            for (State state2 : dfa2.getStates()) {
                for (Transition transition1 : state1.getTransitions()) {
                    for (Transition transition2 : state2.getTransitions()) {
                        //...and each alphabet symbol...
                        if (transition1.getSymbol().equals(transition2.getSymbol())) {
                            // The destination of the transition is the combination of two original destination
                            String originStateName = "(" + state1.getId() + "," + state2.getId() + ")";
                            String endStateName = "(" + transition1.getEndState().getId() + "," + transition2.getEndState().getId() + ")";
                            State startState = findStateById(states, originStateName);
                            startState.addTransition(new Transition(startState,
                                    transition1.getSymbol(),
                                    findStateById(states,endStateName)
                                    ));
                        }
                    }
                }
            }
        }

        Dfa dfa = new Dfa(states, startStates, finalStates, dfa1.getAlphabet());
        return dfa;
    }

    /**
     * Compute the union of two given DFAs.
     * The union DFA should accept and only accept string recognised by at least one given DFA.
     * @param dfa1 the first DFA
     * @param dfa2 the second DFA
     * @return the union
     */
    public Dfa unionComputation(Dfa dfa1, Dfa dfa2) {
        List<State> states = new ArrayList<>();
        State startStates = null;
        List<State> finalStates = new ArrayList<>();

        for (State state1 : dfa1.getStates()) {
            for (State state2 : dfa2.getStates()) {
                //Create new state whose name is generated by id concatenation
                State state = new State("(" + state1.getId() + "," + state2.getId() + ")");
                states.add(state);

                //If both state is the start state in its DFA, declare this compound state is the SS for new DFA.
                if (dfa1.getStartState().equals(state1) && dfa2.getStartState().equals(state2)) {
                    //Paradox arises if there is two start state for new DFA
                    if (startStates != null) {
                        throw new AssertionError("Duplicate start states");
                    }
                    startStates = state;
                }

                //If either state is final state, this compound state is a final state.
                if (dfa1.getFinalStates().contains(state1) || dfa2.getFinalStates().contains(state2)) {
                    finalStates.add(state);
                }
            }
        }

        //Set up transitions for new DFA
        //For each compound state (r,s)...
        for (State state1 : dfa1.getStates()) {
            for (State state2 : dfa2.getStates()) {
                for (Transition transition1 : state1.getTransitions()) {
                    for (Transition transition2 : state2.getTransitions()) {
                        //...and each alphabet symbol...
                        if (transition1.getSymbol().equals(transition2.getSymbol())) {
                            // The destination of the transition is the combination of two original destination
                            String originStateName = "(" + state1.getId() + "," + state2.getId() + ")";
                            String endStateName = "(" + transition1.getEndState().getId() + "," + transition2.getEndState().getId() + ")";
                            State startState = findStateById(states, originStateName);
                            startState.addTransition(new Transition(startState,
                                    transition1.getSymbol(),
                                    findStateById(states,endStateName)
                            ));
                        }

                    }
                }
            }
        }

        Dfa dfa = new Dfa(states, startStates, finalStates, dfa1.getAlphabet());
        return dfa;
    }

    /**
     * Calculate the symmetric difference of two given DFA.
     * The symmetric difference can accept all and only those strings accepted by exactly one of the given DFA.
     * @param dfa1 the first DFA
     * @param dfa2 the second DFA
     * @return the symmetric difference.
     */
    public Dfa symmetricDifference(Dfa dfa1, Dfa dfa2) {
        //FIXME different result if change order
        return unionComputation(
                intersectionComputation(complementationComputation(dfa1),dfa2),
                intersectionComputation(dfa1,complementationComputation(dfa2))
        );
    }

    /**
     * Determine whether a DFA is empty or not.
     * It is empty if the final states cannot be reached.
     * Depth-first search is used to test whether the final state can be reached or not.
     *
     * @implNote It uses recursive DFS.
     * @param dfa the DFA to be determined
     * @param actionWhenNoEmpty the action to execute when the DFA is not empty
     * @return true if empty.
     */
    public boolean emptinessVerification(Dfa dfa,Consumer<List<Transition>> actionWhenNoEmpty) {
        if (dfa.getFinalStates().contains(dfa.getStartState())) {
            actionWhenNoEmpty.accept(null);
            return false;
        }

        boolean[] visited = new boolean[dfa.getStates().size()];
        List<Transition> path = new ArrayList<>();
        dfsRecursion(dfa,dfa.getStartState(), visited, path, actionWhenNoEmpty);
        return isEmptiness;
    }

    /**
     * Recursive depth-first search.
     *
     * @param dfa DFA to search
     * @param currentState state that the current search step is in
     * @param isVisited a bitmap indicating whether a state is visited or not
     * @param path path traveled in current search
     * @param actionWhenNoEmpty the action to be performed when the final state is reached, i.e. non-emptiness.
     */
    private void dfsRecursion(Dfa dfa, State currentState, boolean[] isVisited, List<Transition> path, Consumer<List<Transition>> actionWhenNoEmpty) {
        isVisited[dfa.getStates().indexOf(currentState)] = true;

        //It is the end state, i.e. the DFA is no empty.
        if (dfa.getFinalStates().contains(currentState)) {
            isEmptiness = false;
            actionWhenNoEmpty.accept(path);
        }

        //Not the end state, traverse every unvisited adjacent states.
        for (Transition trans : currentState.getTransitions()) {
            State endState = trans.getEndState();
            if (!isVisited[dfa.getStates().indexOf(endState)]) {
                path.add(trans);
                dfsRecursion(dfa, endState, isVisited, path, actionWhenNoEmpty);
                path.remove(trans);
            }
        }
    }

    /**
     * Determine whether two DFAs are equivalent or not.
     * It is clear that if two DFAs recognise same language, their symmetric difference is empty.
     * @param dfa1 the first DFA
     * @param dfa2 the second DFA
     * @return true if equivalent
     */
    public boolean equivalence(Dfa dfa1, Dfa dfa2) {
        //TODO if the alphabet is not equal, return immediately
        return emptinessVerification(symmetricDifference(dfa1, dfa2), DfaAlgorithm::declareNonEquivalent);
    }

    /**
     * Print a string that is accepted by an given DFA on the console.
     * It will terminate the program.
     * @apiNote it is a Consumer invoked when a route is found, and the user asks to print the path.
     * @implNote passing null path signify the dfa accept empty string
     * @param path the lists of transitions that can eventually end in final state
     */
    public static void printRoute(List<Transition> path) {
        System.out.print("language non-empty - ");
        if (path == null) {
            System.out.print("e");
        }
        else for (Transition transition: path) {
            System.out.print(transition.getSymbol());
        }
        System.out.println(" accepted");
        System.exit(0);
    }

    /**
     * Declare two DFAs are not equivalent by printing prompt in the console.
     * @apiNote it is a Consumer invoked when a route is found, which means the DFA is not empty.
     */
    public static void declareNonEquivalent(List<Transition> path) {
        System.out.println("not equivalent");
        System.exit(0);
    }
}
