
import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());			//최대 공부시간
		K = Integer.parseInt(st.nextToken());			//과목 수
		dp = new int[N + 1];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(bf.readLine());
			int I = Integer.parseInt(st.nextToken());	//중요도
			int T = Integer.parseInt(st.nextToken());	//필요한 공부시간
			
			for(int j = N; j >= T; j--) {
				dp[j] = Math.max(dp[j], dp[j - T] + I);
			}
		}
		System.out.println(dp[N]);
 	}
}
