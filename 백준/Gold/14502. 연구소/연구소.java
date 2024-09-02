import java.io.*;
import java.util.*;

public class Main {
	static int[][] board;
	static int N, M, answer = 0;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static class Point{
		int x;
		int y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void DFS(int L) {
		if(L == 3) {
			BFS();
		}
		else {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(board[i][j] == 0) {
						board[i][j] = 1;
						DFS(L + 1);
						board[i][j] = 0;
					}
				}
			}
		}
	}
	
	public static void BFS() {
		int[][] copyboard = new int[N][M];
		int safe = 0;
		Queue<Point> Q = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				copyboard[i][j] = board[i][j];
				if(board[i][j] == 2) Q.add(new Point(i, j));
			}
		}
		
		while(!Q.isEmpty()) {
			Point cur = Q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = dx[i] + cur.x;
				int ny = dy[i] + cur.y;
				if(nx >= 0 && ny >= 0 && nx < N && ny < M && copyboard[nx][ny] == 0) {
					copyboard[nx][ny] = 2;
					Q.offer(new Point(nx, ny));
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyboard[i][j] == 0) safe++;
			}
		}
		
		if(answer < safe) answer = safe;
		
		
	}
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		board = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		
		DFS(0);
		System.out.println(answer);
	}
}