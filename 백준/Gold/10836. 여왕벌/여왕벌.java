import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N;
    static int[][] map, grow;
    static int[] dx = {-1, -1, 0};
    static int[] dy = {0, -1, -1};

    public static void solution(int zero, int one, int two) {
        Queue<Integer> que = new LinkedList<>();
        for(int i = 0; i < zero; i++) que.offer(0);
        for(int i = 0; i < one; i++) que.offer(1);
        for(int i = 0; i < two; i++) que.offer(2);

        for(int i = M - 1; i > 0; i--) {
            grow[i][0] = que.poll();
            map[i][0] += grow[i][0];
        }
        for(int i = 0; i < M; i++) {
            grow[0][i] = que.poll();
            map[0][i] += grow[0][i];
        }

        for(int i = 1; i < M; i++) {
            for (int j = 1; j < M; j++) {
                int max = 0;
                for(int k = 0; k < 3; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx >= 0 && ny >= 0 && nx < M && ny < M) max = Math.max(max, grow[nx][ny]);
                }
                grow[i][j] = max;
                map[i][j] += max;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][M];
        grow = new int[M][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            solution(zero, one, two);
        }

        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print((anInt + 1) + " ");
            }
            System.out.println();
        }
    }
}
