/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

public class Board {


    private final int n;
    private final int[][] tiles;
    private int I = 0;
    private int J = 0;


    public Board(int[][] tiles) {
        this.n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    I = i;
                    J = j;
                    break;
                }
            }
        }
    }

    public int dimension() {
        return this.n;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();

    }

    public int hamming() {
        int hammingDistance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int rowMajorVal = i * n + j + 1;
                if (tiles[i][j] == 0) {
                    continue;
                }
                else if (tiles[i][j] == rowMajorVal) {
                    continue;
                }
                else {
                    hammingDistance++;
                }

            }
        }
        return hammingDistance;
    }

    public int manhattan() {
        int manhattanDistance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    continue;
                }
                else {
                    int iHash = (tiles[i][j] - 1) / n;
                    int jHash = (tiles[i][j] - 1) % n;
                    int tempManhattanDistance = Math.abs(iHash - i) + Math.abs(jHash - j);
                    manhattanDistance += tempManhattanDistance;
                }
            }
        }
        return manhattanDistance;
    }

    public boolean isGoal() {
        return manhattan() == 0;
    }

    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }

        if (y == null || getClass() != y.getClass()) {
            return false;
        }
        Board other = (Board) y;
        return Arrays.deepEquals(this.tiles, other.tiles);
    }

    public Iterable<Board> neighbors() {

        Stack<Board> sb = new Stack<Board>();
        if (I - 1 >= 0) {
            int[][] neighbourUp = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    neighbourUp[i][j] = tiles[i][j];
                }
            }
            int temp = neighbourUp[I][J];
            neighbourUp[I][J] = neighbourUp[I - 1][J];
            neighbourUp[I - 1][J] = temp;
            Board up = new Board(neighbourUp);
            sb.push(up);
        }
        if (I + 1 < n) {
            int[][] neighbourDown = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    neighbourDown[i][j] = tiles[i][j];
                }
            }
            int temp = neighbourDown[I][J];
            neighbourDown[I][J] = neighbourDown[I + 1][J];
            neighbourDown[I + 1][J] = temp;
            Board down = new Board(neighbourDown);
            sb.push(down);
        }
        if (J - 1 >= 0) {
            int[][] neighbourLeft = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    neighbourLeft[i][j] = tiles[i][j];
                }
            }
            int temp = neighbourLeft[I][J];
            neighbourLeft[I][J] = neighbourLeft[I][J - 1];
            neighbourLeft[I][J - 1] = temp;
            Board left = new Board(neighbourLeft);
            sb.push(left);
        }
        if (J + 1 < n) {
            int[][] neighbourRight = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    neighbourRight[i][j] = tiles[i][j];
                }
            }
            int temp = neighbourRight[I][J];
            neighbourRight[I][J] = neighbourRight[I][J + 1];
            neighbourRight[I][J + 1] = temp;
            Board right = new Board(neighbourRight);
            sb.push(right);
        }
        return sb;

    }

    public Board twin() {
        int[][] twinboard = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                twinboard[i][j] = tiles[i][j];
            }
        }

        int indexa = 0;
        int indexb = 0;
        int indexc = 0;
        int indexd = 1;
        while ((indexa == indexc && indexb == indexd) || (indexa == I && indexb == J) || (
                indexc == I && indexd == J)) {
            if (indexa + 1 < n) {
                indexa++;
            }
            else {
                indexa--;
            }
            if (indexb + 1 < n) {
                indexb++;
            }
            else {
                indexb--;
            }
            if (indexc + 1 < n) {
                indexc++;
            }
            else {
                indexc--;
            }
            if (indexd + 1 < n) {
                indexd++;
            }
            else {
                indexd--;
            }

        }
        int temp = twinboard[indexa][indexb];
        twinboard[indexa][indexb] = twinboard[indexc][indexd];
        twinboard[indexc][indexd] = temp;
        Board twin = new Board(twinboard);
        return twin;

    }

    public static void main(String[] args) {
        int[][] original = { { 1, 0 }, { 2, 3 } };
        Board b = new Board(original);
        System.out.println(b.twin());
    }
}

