import java.io.*;
import java.util.*;

public class Main {
    static int F, S, G, U, D;
    static int[] ch;
    static class Point {
        int num;
        int cnt;

        Point(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    static int solution() {
        if(S == G) return 0;
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(S, 0));
        while (!que.isEmpty()) {
            Point p = que.poll();
            int up = p.num + U;
            int down = p.num - D;
            if(up == G || down == G) return p.cnt + 1;
            if(up <= F && ch[up] == 0) {
                que.offer(new Point(up, p.cnt + 1));
                ch[up] = 1;
            }
            if(down >= 1 && ch[down] == 0) {
                que.offer(new Point(down, p.cnt + 1));
                ch[down] = 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        ch = new int[F + 1];
        ch[S] = 1;

        int answer = solution();
        System.out.println(answer != -1 ? answer : "use the stairs");
    }
}
