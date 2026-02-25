import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, start, end;
    static ArrayList<ArrayList<Point>> arr;
    static int[] ch;

    static class Point implements Comparable<Point>{
        int end;
        int cost;
        Point(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return this.cost - cost;
        }
    }

    static void BFS() {
        ch[start] = 0;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(start, 0));
        ch[start] = 0;

        while(!pq.isEmpty()) {
            Point p = pq.poll();
            if(ch[p.end] < p.cost) continue;
            for(Point np : arr.get(p.end)) {
                int cost = np.cost + ch[p.end];
                if(ch[np.end] > cost) {
                    ch[np.end] = cost;
                    pq.offer(new Point(np.end, cost));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();
        ch = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            arr.add(new ArrayList<>());
            ch[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr.get(Integer.parseInt(st.nextToken())).add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        if(start == end) System.out.println(0);
        else BFS();
        System.out.println(ch[end]);
    }
}