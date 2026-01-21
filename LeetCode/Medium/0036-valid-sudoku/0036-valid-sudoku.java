class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[][] sudoku = new int[9][9];
        
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(Character.isDigit(board[i][j])) sudoku[i][j] = board[i][j] - '0';
            }
        }

        int cnt = 0;

        while(cnt < 9) {
            int[] ch1 = new int[10]; //가로검증
            int[] ch2 = new int[10]; //세로검증
            for(int i = 0; i < 9; i++) {
                if(sudoku[cnt][i] != 0 && ch1[sudoku[cnt][i]] != 0) {
                    return false;
                } else ch1[sudoku[cnt][i]] = 1;
                if(sudoku[i][cnt] != 0 && ch2[sudoku[i][cnt]] != 0) {
                    return false;
                } else ch2[sudoku[i][cnt]] = 1;
            }
            cnt++;
        }

        cnt = 0;
        while(cnt < 9) {
            int cnt2 = 0;
            while(cnt2 < 9) {
                int[] ch = new int[10];
                for(int i = cnt; i < cnt + 3; i++) {
                    for(int j = cnt2; j < cnt2 + 3; j++) {
                        if(sudoku[i][j] != 0 && ch[sudoku[i][j]] != 0) return false;
                        else ch[sudoku[i][j]] = 1;
                    }
                }
                cnt2 += 3;
            }
            cnt += 3;
        }
        return true;
    }
}