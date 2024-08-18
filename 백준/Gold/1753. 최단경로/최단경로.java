import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E, K;
    static int[] cost, ch;
    static PriorityQueue<Node> pq;
    static ArrayList<ArrayList<Node>> arr;


    static class Node implements Comparable<Node> {
        int point;
        int cost;

        public Node(int point, int cost) {
            this.point = point;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            if(this.cost == o.cost) return this.point - o.point;
            else return this.cost - o.cost;
        }
    }

    static void BFS() {
        while (!pq.isEmpty()) {
            Node n = pq.poll();
//            System.out.println("n.point = " + n.point + ", n.cost = " + n.cost);
            if(ch[n.point] == 1 && cost[n.point] <= n.cost) continue;
            else {
                ch[n.point] = 1;
                cost[n.point] = n.cost;
            }
            for (Node node : arr.get(n.point)) {
                pq.add(new Node(node.point, n.cost + node.cost));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(bf.readLine());
        ch = new int[V + 1];
        cost = new int[V + 1];
        ch[K] = 1;
        arr = new ArrayList<>();
        pq = new PriorityQueue<>();
        for (int i = 0; i <= V; i++) arr.add(new ArrayList<>());
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr.get(a).add(new Node(b, c));
        }
        for(Node node : arr.get(K)) pq.add(new Node(node.point, node.cost));
        BFS();
        for (int i = 1; i <= V; i++) {
            if(ch[i] == 0) System.out.println("INF");
            else System.out.println(cost[i]);
        }
    }
}