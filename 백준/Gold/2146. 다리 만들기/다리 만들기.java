import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map, island;
    static ArrayList<PriorityQueue<Point>> list;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Point implements Comparable<Point>{
        int x;
        int y;
        int cost;

        Point(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return this.cost - o.cost;
        }
    }

    //다리 길이 체크
    static int bridgeCheck(int num) {
        int answer = Integer.MAX_VALUE;
        int[][] ch = new int[N][N];
        PriorityQueue<Point> pq = list.get(num);
        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < N && ny < N && nx >= 0 && ny >= 0) {
                    if(island[nx][ny] == 0 && ch[nx][ny] == 0) {
                        ch[nx][ny] = cur.cost + 1;
                        pq.offer(new Point(nx, ny, cur.cost + 1));
                    } else if (island[nx][ny] != 0 && island[nx][ny] != num) answer = Math.min(answer, cur.cost);
                }
            }
        }

        return answer;
    }

    //섬 체크
    static void islandCheck(int num, Point p) {
        while(list.size() <= num) {
            list.add(new PriorityQueue<>());
        }

        PriorityQueue<Point> pq = list.get(num);
        Queue<Point> que = new LinkedList<>();
        island[p.x][p.y] = num;
        que.offer(p);
        while (!que.isEmpty()) {
            Point cur = que.poll();
            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < N && ny < N && nx >= 0 && ny >= 0) {
                    if(map[nx][ny] == 1 && island[nx][ny] == 0) {
                        island[nx][ny] = num;
                        que.offer(new Point(nx, ny, 0));
                    } else if (map[nx][ny] == 0) {
                        flag = true;
                    }
                }
            }
            if(flag) pq.offer(new Point(cur.x, cur.y, 0));
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        island = new int[N][N];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1 && island[i][j] == 0) {
                    islandCheck(cnt, new Point(i, j, 0));
                    cnt++;
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i < cnt; i++) {
            answer = Math.min(answer, bridgeCheck(i));
            if(answer == 1) break;
        }
        System.out.println(answer);
    }
}
