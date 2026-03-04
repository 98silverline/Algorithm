import java.io.*;
import java.util.*;

public class Main {
    static int N, cnt;
    static int[][] map, ch;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int DFS(int x, int y) {
        int wid = 1;
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if(nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] == 1 && ch[nx][ny] == 0) {
                ch[nx][ny] = 1;
                wid += DFS(nx, ny);
            }
        }
        return wid;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        ch = new int[N][N];
        cnt = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            String str = bf.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1 && ch[i][j] == 0) {
                    ch[i][j] = 1;
                    cnt++;
                    pq.offer(DFS(i, j));
                }
            }
        }

        System.out.println(cnt);
        while(!pq.isEmpty()) System.out.println(pq.poll());
    }
}
