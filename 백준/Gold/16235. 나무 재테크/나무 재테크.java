import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] power, map;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static Deque<Tree> live;
    static Queue<Tree> death, breed;

    static class Tree {
        int x, y, age, time;

        public Tree(int x, int y, int age, int time) {
            this.x = x;
            this.y = y;
            this.age = age;
            this.time = time;
        }
    }

    static void solution() {
        int curT = 0;
        for (int i = 0; i < K; i++) {
            //봄 - 양분먹고 나이 + 1
            while(!live.isEmpty()) {
                Tree cur = live.poll();
                //현재 심을 나무 모두 심으면 while문 탈출
                if(cur.time > curT) {
                    live.offerFirst(cur);
                    break;
                }
                //나무가 먹을 양분이 부족하면 죽음
                if(map[cur.x][cur.y] < cur.age) death.offer(cur);
                //먹을 양분이 있으면 나무 나이만큼 양분 빼고 나이 1 추가, 시간 1 추가 해서 live에 다시 넣어줌
                else {
                    map[cur.x][cur.y] -= cur.age;
                    Tree t = new Tree(cur.x, cur.y, cur.age + 1, cur.time + 1);
                    live.offer(t);
                    //나무 나이 5의 배수 되면 번식 큐에 넣어줌
                    if(t.age % 5 == 0) breed.offer(t);
                }
            }
            curT++;

            //여름 - 죽은 나무들 양분 추가
            while(!death.isEmpty()) {
                Tree cur = death.poll();
                map[cur.x][cur.y] += cur.age / 2;
            }

            //가을 - 나이 5의 배수인 나무들 번식
            while(!breed.isEmpty()) {
                Tree cur = breed.poll();
                for(int j = 0; j < 8; j++) {
                    int nx = cur.x + dx[j];
                    int ny = cur.y + dy[j];
                    //만약 유효좌표라면
                    if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
                        //새로운 나무 추가
                        live.offerFirst(new Tree(nx, ny, 1, cur.time));
                    }
                }
            }

            //겨울 - 로봇이 양분 추가
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    map[j][k] += power[j][k];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());   //땅 크기 N * N
        M = Integer.parseInt(st.nextToken());   //초기 나무 수
        K = Integer.parseInt(st.nextToken());   //몇년 후 결과 구할건지
        map = new int[N][N];                    //지도
        power = new int[N][N];                  //로봇이 매년 추가하는 양분
        live = new LinkedList<>();           //살아있는 나무들
        death = new LinkedList<>();             //죽은 나무들
        breed = new LinkedList<>();             //번식하는 나무들

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                power[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            live.offerFirst(new Tree(x - 1, y - 1, age, 0));
        }

        for (int i = 0; i < N; i++) Arrays.fill(map[i], 5);

        solution();
        System.out.println(live.size());
    }
}