
import java.util.*;
import java.io.*;

public class Main {
    static int N, K, idx;
    static long answer;
    static int[] bags;
    static Jewel[] jewels;
    static PriorityQueue<Integer> pq;
    static class Jewel implements Comparable<Jewel>{
        int M;  //무게
        int V;  //가격
        public Jewel(int m, int v) {
            M = m;
            V = v;
        }

        @Override
        public int compareTo(Jewel o) {
            return this.M - o.M;
        }
    }
    static void solution(){
        pq = new PriorityQueue<>(Collections.reverseOrder());
        int jewelIndex = 0;
        for (int i = 0; i < K; i++) {
            // 현재 가방의 무게 용량에 맞는 보석들을 pq에 추가
            while (jewelIndex < N && jewels[jewelIndex].M <= bags[i]) {
                pq.offer(jewels[jewelIndex].V);
                jewelIndex++;
            }
            // 현재 가방에 가장 가치가 높은 보석을 넣음
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jewels = new Jewel[N];
        bags = new int[K];

        answer = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            jewels[i] = (new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(jewels);
        Arrays.sort(bags);

        solution();
        System.out.println((long) answer);
    }
}