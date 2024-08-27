import java.io.*;
import java.util.*;

public class Main {
	static int N, answer1, answer2;
	static int[][] ch;
	static char[][] board;
	static Queue<Point> que;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static class Point {
		int x;
		int y;
		char c;
		public Point(int x, int y, char c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}
	

	static void BFS1(int x, int y, char c) {	//적록색약X
		que.offer(new Point(x, y, c));
		while(!que.isEmpty()) {
			Point p = que.poll();
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx >= 0 && nx < N && ny >= 0 && ny < N && ch[nx][ny] == 0 && board[nx][ny] == p.c) {
					ch[nx][ny] = 1;
					que.offer(new Point(nx, ny, p.c));
				}
			}
		}
	}
	
	static void BFS2(int x, int y, char c) {
		que.offer(new Point(x, y, c));
		while(!que.isEmpty()) {
			Point p = que.poll();
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx >= 0 && nx < N && ny >= 0 && ny < N && ch[nx][ny] == 0) {
					if(board[nx][ny] == p.c) {
						ch[nx][ny] = 1;
						que.offer(new Point(nx, ny, p.c));
					} else if(p.c == 'G' && board[nx][ny] == 'R') {
						ch[nx][ny] = 1;
						que.offer(new Point(nx, ny, p.c));
					} else if (p.c == 'R' && board[nx][ny] == 'G') {
						ch[nx][ny] = 1;
						que.offer(new Point(nx, ny, p.c));
					}
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		answer1 = 0;
		answer2 = 0;
		board = new char[N][N];
		que = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			String str = bf.readLine();
			board[i] = str.toCharArray();
		}
		
		ch = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(ch[i][j] == 0) {
					ch[i][j] = 1;
					BFS1(i, j, board[i][j]);
					answer1++;
				}
			}
		}
		
		ch = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(ch[i][j] == 0) {
					ch[i][j] = 1;
					BFS2(i, j, board[i][j]);
					answer2++;
				}
			}
		}
		
		System.out.println(answer1 + " " + answer2);
		
	}
}