import java.io.*;
import java.util.*;

public class Main {
	static int N, M, answer;
	static int[] ch;
	static boolean flag;
	static ArrayList<ArrayList<Integer>> graph;
	
	static void DFS(int L, int x) {
		if(L == 4) {
			answer = 1;
			flag = true;
		} else {
			if(flag) return;
			for(int i = 0; i < graph.get(x).size(); i++) {
				int n = graph.get(x).get(i);
				if(ch[n] == 0) {
					ch[n] = 1;
					DFS(L + 1, n);
					ch[n] = 0;
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = 0;
		graph = new ArrayList<>();
		flag = false;
		for(int i = 0; i < N; i++) graph.add(new ArrayList<>());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph.get(x).add(y);
			graph.get(y).add(x);
		}
		for(int i = 0; i < N; i++) {
			ch = new int[N];
			ch[i] = 1;
			DFS(0, i);
			if(flag) break;
		}
		System.out.println(answer);
	}
	
}