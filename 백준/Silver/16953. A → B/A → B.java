import java.io.*;
import java.util.*;

public class Main {
	static int N, M, answer;
	static Queue<NumCount> que;
	
	
	static class NumCount {
		int n;
		int cost;
		public NumCount(int n, int cost) {
			super();
			this.n = n;
			this.cost = cost;
		}
	}
	
	static void solution() {
		while(!que.isEmpty()) {
			NumCount nc = que.poll();
			if(nc.n == M) {
				answer = nc.cost;
				return;
			}
			if(nc.n < 100_000_000 && nc.n * 10 + 1 <= M) que.offer(new NumCount(nc.n * 10 + 1, nc.cost + 1));
			if(nc.n <= M / 2) que.offer(new NumCount(nc.n * 2, nc.cost + 1));
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		que = new LinkedList<>();
		answer = -1;
		que.offer(new NumCount(N, 1));
		solution();
		System.out.println(answer);
	}
}