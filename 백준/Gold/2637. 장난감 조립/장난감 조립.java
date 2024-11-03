
import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] need;
    static boolean[] middle;
    static ArrayList<ArrayList<Part>> graph;

    static class Part {
        int num;    //부품 번호
        int cnt;    //필요 부품 개수

        public Part(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    static void solution() {
        Queue<Integer> que = new LinkedList<>();
        que.offer(N);
        while (!que.isEmpty()) {
            int cur = que.poll();
            if(!middle[cur] || need[cur] == 0) continue;   //기본 부품임
            for (Part p : graph.get(cur)) {
                need[p.num] += p.cnt * need[cur];
                que.offer(p.num);
            }
            need[cur] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());    //완제품 번호
        M = Integer.parseInt(bf.readLine());
        need = new int[N + 1];
        middle = new boolean[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int X = Integer.parseInt(st.nextToken());   //중간(혹은 완제품)부품
            int Y = Integer.parseInt(st.nextToken());   //X를 만드는데 필요한 부품
            int K = Integer.parseInt(st.nextToken());   //필요한 Y 개수
            graph.get(X).add(new Part(Y, K));
            middle[X] = true;
        }

        need[N] = 1;
        solution();

        for (int i = 1; i <= N; i++) {
            if(!middle[i]) System.out.println(i + " " + need[i]);
        }
    }
}