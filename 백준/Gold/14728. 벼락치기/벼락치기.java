
import java.util.*;
import java.io.*;

public class Main {
    static int T, N, K, S;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        dp = new int[T + 1];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            K = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            for (int j = T; j >= K; j--) {
                dp[j] = Math.max(dp[j], dp[j - K] + S);
            }
        }
        System.out.println(dp[T]);
    }
}
