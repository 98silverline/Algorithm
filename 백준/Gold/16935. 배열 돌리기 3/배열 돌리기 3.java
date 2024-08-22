import java.util.*;
import java.io.*;

public class Main {
    static int N, M, R;
    static int[][] board;

    // 1번 연산: 상하 반전
    public static void cmdOne() {
        int[][] newBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            newBoard[i] = board[N - 1 - i];
        }
        board = newBoard;
    }

    // 2번 연산: 좌우 반전
    public static void cmdTwo() {
        int[][] newBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newBoard[i][j] = board[i][M - 1 - j];
            }
        }
        board = newBoard;
    }

    // 3번 연산: 오른쪽으로 90도 회전
    public static void cmdThree() {
        int[][] newBoard = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                newBoard[i][j] = board[N - 1 - j][i];
            }
        }
        board = newBoard;
        int temp = N;
        N = M;
        M = temp;
    }

    // 4번 연산: 왼쪽으로 90도 회전
    public static void cmdFour() {
        int[][] newBoard = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                newBoard[i][j] = board[j][M - 1 - i];
            }
        }
        board = newBoard;
        int temp = N;
        N = M;
        M = temp;
    }

    // 5번 연산: 부분 배열 이동 (1 -> 2 -> 3 -> 4 -> 1)
    public static void cmdFive() {
        int xN = N / 2, xM = M / 2;
        int[][] newBoard = new int[N][M];

        // 1 -> 2
        for (int i = 0; i < xN; i++) {
            for (int j = 0; j < xM; j++) {
                newBoard[i][j + xM] = board[i][j];
            }
        }

        // 2 -> 3
        for (int i = 0; i < xN; i++) {
            for (int j = xM; j < M; j++) {
                newBoard[i + xN][j] = board[i][j];
            }
        }

        // 3 -> 4
        for (int i = xN; i < N; i++) {
            for (int j = xM; j < M; j++) {
                newBoard[i][j - xM] = board[i][j];
            }
        }

        // 4 -> 1
        for (int i = xN; i < N; i++) {
            for (int j = 0; j < xM; j++) {
                newBoard[i - xN][j] = board[i][j];
            }
        }

        board = newBoard;
    }

    // 6번 연산: 부분 배열 이동 (1 -> 4 -> 3 -> 2 -> 1)
    public static void cmdSix() {
        int xN = N / 2, xM = M / 2;
        int[][] newBoard = new int[N][M];

        // 1 -> 4
        for (int i = 0; i < xN; i++) {
            for (int j = 0; j < xM; j++) {
                newBoard[i + xN][j] = board[i][j];
            }
        }

        // 4 -> 3
        for (int i = xN; i < N; i++) {
            for (int j = 0; j < xM; j++) {
                newBoard[i][j + xM] = board[i][j];
            }
        }

        // 3 -> 2
        for (int i = xN; i < N; i++) {
            for (int j = xM; j < M; j++) {
                newBoard[i - xN][j] = board[i][j];
            }
        }

        // 2 -> 1
        for (int i = 0; i < xN; i++) {
            for (int j = xM; j < M; j++) {
                newBoard[i][j - xM] = board[i][j];
            }
        }

        board = newBoard;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(bf.readLine());
        while (st.hasMoreTokens()) {
            int n = Integer.parseInt(st.nextToken());
            switch (n) {
                case 1:
                    cmdOne();
                    break;
                case 2:
                    cmdTwo();
                    break;
                case 3:
                    cmdThree();
                    break;
                case 4:
                    cmdFour();
                    break;
                case 5:
                    cmdFive();
                    break;
                case 6:
                    cmdSix();
                    break;
            }
        }

        for (int[] arr : board) {
            for (int n : arr) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}