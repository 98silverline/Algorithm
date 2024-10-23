import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, K, S, X, Y;
    static int[][] map;
    static PriorityQueue<Point> pq;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Point implements Comparable<Point> {
        int x, y, size, time;

        public Point(int x, int y, int size, int time) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.time = time;
        }

        @Override
        public int compareTo(Point o) {
            if(this.time == o.time) return this.size - o.size;
            else return this.time - o.time;
        }
    }

    static void solution() {
        while(!pq.isEmpty()) {
            Point p = pq.poll();
            if(p.time == S) return;
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] == 0) {
                    map[nx][ny] = p.size;
                    pq.offer(new Point(nx, ny, p.size, p.time + 1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0) pq.offer(new Point(i, j, map[i][j], 0));
            }
        }
        st = new StringTokenizer(bf.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        solution();
        System.out.println(map[X - 1][Y - 1]);
    }
}
