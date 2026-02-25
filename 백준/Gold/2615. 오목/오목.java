import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board, ch;
    static boolean flag;
    static int answer, x, y;
    static int[] dx = {1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1};

    //cur = 돌 색, cnt = 이어진 갯수, dist = 방향, xy = 좌표
    static void DFS(int cur, int cnt, int dist, int x, int y) {
        if(cnt == 5) {
            if(x >= 0 && y >= 0 && x < 19 && y < 19 && board[x][y] == cur) return;
            flag = true;
        } else {
            if(x < 0 || y < 0 || x >= 19 || y >= 19) return;
            //현재 좌표에 세고있는 돌 수가 있다면
            if(ch[x][y] == 0 && board[x][y] == cur) {
                DFS(cur, cnt + 1, dist, x + dx[dist], y + dy[dist]);
            }
        }
    }

    static void solution() {
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if(board[i][j] != 0) {
                    for(int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        DFS(board[i][j], 1, k, nx, ny);
                        ch[i][j] = 1;
                        //승부가 결정된 경우
                        if(flag) {
                            nx = i - dx[k];
                            ny = j - dy[k];
                            if(nx >= 0 && ny >= 0 && nx < 19 && ny < 19 && board[nx][ny] == board[i][j]) {
                                flag = false;
                                continue;
                            }
                            answer = board[i][j];
                            if(k == 0) {
                                x = i + 5;
                                y = j - 3;
                            } else {
                                x = i + 1;
                                y = j + 1;
                            }
                            return;
                        }
                    }
                    ch[i][j] = 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        board = new int[19][19];
        ch = new int[19][19];
        flag = false;
        answer = 0;

        for(int i = 0; i < 19; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
        System.out.println(answer);
        if (flag) {
            System.out.println(x + " " + y);
        }
    }
}
