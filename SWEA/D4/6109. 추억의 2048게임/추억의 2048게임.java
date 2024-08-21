import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int T, N;
    static int[][] board, answer;

    static void solution (int cmd) {
        if(cmd == 1 || cmd == 2) {
            for(int i = 0; i < N; i++) {
                int tmp = 0;
                if(cmd == 1) {
                    int cnt = 0;
                    for(int j = 0; j < N; j++) {
                        if(board[i][j] != 0) {
                            if(tmp == board[i][j]) {
                                answer[i][cnt - 1] = tmp * 2;
                                tmp = 0;
                            } else {
                                answer[i][cnt] = board[i][j];
                                tmp = board[i][j];
                                cnt++;
                            }
                        }
                    }
                } else {
                    int cnt = N - 1;
                    for(int j = N - 1; j >= 0; j--) {
                        if(board[i][j] != 0) {
                            if(tmp == board[i][j]) {
                                answer[i][cnt + 1] = tmp * 2;
                                tmp = 0;
                            } else {
                                answer[i][cnt] = board[i][j];
                                tmp = board[i][j];
                                cnt--;
                            }
                        }
                    }
                }
            }
        }
        if(cmd == 3 || cmd == 4) {
            for(int i = 0; i < N; i++) {
                int tmp = 0;
                if(cmd == 3) {
                    int cnt = 0;
                    for(int j = 0; j < N; j++) {
                        if(board[j][i] != 0) {
                            if(tmp == board[j][i]) {
                                answer[cnt - 1][i] = tmp * 2;
                                tmp = 0;
                            } else {
                                answer[cnt][i] = board[j][i];
                                tmp = board[j][i];
                                cnt++;
                            }
                        }
                    }
                } else {
                    int cnt = N - 1;
                    for(int j = N - 1; j >= 0; j--) {
                        if(board[j][i] != 0) {
                            if(tmp == board[j][i]) {
                                answer[cnt + 1][i] = tmp * 2;
                                tmp = 0;
                            } else {
                                answer[cnt][i] = board[j][i];
                                tmp = board[j][i];
                                cnt--;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            String cmd = st.nextToken();
            int command = -1;
            board = new int[N][N];
            answer = new int[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            switch (cmd) {
                case "left":
                    command = 1;
                    break;
                case "right":
                    command = 2;
                    break;
                case "up":
                    command = 3;
                    break;
                case "down":
                    command = 4;
                    break;
            }

            solution(command);
            System.out.println("#" + test_case);
            for(int[] arr : answer) {
                for (int i : arr) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
        }
    }
}
