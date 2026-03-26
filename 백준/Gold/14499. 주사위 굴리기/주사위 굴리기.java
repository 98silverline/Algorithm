import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dice = new int[6];
        int[][] idx = new int[5][6];
        idx[1] = new int[]{3, 1, 0, 5, 4, 2};
        idx[2] = new int[]{2, 1, 5, 0, 4, 3};
        idx[3] = new int[]{4, 0, 2, 3, 5, 1};
        idx[4] = new int[]{1, 5, 2, 3, 0, 4};

        int[] dx = new int[]{0, 0, 0, -1, 1};
        int[] dy = new int[]{0, 1, -1, 0, 0};

        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < K; i++) {
            int cur = Integer.parseInt(st.nextToken());
            int nx = x + dx[cur];
            int ny = y + dy[cur];
            if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                int[] copy = dice.clone();
                for(int j = 0; j < 6; j++) {
                    dice[j] = copy[idx[cur][j]];
                }
                if(map[nx][ny] == 0) {
                    map[nx][ny] = dice[5];
                } else {
                    dice[5] = map[nx][ny];
                    map[nx][ny] = 0;
                }
                sb.append(dice[0]).append("\n");
                x = nx;
                y = ny;
            }
        }
        System.out.println(sb);
    }
}
