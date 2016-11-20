package csula.cs4660.games;

import csula.cs4660.games.models.MiniMaxState;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

public class AlphaBeta {
    public static Node getBestMove(Graph graph, Node source, Integer depth, Integer alpha, Integer beta, Boolean max) {
        // TODO: implement your alpha beta pruning algorithm here
        Node<MiniMaxState> result = graph.getNode(source).get();

        if (depth == 0) {
            return source;
        }

        if (max) {
            int bestValue = Integer.MIN_VALUE;
            for (Node node : graph.neighbors(source)) {
                int value = ((MiniMaxState) getBestMove(graph, node, depth - 1, alpha, beta, false).getData()).getValue();
                bestValue = Math.max(bestValue, value);
                alpha = Math.max(alpha, bestValue);
                    if(beta <= alpha ){
                        break;
                    }
                result.getData().setValue(bestValue);
            }
            return result;
        } else {
            int bestValue = Integer.MAX_VALUE;
            for (Node node : graph.neighbors(source)) {
                int value = ((MiniMaxState) getBestMove(graph, node, depth - 1, alpha, beta, true).getData()).getValue();
                bestValue = Math.min(bestValue, value);
                result.getData().setValue(bestValue);
                beta = Math.min(beta, bestValue);
                    if(beta <= alpha){
                        break;
                    }
                result.getData().setValue(bestValue);
            }
            return result;
        }
    }
}
