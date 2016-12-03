package csula.cs4660.project;

/**
 * Created by James on 12/3/2016.
 */
import java.util.*;
        import java.io.*;
        import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int x = 0;
        int y = 0;
        String move;

        int[][] board = new int [20][30];

        // game loop
        while (true) {
            int N = in.nextInt(); // total number of players (2 to 4).
            int P = in.nextInt(); // your player number (0 to 3).
            for (int i = 0; i < N; i++) {
                int X0 = in.nextInt(); // starting X coordinate of lightcycle (or -1)
                int Y0 = in.nextInt(); // starting Y coordinate of lightcycle (or -1)
                int X1 = in.nextInt(); // starting X coordinate of lightcycle (can be the same as X0 if you play before this player)
                int Y1 = in.nextInt(); // starting Y coordinate of lightcycle (can be the same as Y0 if you play before this player)
                if(P == i){
                    x = X1;
                    y = Y1;
                    System.err.println("X1: " + X1 + " Y1: " + Y1);
                }
                board[Y1][X1] = 1;

            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            move = nextMove(board, x, y);
            System.out.println(move); // A single line with UP, DOWN, LEFT or RIGHT
        }
    }
    public static String nextMove(int[][] board, int x, int y){
        if(x == 0 || x == 29){
            //if(board[y][x])
            return "UP";
        }
        else if(y == 0 || y == 19){
            if(board[y][x + 1] != 1)
                return "RIGHT";
            else
                return "LEFT";
        }

        return "RIGHT";
    }

}