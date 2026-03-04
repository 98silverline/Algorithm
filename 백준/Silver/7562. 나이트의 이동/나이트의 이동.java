import java.io.*;
import java.util.*;

public class Main {
    static int T, I, Xs, Ys, Xg, Yg;
    static int[][] ch;
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};

    static class Point {
        int x;
        int y;
        int cnt;

        Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static int BFS() {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(Xs, Ys, 0));
        while (!que.isEmpty()) {
            Point p = que.poll();
            for(int i = 0; i < 8; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx == Xg && ny == Yg) return p.cnt + 1;
                if(nx >= 0 && ny >= 0 && nx < I && ny < I && ch[nx][ny] == 0) {
                    ch[nx][ny] = 1;
                    que.offer(new Point(nx, ny, p.cnt + 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            I = Integer.parseInt(bf.readLine());
            ch = new int[I][I];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            Xs = Integer.parseInt(st.nextToken());
            Ys = Integer.parseInt(st.nextToken());
            ch[Xs][Ys] = 1;
            st = new StringTokenizer(bf.readLine());
            Xg = Integer.parseInt(st.nextToken());
            Yg = Integer.parseInt(st.nextToken());
            if(Xs == Xg && Ys == Yg) sb.append(0).append("\n");
            else sb.append(BFS()).append("\n");
        }
        System.out.println(sb);
    }
}
