import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static String[][] board, newBoard;

    static String solution2(int x, int y) {
        int one = 0;
        int zero = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = x; i < x + 2; i++) {
            for (int j = y; j < y + 2; j++) {
                String str = board[i][j];
                if(str.length() == 1) {
                    if (Integer.parseInt(str) == 1) one++;
                    else zero++;
                }
                sb.append(str);
            }
        }
        if(one == 4 || zero == 4) {
           if(one > 0) return "1";
           else return "0";
        } else return sb.toString();
    }
    static void solution(){
        while(N >= 2) {
            newBoard = new String[N/2][N/2];
            int x = 0;
            int y = 0;
            for(int i = 0; i < N; i += 2) {
                for (int j = 0; j < N; j += 2) {
                    String str = solution2(i, j);
                    if(str.length() == 1) newBoard[x][y] = str;
                    else newBoard[x][y] = "(" + str + ")";
                    y++;
                    if(y == N/2) {
                        y = 0;
                        x++;
                    }
                }
            }
            board = newBoard.clone();
            N /= 2;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        board = new String[N][N];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String str = bf.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = String.valueOf(str.charAt(j));
            }
        }
        solution();
        for (String[] strings : board) {
            for (String string : strings) {
                sb.append(string);
            }
        }
        System.out.println(sb.toString());
    }
}
