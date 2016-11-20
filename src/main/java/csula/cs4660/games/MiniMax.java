package csula.cs4660.games;

import csula.cs4660.games.models.MiniMaxState;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

public class MiniMax {
    public static Node getBestMove(Graph graph, Node root, Integer depth, Boolean max) {
        /* Pesudocode reference
        function minimax(graph, sourceNode, depth, maximizingPlayer) {
            // usually being optimized in a way to compute even before
            // the end of game by **evaluate** function
            if (depth = 0 || sourceNode is a leaf) {
                return evaluate(soureNode.gameState); // return a number
            }

            if (maximizingPlayer) {
                bestValue = Number.MAX_VALUE; // positive infinite
                for (node in graph.neighbors(sourceNode)) {
                    value = minimax(node, graph, depth - 1, false);
                    bestValue = Math.max(bestValue, value);
                }
                return bestValue;
            } else {
                bestValue = Number.MIN_VALUE; // negative infinite
                for (node in graph.neighbors(sourceNode)) {
                    value = minimax(node, graph, depth - 1, true);
                    bestValue = Math.min(bestValue, value);
                }
                return bestValue;
            }
        }
        */

        Node<MiniMaxState> result = graph.getNode(root).get();

        if (depth == 0) {
            return root;
        }

        if (max) {
            int bestValue = Integer.MIN_VALUE;
            for (Node node : graph.neighbors(root)) {
                int value = ((MiniMaxState) getBestMove(graph, node, depth - 1, false).getData()).getValue();
                bestValue = Math.max(bestValue, value);
                result.getData().setValue(bestValue);
            }
            return result;
        } else {
            int bestValue = Integer.MAX_VALUE;
            for (Node node : graph.neighbors(root)) {
                int value = ((MiniMaxState) getBestMove(graph, node, depth - 1, true).getData()).getValue();
                bestValue = Math.min(bestValue, value);
                result.getData().setValue(bestValue);
            }
            return result;
        }
    }
}