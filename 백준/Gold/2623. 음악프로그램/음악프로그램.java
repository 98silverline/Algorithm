
import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] ch;
	static ArrayList<ArrayList<Integer>> graph;
	static ArrayList<Integer> answer;
	
	static void solution() {
		Queue<Integer> que = new LinkedList<>();
		for(int i = 1; i <= N; i++) if(ch[i] == 0) que.offer(i);
		while(!que.isEmpty()) {
			int n = que.poll();
			answer.add(n);
			for(int node : graph.get(n)) {
				ch[node]--;
				if(ch[node] == 0) que.offer(node);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());	//가수 수
		M = Integer.parseInt(st.nextToken());	//스탭 수
		ch = new int[N + 1];
		graph = new ArrayList<>();
		answer = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] arr = new int[n];
			for(int j = 0; j < n; j++) arr[j] = Integer.parseInt(st.nextToken());
			for(int j = 0; j < n - 1; j++) graph.get(arr[j]).add(arr[j + 1]);
		}
		
		for(ArrayList<Integer> arr : graph) {
			for(int node : arr) ch[node]++;
		}
		solution();
		if(answer.size() == N) {
			for(int ans : answer) System.out.println(ans);
		} else System.out.println(0);
	}
}
