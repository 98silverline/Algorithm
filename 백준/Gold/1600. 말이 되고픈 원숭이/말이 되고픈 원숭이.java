import java.io.*;
import java.util.*;

public class Main {
	static int K, N, M, answer;
	static int[][] map;
	static int[][][] ch;
	static PriorityQueue<Point> pq;
	static int[] hdx = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] hdy = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static class Point implements Comparable<Point> {
		int x, y, cost, cnt;
		public Point(int x, int y, int cost, int cnt) {
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Point o) {
			if(this.cnt == o.cnt) return this.cost - o.cost;
			return this.cnt - o.cnt;
		}
	}
	
	static void solution() {
		while(!pq.isEmpty()) {
			Point p = pq.poll();
//			System.out.print(ch[p.x][p.y] + ", " + p.cnt);
			if(ch[p.x][p.y][p.cost] == 1) continue;
			else ch[p.x][p.y][p.cost] = 1;
			if(p.x == N - 1 && p.y == M - 1) {
				answer = Math.min(answer, p.cnt);
				continue;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx >= 0 && ny >= 0 && nx < N && ny < M && ch[nx][ny][p.cost] == 0 && map[nx][ny] == 0) {
					pq.offer(new Point(nx, ny, p.cost, p.cnt + 1));
				}
			}
			
			if(p.cost < K) {
				for(int i = 0; i < 8; i++) {
					int nx = p.x + hdx[i];
					int ny = p.y + hdy[i];
					if(nx >= 0 && ny >= 0 && nx < N && ny < M && ch[nx][ny][p.cost + 1] == 0 && map[nx][ny] == 0) {
						pq.offer(new Point(nx, ny, p.cost + 1, p.cnt + 1));
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		answer = Integer.MAX_VALUE;		
		map = new int[N][M];
		ch = new int[N][M][K + 1];
		pq = new PriorityQueue<>();
		pq.offer(new Point(0, 0, 0, 0));
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solution();
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
}