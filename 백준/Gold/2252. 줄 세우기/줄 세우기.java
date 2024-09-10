import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static StringBuilder sb;
	static int[] arr;
	static ArrayList<ArrayList<Integer>> graph;
	
	static void solution() {
		Queue<Integer> que = new LinkedList<>();
		for(int i = 1; i <= N; i++) if(arr[i] == 0) que.offer(i);
		while(!que.isEmpty()) {
			int cur = que.poll();
			sb.append(cur + " ");
			for(int n : graph.get(cur)) {
				arr[n]--;
				if(arr[n] == 0) que.offer(n);
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		arr = new int[N + 1];
		graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			arr[to]++;
		}
		
		solution();
		System.out.println(sb.toString());
	}
}