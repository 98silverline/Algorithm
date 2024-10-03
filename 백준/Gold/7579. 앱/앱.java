
import java.util.*;
import java.io.*;

public class Main {
    static int N, M, answer, max;
    static int [] memo, cost;
    static int[][] DP;

    static void solution() {
        for(int i = 0; i < N; i++) {               //열 - 앱
            for(int j = 0; j <= answer; j++) {     //행 - 비용
                if(i > 0) {
                    if(j < cost[i]) {

                        DP[j][i] = DP[j][i - 1];
                    } else {
                        int n = DP[j - cost[i]][i - 1] + memo[i];
                        if(DP[j][i - 1] < n) DP[j][i] = n;
                        else DP[j][i] = DP[j][i - 1];
                    }
                } else if (cost[i] <= j) DP[j][i] = memo[i];
                if(DP[j][i] >= M) answer = Math.min(answer, j);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memo = new int[N];
        cost = new int[N];
        max = 0;
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++) memo[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            max += n;
            cost[i] = n;
        }

        answer = max;
        DP = new int[max + 1][N];
        solution();

        System.out.println(answer);
    }
}
