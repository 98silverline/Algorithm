import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] time, cost, forward;
    static ArrayList<ArrayList<Integer>> arr;
    static Queue<Integer> que;

    static void solution() {
        while (!que.isEmpty()) {
            int cur = que.poll();
            time[cur] += cost[cur];
            for (int n : arr.get(cur)) {
                if(time[n] < time[cur]) time[n] = time[cur];
                forward[n]--;
                if(forward[n] == 0) que.offer(n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        time = new int[N + 1];
        cost = new int[N + 1];
        forward = new int[N + 1];
        arr = new ArrayList<>();

        for (int i = 0; i <= N; i++) arr.add(new ArrayList<>());

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int n = Integer.parseInt(st.nextToken());
                if (n == -1) continue;
                arr.get(n).add(i);
                forward[i]++;
            }
        }

        que = new LinkedList<>();
        for (int i = 1; i <= N; i++) if(forward[i] == 0) que.offer(i);

        solution();

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) sb.append(time[i]).append("\n");
        System.out.println(sb);
    }
}