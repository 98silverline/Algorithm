import java.util.*;
import java.io.*;

public class Main {
	static int C, N;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		C = Integer.parseInt(st.nextToken());	//늘리려는 고객의 수
		N = Integer.parseInt(st.nextToken());	//도시의 수
		dp = new int[C + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			int cost = Integer.parseInt(st.nextToken()); 		//비용
			int customer = Integer.parseInt(st.nextToken());	//늘어나는 고객의 수
			for(int j = 1; j <= C; j++) {
				if(j >= customer) dp[j] = Math.min(dp[j], dp[j - customer] + cost);
				else dp[j] = Math.min(dp[j], cost);
			}
		}
		System.out.println(dp[C]);
	}
}
