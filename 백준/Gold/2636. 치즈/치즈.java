import java.io.*;
import java.util.*;

public class Main {
	static int N, M, answer;
	static int[][] board, ch;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static Queue<Point> que;
	
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static void BFS(int x, int y) {
		que.offer(new Point(x, y));
		while(!que.isEmpty()) {
			Point p = que.poll();
			ch[p.x][p.y] = 1;
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx >= 0 && ny >= 0 && nx < N && ny < M && board[nx][ny] == 0 && ch[nx][ny] == 0) {
					ch[nx][ny] = 1;
					que.offer(new Point(nx, ny));
				}
			}
		}
	}
	static boolean solution(int x, int y) {
		boolean flag = false;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
				if(board[nx][ny] == 0 && ch[nx][ny] == 1)flag = true;	//공기랑 맞닿음
			}
		}
		if(flag) {	//공기가 닿는 면이 있다면
			board[x][y] = 0;	//치즈 녹임
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		que = new LinkedList<>();
		answer = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < M; j++) board[i][j] = Integer.parseInt(st.nextToken());
		}
		
		boolean flag = true;
		int time = 0;
		while(flag) {
			time++;
			ch = new int[N][M];
			int n = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(board[i][j] == 0 && n == 0) {
						n++;
						BFS(i, j);
						break;
					}
				}
			}
			
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(board[i][j] == 1 && solution(i, j)) {
						cnt++;
					}
				}
			}
			
			if(cnt == 0) flag = false;
			else answer = cnt;
		}
		System.out.println(time - 1);
		System.out.println(answer);
	}
}