import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] ch;
    static class Point {
        int num;
        int cnt;

        Point(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    static int BFS() {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(N, 0));
        ch[N] = 1;

        while(!que.isEmpty()) {
            Point p = que.poll();
            int a = p.num - 1;
            int b = p.num + 1;
            int c = p.num * 2;
            if(a == K || b == K || c == K) return p.cnt + 1;
            if(a >= 0 && ch[a] == 0) {
                que.offer(new Point(a, p.cnt + 1));
                ch[a] = 1;
            }
            if(b <= 100000 && ch[b] == 0) {
                que.offer(new Point(b, p.cnt + 1));
                ch[b] = 1;
            }
            if(c <= 100000 && ch[c] == 0) {
                que.offer(new Point(c, p.cnt + 1));
                ch[c] = 1;
            }

        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ch = new int[100001];
        if(N == K) System.out.println(0);
        else System.out.println(BFS());
    }
}
