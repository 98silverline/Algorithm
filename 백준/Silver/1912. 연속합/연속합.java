import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		dp = new int[N + 1];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 1; i <= N ; i++) dp[i] = Integer.parseInt(st.nextToken());
		
		answer = Integer.MIN_VALUE;
		for(int i = 1; i <= N; i++) {
			dp[i] = Math.max(dp[i - 1] + dp[i], dp[i]);
			answer = Math.max(answer, dp[i]);
		}
		
		System.out.println(answer);
	}
}