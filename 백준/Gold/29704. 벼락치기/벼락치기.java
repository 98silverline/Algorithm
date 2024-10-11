

import java.io.*;
import java.util.*;
public class Main {
	static int N, T, max;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken()); 	//문제의 개수
		T = Integer.parseInt(st.nextToken());	//남은 기한
		
		dp = new int[T + 1];
		max = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			int d = Integer.parseInt(st.nextToken());	//걸리는 일수
			int m = Integer.parseInt(st.nextToken());	//벌금
			max += m;
			for(int j = T; j >= d; j--) dp[j] = Math.max(dp[j], dp[j - d] + m);
		}
		System.out.println(max - dp[T]);
	}
}
