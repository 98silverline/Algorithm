import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int T, N, M;
    static int[][] graph;
    static int[] forward, rank;
    static boolean[] ch;
    static Queue<Rank> que;
    static Stack<Integer> stk;

    static class Rank {
        int node;
        int ran;

        public Rank(int node, int ran) {
            this.node = node;
            this.ran = ran;
        }
    }

    static String solution() {
        int cnt = 0;
        que = new LinkedList<>();
        for (int i = 1; i <= N; i++) if(forward[i] == 0) que.offer(new Rank(i, 1));
        while (!que.isEmpty()) {
            Rank cur = que.poll();
            if(rank[cur.ran] != 0) return "?";
            rank[cur.ran] = cur.node;
            cnt++;
            for(int i = 1; i <= N; i++) {
                if(graph[cur.node][i] == 1 && i != cur.node) {
                    forward[i]--;
                    if(forward[i] == 0) que.offer(new Rank(i, cur.ran + 1));
                }
            }
        }
        if(cnt < N) return "IMPOSSIBLE";
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) sb.append(rank[i]).append(" ");
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            N = Integer.parseInt(bf.readLine());
            graph = new int[N + 1][N + 1];
            forward = new int[N + 1];
            rank = new int[N + 1];
            ch = new boolean[N + 1];
            stk = new Stack<>();
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) stk.push(Integer.valueOf(st.nextToken()));
            for (int i = 0; i < N; i++) {
                int cur = stk.pop();
                ch[cur] = true;
                for(int j = 1; j <= N; j++) if(!ch[j]) {
                    graph[j][cur] = 1;
                    forward[cur]++;
                }
            }

            M = Integer.parseInt(bf.readLine());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(bf.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                if(graph[end][start] == 1) {
                    int tmp = start;
                    start = end;
                    end = tmp;
                }
                graph[start][end] = 0;
                graph[end][start] = 1;
                forward[end]--;
                forward[start]++;
            }

            System.out.println(solution());
        }
    }
}