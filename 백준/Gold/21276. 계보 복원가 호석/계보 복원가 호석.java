import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static Map<String, Integer> index;
    static String[] name;
    static ArrayList<ArrayList<String>> graph;
    static int[] forward;
    static PriorityQueue<Person> pq;
    static StringBuilder answer;

    static class Person implements Comparable<Person> {
        String name;
        int level;

        public Person(String name, int level) {
            this.name = name;
            this.level = level;
        }

        @Override
        public int compareTo(Person o) {
            if (this.level == o.level) return this.name.compareTo(o.name);
            return this.level - o.level;
        }
    }

    static void solution() {
        PriorityQueue<String> count = new PriorityQueue<>();
        PriorityQueue<String> child = new PriorityQueue<>();

        while(!pq.isEmpty()){
            StringBuilder sb = new StringBuilder();
            Person p = pq.poll();
            for (String cur : graph.get(index.get(p.name))) {
                int idx = index.get(cur);   //자손의 인덱스
                forward[idx]--;
                if(forward[idx] == 0) {
                    child.offer(cur);     //자녀였으면 추가
                    pq.offer(new Person(cur, p.level + 1));
                }
            }
            sb.append(p.name).append(" ").append(child.size());
            if(!child.isEmpty()) sb.append(" ");
            while(!child.isEmpty()) {
                sb.append(child.poll());
                if(!child.isEmpty()) sb.append(" ");
            }
            sb.append("\n");
            count.offer(sb.toString());
        }
        while(!count.isEmpty()) answer.append(count.poll());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        index = new HashMap<>();
        name = new String[N];
        forward = new int[N];
        graph = new ArrayList<>();
        pq = new PriorityQueue<>();
        answer = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            String str = st.nextToken();
            graph.add(new ArrayList<>());
            index.put(str, i);
            name[i] = str;
        }
        M = Integer.parseInt(bf.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            String X = st.nextToken();
            String Y = st.nextToken();
            graph.get(index.get(Y)).add(X);
            forward[index.get(X)]++;
        }
        for (int i = 0; i < N; i++) if(forward[i] == 0) pq.offer(new Person(name[i], 0));
        answer.append(pq.size()).append("\n");
        while(!pq.isEmpty()) {
            Person p = pq.poll();
            answer.append(p.name);
            if(!pq.isEmpty()) answer.append(" ");
        }
        answer.append("\n");
        for (int i = 0; i < N; i++) if(forward[i] == 0) pq.offer(new Person(name[i], 0));
        solution();
        System.out.println(answer);
    }
}