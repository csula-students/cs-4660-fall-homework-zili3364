package csula.cs4660.graphs.searches;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

import java.util.*;

/**
 * Depth first search
 */
public class DFS implements SearchStrategy {
    List<Edge> result  = new ArrayList<>();

    @Override
    public List<Edge> search(Graph graph, Node source, Node dist) {

        /******
        // accumlator, return, stacks

        DFS(startNode);

        function DFS(current) {
        return DFS(current, {});
        }

        function DFS(current, parents) {
            for (child in chilren) {
                if (child.isDiscovered) {
                    continue;
                }
                child.isDiscovered = true;
                parents.put(current, child);
                DFS(child, parents);
            }
        }
        ************/
        Map<Node, Node> parentNode = new HashMap<>();

        Collections.reverse(result);
        return result;
    }

    public void DFS(Graph graph, Node source, Node dist){
        boolean childisDiscovered = true;

        List<Node> children = graph.neighbors(source);
        for(Node child : children){
            if(source.equals(dist)){
                result.add(new Edge(source, child, graph.distance(source, child)));
                DFS(graph, source, dist);
            }
        }
        childisDiscovered = false;

    }
}
