import java.io.*;
import java.util.*;

public class Main {
    static int N, M, maxSize, ansBag;
    static int[] bags;
    static long[] bag;
    static Obj[] objects;
    static Obj answer;

    static class Obj {
        int weight;
        long cost;

        public Obj(int weight, long cost) {
            this.weight = weight;
            this.cost = cost;
        }
    }

    static void solution() {
        bag = new long[maxSize + 1];

        for(int i = 0; i < N; i++) {
            Obj cur = objects[i];
            if(cur.cost == 0) continue;
            for(int j = maxSize; j >= cur.weight; j--) {
                bag[j] = Math.max(bag[j], cur.cost + bag[j - cur.weight]);
            }
        }

        answer = new Obj(bags[0], bag[bags[0]]);
        ansBag = 1;
        for(int i = 1; i < M; i++) {
            long ans = answer.cost * bags[i];
            long cur = bag[bags[i]] * answer.weight;
//            System.out.println(ans + ", " + cur);
            if(ans < cur) {
                answer = new Obj(bags[i], bag[bags[i]]);
                ansBag = i + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken()); //물품의 수
        M = Integer.parseInt(st.nextToken()); //가방 수
        objects = new Obj[N];
        bags = new int[M];
        maxSize = 0;
        ansBag = 0;

        //물건 정보 입력
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int w = Integer.parseInt(st.nextToken());   //물건의 무게
            int v = Integer.parseInt(st.nextToken());   //물건의 가치
            objects[i] = new Obj(w, v);
        }

        //가방 정보 입력
        for (int i = 0; i < M; i++) {
            bags[i] = Integer.parseInt(bf.readLine());
            maxSize = Math.max(bags[i], maxSize);
        }

        solution();
        System.out.println(ansBag);
    }
}