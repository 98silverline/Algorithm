import java.io.*;
import java.util.*;

public class Solution {
	/*
	 * 메모리: 114,644 kb
	 * 시간: 2,087 ms
	 * 프림 알고리즘을 이용하였습니다.
	 * */
	static int T, V, E;
	static int[] ch;
	static long answer;
	static ArrayList<ArrayList<Edge>> graph;
	static PriorityQueue<Edge> pq;
	
	static class Edge implements Comparable<Edge>{
		int x;
		int cost;
		public Edge(int x, int cost) {
			this.x = x;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	static void solution() {
		int cnt = 0;
		pq.offer(new Edge(1, 0));
		while(!pq.isEmpty() && cnt < V) {
			Edge tmp = pq.poll();
			int ev = tmp.x;
			if(ch[ev] == 0) {
				ch[ev] = 1;
				answer += tmp.cost;
				cnt++;
				for(Edge ob : graph.get(ev)) {
					if(ch[ob.x] == 0) pq.offer(ob);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			StringTokenizer st = new StringTokenizer(bf.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			pq = new PriorityQueue<>();
			graph = new ArrayList<>();
			answer = 0;
			ch = new int[V + 1];
			for(int i = 0; i <= V; i++) graph.add(new ArrayList<>());
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				graph.get(a).add(new Edge(b, c));
				graph.get(b).add(new Edge(a, c));
			}
			solution();
			sb.append(answer + "\n");
		}
		System.out.println(sb.toString());
	}
}