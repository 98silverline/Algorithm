import java.util.*;
import java.io.*;

public class Main {
    static int N, M, answer;
    static int[] parent;
    static PriorityQueue<Point> pq;
    
    static class Point {
    	int from, to, cost;

		public Point(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
    }
    
    static boolean union(int x, int y) {
    	x = find(x);
    	y = find(y);
    	if(x == y) return false;
    	else if (x < y) parent[y] = x;
    	else parent[x] = y;
    	return true;
    }
    
    static int find(int x) {
    	if(parent[x] != x) return find(parent[x]);
    	else return x;
    }
    static void solution() {
    	while(!pq.isEmpty()) {
    		Point p = pq.poll();
    		if(union(p.from, p.to)) answer += p.cost;
    	}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        answer = 0;
        pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;
        for(int i = 0; i < M; i++) {
        	StringTokenizer st = new StringTokenizer(bf.readLine());
        	pq.offer(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        solution();
        System.out.println(answer);
    }
}