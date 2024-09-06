import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		dp = new long[1001];
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3; i <= N; i ++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
		}
		System.out.println(dp[N]);	
	}
}