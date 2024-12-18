import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, time, size, eat;
    static int[][] map;
    static Point baby;
    static PriorityQueue<Point> pq;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Point implements Comparable<Point>{
        int x;
        int y;
        int dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point o) {
            if(this.dist == o.dist) {
                if(this.x == o.x) return this.y - o.y;
                return this.x - o.x;
            } else return this.dist - o.dist;
        }
    }

    static boolean BFS() {
        int[][] ch = new int[N][N];
        boolean flag = false;
        PriorityQueue<Point> que = new PriorityQueue<>();
        que.offer(new Point(baby.x, baby.y, 0));
        while (!que.isEmpty()) {
            Point cur = que.poll();
            if(map[cur.x][cur.y] != 0 && map[cur.x][cur.y] < size) {
                time += cur.dist;
                map[cur.x][cur.y] = 0;
                eat++;
                baby.x = cur.x;
                baby.y = cur.y;
                if(eat == size) {
                    size++;
                    eat = 0;
                }
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                if(nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] <= size && ch[nx][ny] == 0) {
                    ch[nx][ny] = 1;
                    que.offer(new Point(nx, ny, cur.dist + 1));
                }
            }
        }
        return flag;
    }

    static void solution() {
        while (true) {
            if(!BFS()) return;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        time = 0;
        size = 2;
        eat = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n == 9) baby = new Point(i, j, 0);
                else map[i][j] = n;
            }
        }
        solution();
        System.out.println(time);
    }
}