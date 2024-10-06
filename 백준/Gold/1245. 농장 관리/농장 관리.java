
import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Main {
    static int N, M, answer;
    static int[][] map, ch;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static Queue<Point> que;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean BFS(int x, int y) {
        boolean flag = true;
        que = new LinkedList<>();
        que.offer(new Point(x, y));
//        System.out.println("start: " + x + ", " + y);
//        for (int[] ints : ch) {
//            for (int anInt : ints) {
//                System.out.print(anInt);
//            }
//            System.out.println();
//        }
        while(!que.isEmpty()) {
            Point p = que.poll();
//            System.out.println(p.x + ", " + p.y);
            ch[p.x][p.y] = 1;
            for (int i = 0; i < 8; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;
                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if(map[nx][ny] == map[p.x][p.y] && ch[nx][ny] == 0) que.offer(new Point(nx, ny));
                    else if (map[nx][ny] > map[p.x][p.y]) {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = 0;
        map = new int[N][M];
        ch = new int[N][M];
        que = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//
//        for (int[] ints : map) {
//            for (int anInt : ints) {
//                System.out.print(anInt + " ");
//            }
//            System.out.println();
//        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(ch[i][j] == 0) {
                    if(BFS(i, j)) {
                        answer++;
//                        System.out.println("높이: " + map[i][j] + ", i: " + i + ", j: " + j + ", 결과 성공");
                    }
//                    else System.out.println("높이: " + map[i][j] + ", i: " + i + ", j: " + j + ", 결과 실패");
                }
            }
        }
        System.out.println(answer);
    }
}
