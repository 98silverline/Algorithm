import java.io.*;
import java.util.*;

public class Solution {
	/* 메모리: 191484KB
	 * 시간: 1408ms
	 * 3차원 배열 ch를 이용해 체크하며 BFS를 진행했습니다.
	 * ch[x][y][k]의 의미는 x행 y열을 말처럼 k번 이동했다는 의미입니다.
	 * */
	static int T, N, K, answer, high;
	static int[][] map, ch;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static class Point{
		int x, y, ch, cnt, cur;

		public Point(int x, int y, int ch, int cnt, int cur) {
			this.x = x;
			this.y = y;
			this.ch = ch;
			this.cnt = cnt;
			this.cur = cur;
		}
	}
	static void DFS(int L, Point p) {
		answer = Math.max(answer, L);
		for(int i = 0; i < 4; i++) {
			int nx = p.x + dx[i];
			int ny = p.y + dy[i];
			if(nx >= 0 && ny >= 0 && nx < N && ny < N && ch[nx][ny] == 0) {
				if(map[nx][ny] < p.cur) {
					ch[nx][ny] = 1;
					DFS(L + 1, new Point(nx, ny, p.ch, p.cnt + 1, map[nx][ny]));
					ch[nx][ny] = 0;
				} else if (p.ch == 0 && (map[nx][ny] - p.cur + 1) <= K){
					ch[nx][ny] = 1;
					DFS(L + 1, new Point(nx, ny, 1, p.cnt + 1, p.cur - 1));
					ch[nx][ny] = 0;
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			high = 0;
			answer = 0;
			ch = new int[N][N];
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					map[i][j] = n;
					high = Math.max(high, n);
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == high) {
						ch[i][j] = 1;
						DFS(1, new Point(i, j, 0, 1, high));
						ch[i][j] = 0;
					}
				}
			}
			sb.append(answer + " \n");
		}
		System.out.println(sb.toString());
	}
}