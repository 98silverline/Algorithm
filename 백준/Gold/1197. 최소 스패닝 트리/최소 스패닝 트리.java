import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E, answer;
    static boolean[] visited;
    static PriorityQueue<Node> pq;
    static ArrayList<ArrayList<Node>> graph;

    static class Node implements Comparable<Node>{
        int point;
        int cost;

        public Node(int point, int cost) {
            this.point = point;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static void solution() {
        pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if(visited[node.point]) continue;
            visited[node.point] = true;
            answer += node.cost;
            ArrayList<Node> curGraph = graph.get(node.point);
            for (Node cur : curGraph) {
                if (!visited[cur.point]) pq.offer(cur);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken()); //정점의 개수
        E = Integer.parseInt(st.nextToken()); //간선의 개수
        graph = new ArrayList<>();
        visited = new boolean[V + 1];
        for (int i = 0; i <= V; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(node1).add(new Node(node2, cost));
            graph.get(node2).add(new Node(node1, cost));
        }

        solution();
        System.out.println(answer);
    }
}