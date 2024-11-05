
import java.util.*;
import java.io.*;

public class Main {
    static int N, answer;
    static PriorityQueue<Homework> pq;
    static int[] ch;

    static class Homework implements Comparable<Homework>{
        int time;
        int score;

        public Homework(int time, int score) {
            this.time = time;
            this.score = score;
        }

        @Override
        public int compareTo(Homework o) {
            if(this.score == o.score) return o.time - this.time;
            return o.score - this.score;
        }
    }

    static void solution() {
        while(!pq.isEmpty()) {
            Homework cur = pq.poll();
            for(int i = cur.time; i >= 1; i--) {
                if(ch[i] == 0) {
                    ch[i] = 1;
                    answer += cur.score;
                    break;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        pq = new PriorityQueue<>();
        ch = new int[1001];
        answer = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int time = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            pq.offer(new Homework(time, score));
        }
        solution();
        System.out.println(answer);
    }
}