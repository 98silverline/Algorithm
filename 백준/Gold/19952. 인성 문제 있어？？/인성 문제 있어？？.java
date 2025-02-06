import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int T, H, W, O, F, Xs, Ys, Xe, Ye;
    static int[][] map, ch;
    static Queue<Point> que;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Point {
        int x, y, power;

        public Point(int x, int y, int power) {
            this.x = x;
            this.y = y;
            this.power = power;
        }
    }

    static void solution() {
        while (!que.isEmpty()) {
            Point p = que.poll();
            if(p.power == 0) continue;
            if(p.x == Xe && p.y == Ye) return;
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < H && ny < W && ch[nx][ny] == 0 && map[nx][ny] - map[p.x][p.y] <= p.power) {
                    ch[nx][ny] = 1;
                    que.offer(new Point(nx, ny, p.power - 1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            H = Integer.parseInt(st.nextToken());   //미로 행
            W = Integer.parseInt(st.nextToken());   //미로 열
            O = Integer.parseInt(st.nextToken());   //장애물 수
            F = Integer.parseInt(st.nextToken());   //초기 힘
            Xs = Integer.parseInt(st.nextToken()) - 1;  //초기 좌표 행
            Ys = Integer.parseInt(st.nextToken()) - 1;  //초기 좌표 열
            Xe = Integer.parseInt(st.nextToken()) - 1;  //목적지 좌표 행
            Ye = Integer.parseInt(st.nextToken()) - 1;  //목적지 좌표 열

            que = new LinkedList<>();
            que.offer(new Point(Xs, Ys, F));

            map = new int[H][W];
            ch = new int[H][W];

            for (int i = 0; i < O; i++) {
                st = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                int height = Integer.parseInt(st.nextToken());
                map[x][y] = height;
            }

            solution();
            sb.append(ch[Xe][Ye] == 1 ? "잘했어!!" : "인성 문제있어??").append("\n");
        }
        System.out.println(sb);
    }
}