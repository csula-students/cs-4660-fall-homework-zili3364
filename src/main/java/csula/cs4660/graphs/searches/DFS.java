package csula.cs4660.graphs.searches;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

import java.util.*;

/**
 * Depth first search
 */
public class DFS implements SearchStrategy {
    Node endTile = null;
    public List<Edge> search(Graph graph, Node source, Node dist) {
        //Initalizing
        List<Edge> result = new ArrayList<>();
        Map<Node, Node> parentNode = new HashMap<>();
        Map<Node, Integer> distance = new HashMap<>();

        //Setting up nodes
        parentNode.put(source, null);
        distance.put(source, 0);

        //recurveive dfs
        dfs(graph, source, parentNode, dist);

        //Creating the result list
        while (endTile != null) {
            Node parent = parentNode.get(endTile);
            if (parent != null) {
                result.add(new Edge(parent, endTile, graph.distance(parent, endTile)));
            }
            endTile = parent;
        }
        Collections.reverse(result);

        return result;
    }

    public void dfs(Graph graph, Node source, Map<Node, Node> parentNode, Node dist){
        for(Node child : graph.neighbors(source)){
            if(!parentNode.containsKey(child)){
                parentNode.put(child, source);
                dfs(graph, child, parentNode, dist);
            }
            if(child.equals(dist)){
                //System.out.println("end");
                endTile = dist;
            }
        }
    }
}
