import java.util.*;
import java.io.*;

public class Main {
    static int N, min, ans1, ans2;
    static int[] arr;

    static void solution() {
        int pt1 = 0;
        int pt2 = N - 1;
        while(pt1 < pt2) {
            int n = arr[pt1] + arr[pt2];
            if(Math.abs(n) < min) {
                min = Math.abs(n);
                ans1 = arr[pt1];
                ans2 = arr[pt2];
            }
            if(n < 0) pt1++;
            else pt2--;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        min = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        solution();
        System.out.println(ans1 + " " + ans2);
    }
}