import java.util.*;
import java.io.*;

public class Main {
    static int N, max;
    static int[] answer, ch;
    static ArrayList<Integer> arr;

    static void solution() {
        int idx = 0;
        while(idx < arr.size() - 1) {
            int cur = arr.get(idx);
            cur += cur;
            while(cur <= max) {
                if(ch[cur] != 0) {
                    answer[ch[arr.get(idx)]]++;
                    answer[ch[cur]]--;
                }
                cur += arr.get(idx);
            }
            idx++;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        answer = new int[N + 1];
        ch = new int[1000001];
        arr = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            ch[n] = i;
            arr.add(n);
        }
        Collections.sort(arr);
        max = arr.get(arr.size() - 1);
        solution();
        for (int i = 1; i <= N; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}