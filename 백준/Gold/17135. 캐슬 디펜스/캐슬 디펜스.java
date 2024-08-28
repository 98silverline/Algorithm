import java.io.*;
import java.util.*;

public class Main {
    static int N, M, D;
    static int[][] map;
    static int[] archers = new int[3]; // 궁수의 위치를 저장할 배열
    static int maxEnemies = 0;

    // 적의 위치를 저장할 클래스
    static class Enemy {
        int x, y;

        public Enemy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 궁수 배치 조합을 만들기 위한 메서드
    static void combination(int start, int depth) {
        if (depth == 3) {
            // 궁수 배치를 다 정했으면 시뮬레이션 실행
            simulate();
            return;
        }

        for (int i = start; i < M; i++) {
            archers[depth] = i;
            combination(i + 1, depth + 1);
        }
    }

    // 게임 시뮬레이션 메서드
    static void simulate() {
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }

        int killedEnemies = 0;

        while (true) {
            // 현재 남아있는 적들 위치를 찾기 위한 리스트
            List<Enemy> enemies = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (copyMap[i][j] == 1) {
                        enemies.add(new Enemy(i, j));
                    }
                }
            }

            if (enemies.size() == 0) break; // 남아있는 적이 없으면 종료

            // 각 궁수가 공격할 적 위치를 저장하는 셋
            Set<Enemy> targets = new HashSet<>();

            for (int archer : archers) {
                int minDist = Integer.MAX_VALUE;
                Enemy target = null;

                // 가장 가까운 적을 찾는다
                for (Enemy enemy : enemies) {
                    int dist = Math.abs(N - enemy.x) + Math.abs(archer - enemy.y);
                    if (dist <= D) { // 공격 범위 내에 있는 적인지 확인
                        if (dist < minDist) {
                            minDist = dist;
                            target = enemy;
                        } else if (dist == minDist) {
                            // 거리가 같을 때는 더 왼쪽에 있는 적을 우선으로
                            if (enemy.y < target.y) {
                                target = enemy;
                            }
                        }
                    }
                }

                if (target != null) {
                    targets.add(target);
                }
            }

            // 선택된 적들 제거
            for (Enemy enemy : targets) {
                if (copyMap[enemy.x][enemy.y] == 1) {
                    copyMap[enemy.x][enemy.y] = 0;
                    killedEnemies++;
                }
            }

            // 적 이동
            for (int i = N - 1; i > 0; i--) {
                for (int j = 0; j < M; j++) {
                    copyMap[i][j] = copyMap[i - 1][j];
                }
            }
            Arrays.fill(copyMap[0], 0); // 성으로 도달한 적들은 제거
        }

        maxEnemies = Math.max(maxEnemies, killedEnemies); // 최대 적 수 업데이트
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 궁수 배치 조합 생성
        combination(0, 0);

        System.out.println(maxEnemies); // 최대 적 수 출력
    }
}