import java.io.*;
import java.util.*;

public class Main {
    static int answer;
    static int[][] ch;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Queue<Point> del;

    static void pang (Queue<Point> del) {
        int[] chC = new int[6];
        while(!del.isEmpty()) {
            Point p = del.poll();
            map[p.x][p.y] = '.';
            chC[p.y] = 1;
        }
//        System.out.println("start drop!");
//        for (char[] chars : map) {
//            for (char aChar : chars) {
//                System.out.print(aChar);
//            }
//            System.out.println();
//        }
//        System.out.println();

        //블록 떨구기
        for(int i = 0; i < 6; i++) {
            //해당 열에서 뿌요뿌요 터졌음
            if(chC[i] == 1) {
                for(int j = 11; j >= 0; j--) {
                    //빈 공간임
                    if(map[j][i] == '.') {
                        boolean flag = false;
                        int start = j - 1;
                        while(start >= 0) {
                            if(map[start][i] != '.') {
                                map[j][i] = map[start][i];
                                map[start][i] = '.';
                                flag = true;
                                break;
                            }
                            start--;
                        }
                        //더이상 위에 블록이 없다
                        if(!flag) break;
                    }
                }
            }
        }
    }
    static boolean solution(Point start) {
        Queue<Point> que = new LinkedList<>();  //블록 세는거
        Queue<Point> q = new LinkedList<>();  //삭제할 블록들(확정은 아님)
        que.offer(start);
        int cnt = 1;
        ch[start.x][start.y] = 1;
        while(!que.isEmpty()) {
            Point p = que.poll();
            q.offer(new Point(p.x, p.y));
            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                //해당 위치가 유효 좌표이면서 같은 색의 블록이 있다면
                if(nx >= 0 && ny >= 0 && nx < 12 && ny < 6 && ch[nx][ny] == 0 && map[nx][ny] == p.color) {
                    ch[nx][ny] = 1;
                    cnt++;
                    que.offer(new Point(nx, ny, p.color));
                }
            }
        }
        if(cnt >= 4) {
            while(!q.isEmpty()) del.offer(q.poll());
            return true;
        } else return false;
    }

    static class Point {
        int x, y;
        char color;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        ch = new int[12][6];
        del = new LinkedList<>();  //삭제할 블록들
        answer = 0;
        for(int i = 0; i < 12; i++) {
            String str = bf.readLine();
            for(int j = 0; j < 6; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        boolean flag = true;
        while(flag) {
            ch = new int[12][6];
//            for (char[] chars : map) {
//                for (char aChar : chars) {
//                    System.out.print(aChar);
//                }
//                System.out.println();
//            }
//            System.out.println();

            for(int i = 0; i < 12; i++) {
                for(int j = 0; j < 6; j++) {
                    if(map[i][j] != '.') {
                        solution(new Point(i, j, map[i][j]));
                    }
                }
            }
            if(!del.isEmpty()) {
                pang(del);
                answer++;
            } else flag = false;
        }
        System.out.println(answer);
    }
}