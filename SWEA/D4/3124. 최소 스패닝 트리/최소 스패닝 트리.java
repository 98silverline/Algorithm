import java.io.*;
import java.util.*;

public class Solution {
	static int T, V, E;
	static long answer;
	static int[] parent;
	static ArrayList<Point> arr;
	
	static class Point implements Comparable<Point>{
		int x;
		int y;
		int cost;
		public Point(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		@Override
		public int compareTo(Point o) {
			return this.cost - o.cost;
		}
	}
	
	
	static boolean Union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return false;
		else if (x < y) parent[y] = x;
		else parent[x] = y;
		return true;
	}
	
	static int find(int x) {
		if(x == parent[x]) return x;
		else return find(parent[x]);
	}
	
	static void solution() {
		for(int i = 0; i < E; i++) {
			Point p = arr.get(i);
			if(Union(p.x, p.y)) answer += p.cost;
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
			parent = new int[V + 1];
			arr = new ArrayList<>();
			answer = 0;
			for(int i = 0; i <= V; i++) parent[i] = i;
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(bf.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				arr.add(new Point(x, y, cost));
			}
			Collections.sort(arr);
			solution();
			sb.append(answer + "\n");
		}
		System.out.println(sb.toString());
	}
}