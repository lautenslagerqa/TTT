public class TTT {
    public char[][] board;
    public TTT() {
        board = new char[3][3];
        for (int i = 0; i< board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j]='-';
            }
        }
    }
    public boolean isMovesLeft() {
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board.length; j++) {
                if (board[i][j] == '-') {
                    return true;
                }
            }
        }
        return false;
    }
    public int solve(char[][] b) {
        if (b[0][0] == b[1][1] && b[2][2] == b[1][1] && b[0][0] != '-') {
            if (b[0][0] == 'X') return 1;
            else return -1;
        } 
        if (b[0][2] == b[1][1] && b[2][0] == b[1][1] && b[0][2] != '-') {
            if (b[0][2] == 'X') return 1;
            else return -1;
        }
        for (int i = 0; i<b.length; i++) {
            if (b[i][0] == b[i][1] && b[i][2] == b[i][1] && b[i][0] != '-') {
                if (b[i][0] == 'X') return 1;
                else return -1;
            }
            if (b[0][i] == b[1][i] && b[2][i] == b[1][i] && b[0][i] != '-') {
                if (b[0][i] == 'X') return 1;
                else return -1;
            } 
        }
        
        return 0;
    }
    public int minmax(char[][] b, int d, boolean max) {
        int s = solve(b);
        // If the position is terminal, return the game result.
        if (s != 0) {
            return s;
        }
        if (!isMovesLeft()) {
            return 0;
        }
        // If we've exhausted search depth on a non-terminal position,
        // return a neutral heuristic (0) rather than the sentinel 4.
        if (d == 0) {
            return 0;
        }
        if (max) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i<b.length; i++) {
                for (int j = 0; j<b.length; j++) {
                    if (b[i][j] == '-') {
                        b[i][j] = 'X';
                        best = Math.max(best, minmax(b, d-1, false));
                        b[i][j]='-';
                    }
                }
            }
            return best;
        } else {
            int worst = Integer.MAX_VALUE;
            for (int i = 0; i<b.length; i++) {
                for (int j = 0; j<b.length; j++) {
                    if (b[i][j] == '-') {
                        b[i][j] = 'O';
                        worst = Math.min(worst, minmax(b, d-1, true));
                        b[i][j]='-';
                    }
                }
            }
            return worst;
        }
        //return 0;
    }
    public void reset() {
        for (int i=0; i< board.length; i++) {
            for (int j=0;j<board.length;j++) {
                board[i][j]='-';
            }

        }
    }
    public int[] bestMove(char[][] b, boolean max) {
        int[] r = new int[2]; 
        if (max) {
            int bN = Integer.MIN_VALUE;
            for (int i = 0; i<b.length; i++) {
                for (int j = 0; j<b.length; j++) {
                    if (b[i][j] == '-') {
                        b[i][j]='X';
                        int temp = minmax(board, 5, false);
                        if (temp > bN) {

                            bN=temp;
                            r[0]=j;
                            r[1]=i;
                        }
                        b[i][j]='-';
                    }
                }
            }
        } else {
            int bN = Integer.MAX_VALUE;
            for (int i = 0; i<b.length; i++) {
                for (int j = 0; j<b.length; j++) {
                    if (b[i][j] == '-') {
                        b[i][j]='O';
                        int temp = minmax(board, 5, true);
                        if (temp < bN) {

                            bN=temp;
                            r[0]=i;
                            r[1]=j;
                        }
                        b[i][j]='-';
                    }
                }
            }
        }
        return r;
    }
    public boolean state = true;
    public void makeMove(int x, int y, boolean p) {
        if (board[x][y] != '-') {
            state = true;
            return;
        } else {
            if (p) board[x][y] = 'X';
            else board[x][y]='O';
            state = false;
        }
    }
    public String getSquare(int x, int y) {
        return Character.toString(board[x][y]);
    }
    public static void main(String[] args) {
        TTT t = new TTT();
        char[][] b = { {'X','O','O'},
                       {'-','X','X'}, 
                       {'-','X','O'} };
        t.board = b;
        int[] r= t.bestMove(b, false);
        System.out.println("col: " + r[0] + " row: " + r[1]);
    }
}
