
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[][] map, ch, arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void DFS(int cnt, int x, int y, int sub) {
        if(cnt == 4) {
            answer = Math.max(answer, sub);
        } else {
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < N && ny < M && ch[nx][ny] == 0) {
                    ch[nx][ny] = 1;
                    DFS(cnt + 1, nx, ny, sub + map[nx][ny]);
                    ch[nx][ny] = 0;
                }
            }
            if(cnt == 2) {
                for(int i = 0; i < 6; i++) {
                    int nx1 = x + dx[arr[i][0]];
                    int ny1 = y + dy[arr[i][0]];
                    int nx2 = x + dx[arr[i][1]];
                    int ny2 = y + dy[arr[i][1]];
                    if(nx1 >= 0 && ny1 >= 0 && nx2 >= 0 && ny2 >= 0 && nx1 < N && ny1 < M && nx2 < N && ny2 < M && ch[nx1][ny1] == 0 && ch[nx2][ny2] == 0) {
                        answer = Math.max(answer, sub + map[nx1][ny1] + map[nx2][ny2]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[][]{{0, 2}, {0, 3}, {2, 3}, {1, 2}, {0, 1}, {1, 3}};
        map = new int[N][M];
        ch = new int[N][M];
        answer = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                DFS(0, i, j, 0);
            }
        }

        System.out.println(answer);
    }
}
