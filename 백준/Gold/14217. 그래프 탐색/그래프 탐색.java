import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, Q;
    static ArrayList<ArrayList<Integer>> graph;

    static class Point {
        int node, dist;

        public Point(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static void solution() {

        Queue<Integer> que = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        que.offer(1);
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        dist[1] = 0;
        while(!que.isEmpty()) {
            int cur = que.poll();
            for (int node : graph.get(cur)) {
                if(dist[node] == -1) {
                    dist[node] = dist[cur] + 1;
                    que.offer(node);
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        Q = Integer.parseInt(bf.readLine());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            if (a == 1) {
                graph.get(node1).add(node2);
                graph.get(node2).add(node1);
            } else {
                for (int j = 0; j < graph.get(node1).size(); j++) {
                    if(graph.get(node1).get(j) == node2) {
                        graph.get(node1).remove(j);
                        break;
                    }
                }
                for (int j = 0; j < graph.get(node2).size(); j++) {
                    if(graph.get(node2).get(j) == node1) {
                        graph.get(node2).remove(j);
                        break;
                    }
                }
            }
            solution();
        }
    }
}