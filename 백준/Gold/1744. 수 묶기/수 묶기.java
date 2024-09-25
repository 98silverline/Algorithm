import java.util.*;
import java.io.*;

public class Main {
    static int N, answer, zero;

    static PriorityQueue<Integer> pq1, pq2;

    static void solution(PriorityQueue<Integer> pq) {

        while(!pq.isEmpty()) {
            if(pq.size() == 1) {
                if(pq.peek() > 0) answer += pq.poll();
                else if (pq.peek() < 0 && zero > 0) {
                    zero--;
                } else answer += pq.poll();
                return;
            }
            int num1 = pq.poll();
            int num2 = pq.poll();
            if(num1 * num2 > num1 + num2) answer += num1 * num2;
            else answer += num1 + num2;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        answer = 0;
        zero = 0;
        pq1 = new PriorityQueue<>(Collections.reverseOrder());
        pq2 = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(bf.readLine());
            if(n == 0) {
                zero++;
                continue;
            }
            if(n > 0) pq1.offer(n);
            else if (n < 0) pq2.offer(n);
        }
        solution(pq1);
        solution(pq2);
        System.out.println(answer);
    }
}