package csula.cs4660.graphs.searches;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

import java.util.*;
import java.util.Comparator;

/**
 * As name, dijkstra search using graph structure
 */
public class DijkstraSearch implements SearchStrategy {
    Node endTile = null;

    @Override
    public List<Edge> search(Graph graph, Node source, Node dist) {
        //Initialization
        List<Edge> result = new ArrayList<>();
        Map<Node, Node> previousNode = new HashMap<>();
        Map<Node, Integer> distance = new HashMap<>();

        //// create priority vertex queue Q
        Queue<Node> queue = new LinkedList<>();
        distance.put(source, 0);
        previousNode.put(source, null);

        for(Node vertex : graph.neighbors(source)){
            if(!vertex.equals(source)){
                distance.put(vertex, graph.distance(source, vertex));
            }
            queue.add(vertex);
        }

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            for(Node neightbor : graph.neighbors(currentNode)){
                int alt = distance.get(currentNode) + graph.distance(currentNode, neightbor);
                System.out.println(distance.get(currentNode));
                if(alt > graph.distance(currentNode, neightbor)){
                    distance.put(neightbor, alt);
                    previousNode.put(neightbor, currentNode);
                    queue.poll();
                }
                if(neightbor.equals(dist)){
                    endTile = neightbor;
                }
            }
        }

        while(previousNode.get(endTile) != null) {
            Node parent = previousNode.get(endTile);
            result.add(new Edge(parent, endTile, graph.distance(parent, endTile)));
            endTile = parent;
        }

        Collections.reverse(result);
        System.out.println(result);
        return result;

    }
}
