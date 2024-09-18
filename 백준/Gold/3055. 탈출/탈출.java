import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] ch;
    static String answer;
    static PriorityQueue<Point> pq;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Point implements Comparable<Point>{
        int x, y, status, time;

        public Point(int x, int y, int status, int time) {
            this.x = x;
            this.y = y;
            this.status = status;
            this.time = time;
        }

        @Override
        public int compareTo(Point o) {
            if(this.time == o.time) return this.status - o.status;
            else return this.time - o.time;
        }
    }

    static void solution() {
        int curT = 1;
        boolean flag = false;
        while(!pq.isEmpty()) {
            Point p = pq.poll();
            if(p.status == 0 && p.time != curT) {
                if(flag) curT++;
                else return;
            }
            if(p.status == 0 && p.time == curT) {
                for(int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];
                    if(nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == '.') {
                        ch[nx][ny] = 1;
                        map[nx][ny] = '*';
                        pq.offer(new Point(nx, ny, 0, p.time + 1));
                    }
                }
            } else if (p.status == 1) {
                flag = true;
                for(int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];
                    if(nx >= 0 && ny >= 0 && nx < N && ny < M && ch[nx][ny] == 0) {
                        if(map[nx][ny] == '.') {
                            ch[nx][ny] = 1;
                            pq.offer(new Point(nx, ny, 1, p.time + 1));
                        }
                        else if (map[nx][ny] == 'D') {
                            answer = String.valueOf(p.time);
                            return;
                        }
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
        map = new char[N][M];
        ch = new int[N][M];
        pq = new PriorityQueue<>();
        answer = "KAKTUS";
        for(int i = 0; i < N; i++) {
            String str = bf.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == '*') {
                    pq.offer(new Point(i, j, 0, 1));
                    ch[i][j] = 1;
                }
                else if (map[i][j] == 'S') {
                    pq.offer(new Point(i, j, 1, 1));
                    ch[i][j] = 1;
                }
            }
        }

        solution();

        System.out.println(answer);
    }
}