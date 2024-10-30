import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, start, end, road;
    static int[] forward, cost;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> graph, reverse;
    static Queue<Integer> que;

    static class Node {
        int x;
        int cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
    }

    //최장거리 찾기
    static void solution() {
        que = new LinkedList<>();
        que.offer(start);
        while (!que.isEmpty()) {
            int cur = que.poll();
            for (Node node : graph.get(cur)) {
                forward[node.x]--;
                if(forward[node.x] == 0) que.offer(node.x);
                int curCost = cost[cur] + node.cost;
                if(curCost > cost[node.x]) cost[node.x] = curCost;
            }
        }
    }

    //경로 찾기
    static void solution2() {
        que = new LinkedList<>();
        que.offer(end);
        visited[end] = true;
        while (!que.isEmpty()) {
            int cur = que.poll();
            for (Node node : reverse.get(cur)) {
                if(cost[cur] - node.cost == cost[node.x]) {
                    road++;
                    if(!visited[node.x]) {
                        visited[node.x] = true;
                        que.offer(node.x);
                    }
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());    //도시의 개수
        M = Integer.parseInt(bf.readLine());    //도로의 수
        forward = new int[N + 1];
        cost = new int[N + 1];
        graph = new ArrayList<>();
        reverse = new ArrayList<>();
        visited = new boolean[N + 1];
        road = 0;
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            forward[end]++;
            graph.get(start).add(new Node(end, cost));
            reverse.get(end).add(new Node(start, cost));
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        solution();
        solution2();

        System.out.println(cost[end]);
        System.out.println(road);
    }
}