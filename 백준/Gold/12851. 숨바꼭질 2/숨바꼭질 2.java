
import java.util.*;
import java.io.*;

public class Main {
    static int N, K, max, answer;
    static int[] arr;
    static PriorityQueue<Point> que;

    static class Point {
        int x;
        int cost;

        public Point(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
    }
    static void solution(){
        que.offer(new Point(N, 0));
        arr[N] = 0;
        while(!que.isEmpty()) {
            Point p = que.poll();
            if(p.cost + 1 > arr[K]) return;
            int plus = p.x + 1;
            int minus = p.x - 1;
            int dou = p.x + p.x;
            if(plus <= max) {
                if(plus == K) {
                    arr[plus] = p.cost + 1;
                    answer++;
                } else if (arr[plus] >= p.cost + 1){
                    arr[plus] = p.cost + 1;
                    que.offer(new Point(plus, p.cost + 1));
                }
            }
            if(minus <= max && minus >= 0) {
                if(minus == K) {
                    arr[minus] = p.cost + 1;
                    answer++;
                }
                else if (arr[minus] >= p.cost + 1) {
                    arr[minus] = p.cost + 1;
                    que.offer(new Point(minus, p.cost + 1));
                }
            }
            if(dou <= max) {
                if(dou == K) {
                    arr[dou] = p.cost + 1;
                    answer++;
                }
                else if (arr[dou] >= p.cost + 1) {
                    arr[dou] = p.cost + 1;
                    que.offer(new Point(dou, p.cost + 1));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        if(N > K) max = N + 1;
        else max = K + 100;
        arr = new int[max + 1];
        if(N == K) {
            arr[K] = 0;
            answer = 1;
        }
        Arrays.fill(arr, Integer.MAX_VALUE);
        solution();
        System.out.println(arr[K]);
        System.out.println(answer);
    }
}