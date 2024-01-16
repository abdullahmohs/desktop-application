package n_queen;

public class solving {

    private int size;
    public int board[][] = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    runnable[] r;
    Thread[] t;
    private print_solution ss = new print_solution();
    void printSolution(int board[][], int size) {
        ss.print(size, board);
    }

    Boolean isSafe(int board[][], int row, int col, int size) {
        int i, j;

        for (i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (i = row, j = col; j >= 0 && i < size; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    Boolean solveNQUtil(int board[][], int col, int size, int row) {

        if (col >= size) {
            return true;
        }

        if (isSafe(board, row, col, size)) {

            board[row][col] = 1;
            if (solveNQUtil(board, col + 1, size, row) == true) {
                return true;
            }

        }

        for (int i = 0; i < size; i++) {

            if (isSafe(board, i, col, size)) {

                board[i][col] = 1;

                if (solveNQUtil(board, col + 1, size, row) == true) {
                    return true;
                }

                board[i][col] = 0;
            }
        }

        return false;
    }

    Boolean solveNQ(int size, int row) {

        if (size >= 10) {
            System.out.println("The max size is 9x9.");
            return false;
        }

        if (solveNQUtil(board, 0, size, row) == false) {
            return false;
        }

        printSolution(board, size);
        return true;
    }

    Boolean solveNQwiththreads(int size) {

        t = new Thread[size];
        r = new runnable[size];
        for (int i = 0; i < size; i++) {
            r[i] = new runnable(size, i);
            t[i] = new Thread(r[i]);
            t[i].run();
        }

        return true;
    }

}
