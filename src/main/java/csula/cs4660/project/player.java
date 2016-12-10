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
        String move = " ";

        int[][] board = new int [20][30];

        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 30; j++){
                board[i][j] = -1;
            }
        }

        ArrayList<Direction> direction = new ArrayList<>();
        direction.add(new Direction(-1, 0, "LEFT"));
        direction.add(new Direction(0, 1, "DOWN"));
        direction.add(new Direction(1, 0, "RIGHT"));
        direction.add(new Direction(0, -1, "UP"));

        // game loop
        while (true) {
            int N = in.nextInt(); // total number of players (2 to 4).
            int P = in.nextInt(); // your player number (0 to 3).
            for (int i = 0; i < N; i++) {
                int X0 = in.nextInt(); // starting X coordinate of lightcycle (or -1)
                int Y0 = in.nextInt(); // starting Y coordinate of lightcycle (or -1)
                int X1 = in.nextInt(); // starting X coordinate of lightcycle (can be the same as X0 if you play before this player)
                int Y1 = in.nextInt(); // starting Y coordinate of lightcycle (can be the same as Y0 if you play before this player)

                if(X1 != -1){
                    board[Y1][X1] = i;
                }
                else{
                    continue;
                }

                if(P == i){
                    x = X1;
                    y = Y1;
                }
            }

            int maxFreeBlocks = -1;
            for(Direction direct : direction){
                int X = x + direct.x;
                int Y = y + direct.y;
                int freeBlocks = moveBoard(board, X, Y);
                if(freeBlocks > maxFreeBlocks){
                    move = direct.direction;
                    maxFreeBlocks = freeBlocks;
                }
            }
//            System.err.println("board.length: " + board.length);
//            System.err.println("board.length[0]: " + board[0].length);

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            System.out.println(move); // A single line with UP, DOWN, LEFT or RIGHT
        }
    }

    public static int floodFill(int[][] board, int x, int y){
        // System.err.println(x + " " + y);
        if(x < 0 || x >= board[0].length || y < 0  || y >= board.length)
            return 0;

        if(board[y][x] != -1)
            return 0;

        int block = 1;
        board[y][x] = 0;

        block += floodFill(board, x + 1, y);
        block += floodFill(board, x - 1, y);
        block += floodFill(board, x, y + 1);
        block += floodFill(board, x, y - 1);

        return block;
    }

    public static int moveBoard(int[][] board, int x , int y){
        //Clone the array to a new one
        int[][] newBoard = Arrays.stream(board)
                .map((int[] row) -> row.clone())
                .toArray((int length) -> new int[length][]);

        return floodFill(newBoard, x, y);
    }

    public static class Direction {
        public int x;
        public int y;
        public String direction;

        public Direction(int x, int y, String direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}