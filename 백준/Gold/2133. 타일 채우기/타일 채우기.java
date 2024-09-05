import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		dp = new int[N + 1];
		if(dp.length > 4) {
			dp[2] = 3;
			dp[4] = 11;
			for(int i = 6; i <= N; i += 2) {
				dp[i] = dp[i - 2] * 4  - dp[i - 4];
			}
			System.out.println(dp[N]);
		} else {
			if(N == 1 || N == 3) System.out.println(0);
			else System.out.println(3);
		}
	}
}