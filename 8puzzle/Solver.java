/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private boolean solution;
    private Stack<Board> solutiontracker;
    private int finalmoves;

    private class SearchNode implements Comparable<SearchNode> {
        private int moves;
        private SearchNode previous;
        private Board board;
        private int priority;

        private SearchNode(int moves, SearchNode previous, Board board) {
            this.moves = moves;
            this.previous = previous;
            this.board = board;
            this.priority = moves + board.manhattan();
        }


        public int compareTo(SearchNode that) {
            return Integer.compare(this.priority, that.priority);
        }

    }

    public Solver(Board initial) {

        if (initial == null) {
            throw new IllegalArgumentException();
        }
        MinPQ<SearchNode> board = new MinPQ<SearchNode>();
        MinPQ<SearchNode> twin = new MinPQ<SearchNode>();
        SearchNode initialb = new SearchNode(0, null, initial);
        board.insert(initialb);
        SearchNode initialt = new SearchNode(0, null, initial.twin());
        twin.insert(initialt);
        while (!board.isEmpty() && !twin.isEmpty()) {
            SearchNode minPriorityb = board.delMin();
            if (minPriorityb.board.isGoal()) {
                this.finalmoves = minPriorityb.moves;
                solution = true;
                solutiontracker = new Stack<Board>();
                for (SearchNode node = minPriorityb; node != null; node = node.previous) {
                    solutiontracker.push(node.board);
                }
                break;

                // Reconstruct the solution path & exit & mark as solvable!
            }

            for (Board bd : minPriorityb.board.neighbors()) {
                if (minPriorityb.previous != null && bd.equals(minPriorityb.previous.board)) {
                    continue;
                }
                SearchNode neighbours = new SearchNode(minPriorityb.moves + 1, minPriorityb, bd);
                board.insert(neighbours);
            }

            SearchNode minPriorityt = twin.delMin();
            if (minPriorityt.board.isGoal()) {
                solution = false;
                break;
                // It is not solvable store it somewhere & exit
            }

            for (Board bd : minPriorityt.board.neighbors()) {
                if (minPriorityt.previous != null && bd.equals(minPriorityt.previous.board)) {
                    continue;
                }
                SearchNode neighbours = new SearchNode(minPriorityt.moves + 1, minPriorityt, bd);
                twin.insert(neighbours);
            }

        }
    }

    public boolean isSolvable() {
        return solution;
    }

    public int moves() {
        if (isSolvable()) {
            return finalmoves;
        }
        return -1;
    }

    public Iterable<Board> solution() {
        return solutiontracker;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

    }
}
