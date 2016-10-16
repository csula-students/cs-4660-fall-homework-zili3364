package csula.cs4660.graphs.searches;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

import java.util.*;

/**
 * Breadth first search
 */
public class BFS implements SearchStrategy {
    Node endTile = null;

    @Override
    public List<Edge> search(Graph graph, Node source, Node dist) {

        /***************
         function BFS(v) {
         for (node in G) {
         node.distance = Number.MAX_VALUE;
         node.parent = null;
         }
         **************/
        List<Edge> result = new ArrayList<>();
        Map<Node, Node> parentNode = new HashMap<>();
        Map<Node, Integer> distance = new HashMap<>();

        //reate empty queue Q
        //var queue = new Queue();
        Queue<Node> queue = new LinkedList<>();

        //Best first search - use priority queue (lowest number goes first)
        //var result = [];
        //var endTile = null;

        //v.distance = 0;
        //queue.enqueue(v);
        distance.put(source, 0);
        queue.add(source);

        while (!queue.isEmpty()) {
            //var u = queue.dequeue();
            Node currentNode = queue.poll();

            /*******
             for (node in Graph.neighbors(u)) {
             if (node.distance == Number.MAX_VALUE) {
             node.distance = u.distance + edge.value
             // note that I'm leaving edge value up to
             // you to implement
             node.parent = u;
             if (Graph.isGoal(node)) {
             endTile = u;
             }
             queue.enqueue(node);
             }
             }
             ******/

            List<Node> temp = graph.neighbors(currentNode);
            for(Node node : temp){
                if(!distance.containsKey(node)){
                    int dis = distance.get(currentNode) + graph.distance(currentNode, node);
                    distance.put(node, dis);
                    parentNode.put(node, currentNode);
                    if(node.equals(dist)){
                        endTile = node;
                    }
                    queue.add(node);
                }
            }

        }

        // back trace from goal to start
        /*****
         while (endTile.parent != null) {
         result.add(Edge(endTile.parent, endTile));
         endTile = endTile.parent;
         }
         ******/

        while(parentNode.get(endTile) != null) {
            Node parent = parentNode.get(endTile);
            result.add(new Edge(parent, endTile, graph.distance(parent, endTile)));
            endTile = parent;
        }

        // remember to reverse the result before return
        //return result.reverse();

        Collections.reverse(result);
        return result;
    }
}