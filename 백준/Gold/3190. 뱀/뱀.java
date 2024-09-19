import java.util.*;
import java.io.*;

public class Main {
    static int N, K, L, answer;
    static int[][] ch, map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Queue<Point> tail;
    static Queue<Turn> turn;
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Turn {
        int time;
        char direc;
        public Turn(int time, char direc) {
            this.time = time;
            this.direc = direc;
        }
    }

    static int solution() {
        int status = 3;
        int curT = 0;
        Point curP = new Point(0, 0);
        tail.add(new Point(0, 0));

        while(true) {
//            System.out.println("X: " + curP.x + ", Y: " + curP.y);
//            System.out.println(turn.peek().time);
            if(!turn.isEmpty() && curT == turn.peek().time) {
                Turn t = turn.poll();
                if(t.direc == 'L') {
                    if(status == 3) status = 0;
                    else status++;
                } else {
                    if(status == 0) status = 3;
                    else status--;
                }
            }
            int nx = curP.x + dx[status];
            int ny = curP.y + dy[status];
            if(nx >= 0 && ny >= 0 && nx < N && ny < N && ch[nx][ny] == 0) {
                if(map[nx][ny] == 1) {
                    map[nx][ny] = 0;
                } else {
                    Point t = tail.poll();
                    ch[t.x][t.y] = 0;
                }
                ch[nx][ny] = 1;
                curP = new Point(nx, ny);
                tail.add(new Point(nx, ny));
            } else return curT + 1;
            curT++;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        K = Integer.parseInt(bf.readLine());
        ch = new int[N][N];
        map = new int[N][N];
        tail = new LinkedList<>();
        turn = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = 1;
        }
        L = Integer.parseInt(bf.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int time = Integer.parseInt(st.nextToken());
            String str = st.nextToken();
            char direc = str.charAt(0);
            turn.offer(new Turn(time, direc));
        }
        answer = solution();
        System.out.println(answer);
    }
}