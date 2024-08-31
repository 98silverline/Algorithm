

import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, answer, maxConnect, willConnect;
    static int[] ch, on;
    static int[][] map, chCol, chRow;
    static ArrayList<Point> cores;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void simulation() {
        int cnt = 0;
        int curConnect = 0;
        int[][] newMap = new int[N][N];
        for(int i = 0; i < cores.size(); i++) {
            if(on[i] == 0) continue;
            int cur = 0;
            Point core = cores.get(i);
            if(core.x == 0 || core.x == N - 1 || core.y == 0 || core.y == N - 1) continue;
            int dist = 1;
            while(true) {
                int nx = core.x + (dx[ch[i]] * dist);
                int ny = core.y + (dy[ch[i]] * dist);
                if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if(newMap[nx][ny] == 1 || map[nx][ny] == 1) break;
                    else {
                        newMap[nx][ny] = 1;
                        cur++;
                        dist++;
                    }
                } else {
                    cnt += cur;
                    curConnect++;
                    break;
                };
            }
        }
        if(maxConnect < curConnect) {
            maxConnect = curConnect;
            answer = cnt;
        } else if (maxConnect == curConnect) {
            answer = Math.min(answer, cnt);
        }
    }

    static void DFS(int L) {
        if(willConnect + (cores.size() - L) < maxConnect) return;
        if(L == cores.size()) {
            if(willConnect < maxConnect) return;
            else simulation();
        } else {
            Point p = cores.get(L);
            if(p.x == 0 || p.x == N - 1 || p.y == 0 || p.y == N - 1) {
                DFS(L + 1);
            }
             else {
                 int cant = 0;
                 for(int i = 0; i < 4; i++) {
                    if(i == 0 && chCol[p.y][0] != -1 && chCol[p.y][0] < p.x) {
                        cant++;
                        continue;
                    }
                    if(i == 1 && chRow[p.x][0] != -1 && chRow[p.x][0] < p.y) {
                        cant++;
                        continue;
                    }
                    if(i == 2 && chCol[p.y][1] > p.x) {
                        cant++;
                        continue;
                    }
                    if(i == 3 && chRow[p.x][1] > p.y) {
                        cant++;
                        continue;
                    }
                    ch[L] = i;
                    willConnect++;
                    on[L] = 1;
                    DFS(L + 1);
                    willConnect--;
                    on[L] = 0;
                    DFS(L + 1);
                }
                 if(cant == 4) DFS(L + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            N = Integer.parseInt(bf.readLine());
            chCol = new int[N][2];
            chRow = new int[N][2];
            for(int[] arr : chCol) Arrays.fill(arr, -1);
            for(int[] arr : chRow) Arrays.fill(arr, -1);
            answer = 0;
            map = new int[N][N];
            cores = new ArrayList<>();
            maxConnect = 0;
            willConnect = 0;
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for(int j = 0; j < N; j++) {
                    int n = Integer.parseInt(st.nextToken());
                    map[i][j] = n;
                    if(n == 1) {
                        cores.add(new Point(i, j));
                        if(chRow[i][0] == -1) chRow[i][0] = j;
                        else chRow[i][1] = j;
                        if(chCol[j][0] == -1) chCol[j][0] = i;
                        else chCol[j][1] = i;
                    }
                }
            }
            ch = new int[cores.size()];
            on = new int[cores.size()];
            DFS(0);
            sb.append(answer + "\n");
        }
        System.out.println(sb.toString());
    }
}
