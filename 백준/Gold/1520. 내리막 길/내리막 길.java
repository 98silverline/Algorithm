import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N;
    static int[][] map, ch;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};


    static int solution(int x, int y) {
        if(x == M - 1 && y == N - 1) return 1;
        else if (ch[x][y] != -1) return ch[x][y];
        else {
            ch[x][y] = 0;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < M && ny < N && map[x][y] > map[nx][ny]) {
                    ch[x][y] += solution(nx, ny);
                }
            }
            return ch[x][y];
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        ch = new int[M][N];

        for (int i = 0; i < M; i++) {
            Arrays.fill(ch[i], -1);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
            }
        }

        solution(0, 0);
        System.out.println(ch[0][0]);
    }
}