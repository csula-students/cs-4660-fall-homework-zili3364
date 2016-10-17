package csula.cs4660.quizes;

import csula.cs4660.quizes.models.State;

import java.util.*;

/**
 * Here is your quiz entry point and your app
 */
public class App {
    public static void main(String[] args) {
        // to get a state, you can simply call `Client.getState with the id`
        State initialState = Client.getState("10a5461773e8fd60940a56d2e9ef7bf4").get();
        System.out.println(initialState);
        State endState = Client.getState("e577aa79473673f6158cc73e0e5dc122").get();
        // to get an edge between state to its neighbor, you can call stateTransition
        System.out.println(Client.stateTransition(initialState.getId(), initialState.getNeighbors()[0].getId()));

        Queue<State> frontier = new LinkedList<>();
        Set<State> exploredSet = new HashSet<>();
        Map<State, State> parents = new HashMap<>();
        List<String> result = new LinkedList<>();
        frontier.add(initialState);

        Boolean endTile = false;
        while (endTile != true) {
            State current = frontier.poll();
            exploredSet.add(current);

            // for every possible action
            for (State neighbor: Client.getState(current.getId()).get().getNeighbors()) {
                // state transition
                if (neighbor.getId().equals("e577aa79473673f6158cc73e0e5dc122")) {
                    // construct actions from endTile
                    //System.out.println("found solution with depth of " + findDepth(parents, current, initialState));
                    //System.out.println("ends");
                    endTile = true;
                }
                if (!exploredSet.contains(neighbor)) {
                    parents.put(neighbor, current);
                    frontier.add(neighbor);
                    //System.out.println("println");
                }
            }
        }

        System.out.println("BFS Path: ");
        printPath(parents, initialState, endState);
    }

    public static void printPath(Map<State, State> parents, State initialState, State endState){
        List<String> result = new LinkedList<>();

        while(!initialState.equals(endState)){
            result.add(parents.get(endState).getLocation().getName()+ " : " + endState.getLocation().getName()
                    + " : " + Client.stateTransition(parents.get(endState).getId() ,
                    endState.getId()).get().getEvent().getEffect());
            endState = parents.get(endState);
        }

        Collections.reverse(result);

        for(String print : result){
            System.out.println(print);
        }
    }
}