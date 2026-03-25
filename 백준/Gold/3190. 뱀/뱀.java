
import java.io.*;
import java.util.*;
public class Main {
    static int N, K;
    static int[][] map, ch;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        K = Integer.parseInt(bf.readLine());
        map = new int[N + 2][N + 2];
        ch = new int[N + 2][N + 2];
        Deque<int[]> deq = new ArrayDeque<>();

        for(int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
        }
        int time = 0;
        int cnt = Integer.parseInt(bf.readLine()) - 1;
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int chgT = Integer.parseInt(st.nextToken());
        String chgD = st.nextToken();

        int dist = 3;
        int x = 1;
        int y = 1;
        ch[x][y] = 1;
        deq.offerFirst(new int[] {x, y});
        while(true) {
            int nx = x + dx[dist];
            int ny = y + dy[dist];
            if(nx > 0 && ny > 0 && nx <= N && ny <= N && ch[nx][ny] == 0) {
                x = nx;
                y = ny;
                ch[nx][ny] = 1;
                deq.offerFirst(new int[] {nx, ny});
                if(map[nx][ny] == 1) {
                    map[nx][ny] = 0;
                } else {
                    int[] cur = deq.pollLast();
                    ch[cur[0]][cur[1]] = 0;
                }
                time++;
                if(chgT == time) {
                    if(chgD.equals("D")) {
                        dist -= 1;
                        if(dist < 0) dist = 3;
                    } else {
                        dist += 1;
                        if(dist > 3) dist = 0;
                    }
                    if(cnt > 0) {
                        st = new StringTokenizer(bf.readLine());
                        chgT = Integer.parseInt(st.nextToken());
                        chgD = st.nextToken();
                        cnt--;
                    }
                }
            } else {
                time++;
                break;
            }
        }

        System.out.println(time);

    }
}
