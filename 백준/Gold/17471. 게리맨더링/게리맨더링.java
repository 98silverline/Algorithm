
import java.util.*;
import java.io.*;

public class Main {
	static int N, size, answer, curSize;
	static int[] ch, score, visited;
	static ArrayList<ArrayList<Integer>> arr;
	
	static void DFS(int L, int num) {
		if(L == curSize) {
			isItRight();
		} else {
			for(int i = num; i <= N; i++) {
				ch[i] = 1;
				DFS(L + 1, i + 1);
				ch[i] = 0;
			}
		}
	}
	
	static void isItRight() {
		int score1 = 0;
		int score2 = 0;
		int cnt = 0;
		Queue<Integer> que = new LinkedList<>();
		visited = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			if(ch[i] == 0) {
				visited[i] = 1;
				que.offer(i);
				break;
			}
		}
		while(!que.isEmpty()) {
			int cur = que.poll();
			score1 += score[cur];
			cnt++;
			for(int n : arr.get(cur)) {
				if(visited[n] == 0 && ch[n] == 0) {
					visited[n] = 1;
					que.offer(n);
				}
			}
		}
		for(int i = 1; i <= N; i++) {
			if(ch[i] == 1) {
				visited[i] = 1;
				que.offer(i);
				break;
			}
		}
		while(!que.isEmpty()) {
			int cur = que.poll();
			score2 += score[cur];
			cnt++;
			for(int n : arr.get(cur)) {
				if(visited[n] == 0 && ch[n] == 1) {
					visited[n] = 1;
					que.offer(n);
				}
			}
		}
		if(cnt == N) answer = Math.min(answer, Math.abs(score1 - score2));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		ch = new int[N + 1];
		score = new int[N + 1];
		size = N / 2;
		arr = new ArrayList<>();
		answer = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 1; i <= N; i++) score[i] = Integer.parseInt(st.nextToken());
		for(int i = 0; i <= N; i++) arr.add(new ArrayList<>());
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int j = 0; j < n; j++) {
				arr.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		for(int i = 1; i <= size; i++) {
			curSize = i;
			DFS(0, 1);
		}
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
}
