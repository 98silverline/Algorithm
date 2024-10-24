import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N;
    static int[] row, col;

    public static void solution(int zero, int one, int two) {
        Queue<Integer> que = new LinkedList<>();
        for(int i = 0; i < zero; i++) que.offer(0);
        for(int i = 0; i < one; i++) que.offer(1);
        for(int i = 0; i < two; i++) que.offer(2);

        for(int i = M - 1; i > 0; i--) {
             row[i] += que.poll();
        }
        for(int i = 0; i < M; i++) {
            col[i] += que.poll();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        row = new int[M];
        col = new int[M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            solution(zero, one, two);
        }

        for(int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (i > 0 && j == 0) System.out.print(row[i] + 1 + " ");
                else System.out.print(Math.max(row[i], col[j]) + 1 + " ");
            }
            System.out.println();
        }
    }
}
