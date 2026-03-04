import java.io.*;
import java.util.*;

public class Main {
    static int N, S;
    static Node[] graph;
    static class Node {
        int left;
        int right;

        Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static int solution() {
        int answer = 1;
        int[] ch = new int[N + 1];
        Queue<Integer> que = new LinkedList<>();
        que.offer(S);
        ch[S] = 1;
        while (!que.isEmpty()) {
            int cur = que.poll();
            Node n = graph[cur];
            if(n.left != 0 && ch[n.left] == 0) {
                ch[n.left] = 1;
                que.offer(n.left);
                answer++;
            }
            if(n.right != 0 && ch[n.right] == 0) {
                ch[n.right] = 1;
                que.offer(n.right);
                answer++;
            }
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        graph = new Node[N + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            int left = i - cur;
            int right = i + cur;
            if(left >= 1 && right <= N) graph[i] = new Node (left, right);
            else if (left >= 1) graph[i] = new Node (left, 0);
            else if (right <= N) graph[i] = new Node(0, right);
            else graph[i] = new Node(0, 0);
        }
        S = Integer.parseInt(bf.readLine());

        System.out.println(solution());
    }
}
