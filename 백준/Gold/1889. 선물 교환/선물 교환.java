
import java.util.*;
import java.io.*;

public class Main {
    static int N, answer;
    static ArrayList<ArrayList<Integer>> graph1, graph2;
    static int[] forward;
    static boolean[] cant;

    static void solution() {
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if(forward[i] < 2) que.offer(i);
        }

        while(!que.isEmpty()) {
            int cur = que.poll();
            if(cant[cur]) continue;
            cant[cur] = true;
            answer--;
            for(int n : graph1.get(cur)) {
                forward[n]--;
                if(forward[n] < 2) que.offer(n);
            }
            for(int n : graph2.get(cur)) que.offer(n);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        answer = N;
        forward = new int[N + 1];
        cant = new boolean[N + 1];
        graph1 = new ArrayList<>();
        graph2 = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph1.add(new ArrayList<>());
            graph2.add(new ArrayList<>());
        }
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            graph1.get(i).add(n1);
            graph1.get(i).add(n2);
            graph2.get(n1).add(i);
            graph2.get(n2).add(i);
            forward[n1]++;
            forward[n2]++;
        }
        solution();
        if(answer == 0) System.out.println(0);
        else {
            System.out.println(answer);
            for(int i = 1; i <= N; i++) if(!cant[i]) System.out.print(i + " ");
        }
    }
}