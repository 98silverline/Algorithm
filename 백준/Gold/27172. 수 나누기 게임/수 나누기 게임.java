import java.io.*;
import java.util.*;

public class Main {
    static int N, max;
    static int[] nums, ch, scores;

    static void solution() {
        for (int i = 0; i < N; i++) {
            int cur = nums[i];
            int num = cur;
            cur += num;
            while (cur <= max) {
                if(ch[cur] != 0) {
                    scores[i]++;
                    scores[ch[cur] - 1]--;
                }
                cur += num;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        ch = new int[1000001];
        scores = new int[N];
        nums = new int[N];
        max = Integer.MIN_VALUE;

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            nums[i] = cur;
            ch[cur] = i + 1;
            max = Math.max(max, cur);
        }
        solution();
        for(int n : scores) System.out.print(n + " ");
    }
}
