import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, start, answer, max;
	static int[][] graph;
	static int[] ch;
	static Queue<Point> que;
	
	static class Point{
		int x;
		int cost;
		public Point(int x, int cost) {
			super();
			this.x = x;
			this.cost = cost;
		}
	}
	public static void BFS(int start) {
		que.offer(new Point(start, 0));
		ch[start] = 1;
		int maxCost = 0;
		while(!que.isEmpty()) {
			Point p = que.poll();
			if(maxCost < p.cost) {
				maxCost = p.cost;
				answer = p.x;
			} else answer = Math.max(answer, p.x);
			for(int i = 1; i <= 100; i++) {
				if(graph[p.x][i] == 1 && ch[i] == 0) {
					ch[i] = 1;
					que.offer(new Point(i, p.cost + 1));
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = 10;
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			answer = 0;
			que = new LinkedList<>();
			graph = new int[101][101];
			ch = new int[101];
			st = new StringTokenizer(bf.readLine());
			while(st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from][to] = 1;
			}
			BFS(start);
			sb.append(answer + "\n");
		}
		System.out.println(sb.toString());
	}
}