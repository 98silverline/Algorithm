
import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] ch, answer;
	static ArrayList<ArrayList<Integer>> arr;
	static Queue<Subject> que;
	
	static class Subject {
		int num, term;

		public Subject(int num, int term) {
			this.num = num;
			this.term = term;
		}
	}
	
	static void solution() {
		while(!que.isEmpty()) {
			Subject cur = que.poll();
			answer[cur.num] = cur.term;
			for(int n : arr.get(cur.num)) {
				ch[n]--;
				if(ch[n] == 0) que.offer(new Subject(n, cur.term + 1));
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new ArrayList<>();
		que = new LinkedList<>();
		ch = new int[N + 1];
		answer = new int[N + 1];
		for(int i = 0; i <= N; i++) arr.add(new ArrayList<>()); 
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr.get(x).add(y);
			ch[y]++;
		}
		for(int i = 1; i <= N; i++) if(ch[i] == 0) que.add(new Subject(i, 1));
		solution();
		for(int i = 1; i <= N; i++) System.out.print(answer[i] + " ");
	}
}
