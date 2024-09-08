
import java.util.*;
import java.io.*;

public class Main {
    static int N, M, answer;
    static int [][] map;
    static int [][][] ch;
    static int [] dx = {-1, 0, 1, 0};
    static int [] dy = {0, -1, 0, 1};
    static Queue<Point> que;

    static class Point {
        int x, y, broke;
        public Point(int x, int y, int broke) {
            this.x = x;
            this.y = y;
            this.broke = broke;
        }
    }

    static void solution() {
        que.offer(new Point(0, 0, 0));
        ch[0][0][0] = 1;
        ch[0][0][1] = 1;
        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if(p.broke == 1) {
                        if(map[nx][ny] == 0 && ch[nx][ny][1] == 0) {
                            ch[nx][ny][1] = ch[p.x][p.y][1] + 1;
                            que.offer(new Point(nx, ny, 1));
                        }
                    } else {
                        if(map[nx][ny] == 0 && ch[nx][ny][0] == 0 && ch[p.x][p.y][0] != 0) {
                            ch[nx][ny][0] = ch[p.x][p.y][0] + 1;
                            que.offer(new Point(nx, ny, 0));
                        } else if(map[nx][ny] == 1 && ch[nx][ny][1] == 0 && ch[p.x][p.y][0] != 0) {
                            ch[nx][ny][1] = ch[p.x][p.y][0] + 1;
                            que.offer(new Point(nx, ny, 1));
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
        map = new int[N][M];
        ch = new int[N][M][2];
        que = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String str = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] =  str.charAt(j) - '0';
            }
        }
        solution();

        if(ch[N - 1][M - 1][0] != 0 && ch[N - 1][M - 1][1] != 0) answer = Math.min(ch[N - 1][M - 1][0], ch[N - 1][M - 1][1]);
        else if (ch[N - 1][M - 1][0] == 0 && ch[N - 1][M - 1][1] == 0) answer = -1;
        else if (ch[N - 1][M - 1][0] == 0) answer = ch[N - 1][M - 1][1];
        else if (ch[N - 1][M - 1][1] == 0) answer = ch[N - 1][M - 1][0];
        System.out.println(answer);

    }
}