import java.util.*;
import java.io.*;

public class Main {
	static int N, M, V, C, K;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken()); 	//물건 종류의 수
		M = Integer.parseInt(st.nextToken());	//들 수 있는 가방의 최대 무게
		dp = new int[M + 1];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			V = Integer.parseInt(st.nextToken());	//물건의 무게
			C = Integer.parseInt(st.nextToken());	//올라가는 만족도
			K = Integer.parseInt(st.nextToken());	//물건의 개수
			
			for(int j = 1; K > 0; j *= 2) {
				int count = Math.min(K, j);
				int newV = V * count;
				int newC = C * count;
				for(int k = M; k >= newV; k--) dp[k] = Math.max(dp[k], dp[k - newV] + newC);
				K -= count;
			}
		}
		System.out.println(dp[M]);
	}
} 
