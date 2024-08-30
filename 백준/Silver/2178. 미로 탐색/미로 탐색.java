import java.io.*;
import java.util.*;

public class Main {
	public static int N, M;
	public static int[][] map, ch;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	public static Queue<Point> que;
	
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static void BFS() {
		while(!que.isEmpty()) {
			Point p = que.poll();
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 1 && ch[nx][ny] == 0) {
					ch[nx][ny] = ch[p.x][p.y] + 1;
					que.offer(new Point(nx, ny));
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		ch = new int[N][M];
		que = new LinkedList<>();
		que.offer(new Point(0, 0));
		ch[0][0] = 1;
		for(int i = 0; i < N; i++) {
			String str = bf.readLine();
			for(int j = 0; j < M; j++) map[i][j] = str.charAt(j) - '0';
		}
		BFS();
		System.out.println(ch[N - 1][M - 1]);
	}
}