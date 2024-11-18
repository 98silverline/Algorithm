import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int answer;
    static int[] forward, cost;
    static ArrayList<ArrayList<Integer>> graph;

    static class Project implements Comparable<Project>{
        int time, index;

        public Project(int time, int index) {
            this.index = index;
            this.time = time;
        }

        @Override
        public int compareTo(Project o) {
            return this.time - o.time;
        }
    }

    static void solution() {
        PriorityQueue<Project> pq = new PriorityQueue<>();
        for (int i = 0; i < 26; i++) if(forward[i] == 0 && cost[i] != 0) pq.offer(new Project(cost[i], i));

        while (!pq.isEmpty()) {
            Project cur = pq.poll();
            answer = cur.time;
            for(int n : graph.get(cur.index)) {
                forward[n]--;
                if(forward[n] == 0) {
                    pq.offer(new Project(cost[n] + cur.time, n));
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        cost = new int[26];
        forward = new int[26];
        graph = new ArrayList<>();

        for (int i = 0; i < 26; i++) graph.add(new ArrayList<>());

        String line;
        while ((line = bf.readLine()) != null && !line.isEmpty()) {
            StringTokenizer st = new StringTokenizer(line);
            String task = st.nextToken();
            int work = task.charAt(0) - 'A';
            int cos = Integer.parseInt(st.nextToken());
            cost[work] = cos;

            if (st.hasMoreTokens()) {
                String first = st.nextToken();
                for (int i = 0; i < first.length(); i++) {
                    int fst = first.charAt(i) - 'A';
                    forward[work]++;
                    graph.get(fst).add(work);
                }
            }
        }

        solution();
        System.out.println(answer);
    }

}