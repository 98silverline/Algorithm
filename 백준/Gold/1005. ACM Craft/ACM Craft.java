

import java.io.*;
import java.util.*;

public class Main {
	static int T, N, K, W;
	static int[] cost, fwd;
	static ArrayList<ArrayList<Integer>> graph;
	static StringBuilder sb;
	static PriorityQueue<Point> pq;
	
	static class Point implements Comparable<Point> {
		int x, time;

		public Point(int x, int time) {
			this.x = x;
			this.time = time;
		}

		@Override
		public int compareTo(Point o) {
			return this.time - o.time;
		}
	}
	
	static void solution() {
		while(!pq.isEmpty()) {
			Point cur = pq.poll();
			if(cur.x == W) {
				sb.append(cur.time + "\n"); 
				return;
			}
			for(int i = 0; i < graph.get(cur.x).size(); i++) {
				int n = graph.get(cur.x).get(i);
				fwd[n]--;
				if(fwd[n] == 0) pq.offer(new Point(n, cur.time + cost[n]));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		sb = new StringBuilder();
		for(int test_case = 0; test_case < T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());	//건물 개수
			K = Integer.parseInt(st.nextToken());	//규칙 개수
			graph = new ArrayList<>();
			fwd = new int[N + 1];
			cost = new int[N + 1];
			pq = new PriorityQueue<>();
			
			//건설 시간
			st = new StringTokenizer(bf.readLine());
			for(int i = 1; i <= N; i++) cost[i] = Integer.parseInt(st.nextToken());
			
			//그래프 행 추가
			for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

			//규칙
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(bf.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph.get(from).add(to);
				fwd[to]++;
			}
			
			//지어야 할 건물
			W = Integer.parseInt(bf.readLine());
			
			//pq에 처음부터 건설 가능한 건물들 추가
			for(int i = 1; i <= N; i++) if(fwd[i] == 0) pq.add(new Point(i, cost[i]));
			
			//정답 구하기
			solution();
		}
		System.out.println(sb.toString());
	}
}
