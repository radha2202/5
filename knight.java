import java.util.*;

public class knight {

    // Knight moves (8 possibilities)
    static final int[] DR = {-2,-2,-1,-1, 1, 1, 2, 2};
    static final int[] DC = {-1, 1,-2, 2,-2, 2,-1, 1};

    static boolean solve(int[][] board, int r, int c, int step) {
        int n = board.length;
        if (step == n * n) return true; // all squares visited

        for (int k = 0; k < 8; k++) {
            int nr = r + DR[k], nc = c + DC[k];
            if (isSafe(board, nr, nc)) {
                board[nr][nc] = step;      // place next jump number
                if (solve(board, nr, nc, step + 1)) return true;
                board[nr][nc] = -1;        // backtrack
            }
        }
        return false;
    }

    static boolean isSafe(int[][] board, int r, int c) {
        int n = board.length;
        return r >= 0 && r < n && c >= 0 && c < n && board[r][c] == -1;
    }

    static void printBoard(int[][] board) {
        int n = board.length;
        for (int[] row : board) {
            for (int x : row) System.out.printf("%3d ", x);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        System.out.print("Enter board size N: ");
        int n = sc.nextInt();
        System.out.print("Enter start row (1..N): ");
        int sr1 = sc.nextInt();
        System.out.print("Enter start col (1..N): ");
        int sc1 = sc.nextInt();

        // Convert to 0-based indexes
        int sr = sr1 - 1, sc0 = sc1 - 1;
        if (n <= 0 || sr < 0 || sr >= n || sc0 < 0 || sc0 >= n) {
            System.out.println("Invalid inputs.");
            return;
        }

        int[][] board = new int[n][n];
        for (int[] row : board) Arrays.fill(row, -1);

        // Start position has 0 jumps
        board[sr][sc0] = 0;

        if (solve(board, sr, sc0, 1)) {
            System.out.println("\nKnight's tour found (numbers = jumps from start):");
            printBoard(board);
        } else {
            System.out.println("\nNo tour exists from that start on this board.");
        }
    }
}
