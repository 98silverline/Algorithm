import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] T = new int[N + 1];
        int[] P = new int[N + 1];
        int[] sub = new int[N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
            if(i + T[i] <= N) {
                sub[i + T[i]] = Math.max(sub[i + T[i]], P[i] + sub[i]);
            }
            sub[i + 1] = Math.max(sub[i + 1], sub[i]);
        }

        System.out.println(sub[N]);
    }
}
