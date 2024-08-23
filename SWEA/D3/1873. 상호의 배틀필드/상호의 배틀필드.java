import java.io.*;
import java.util.*;

class Solution {
	static int T, H, W, N;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static State Tank;
	static char[] cmd;
	static char[][] Map;
	
	static class State {
		int x;
		int y;
		int direc;
		public State(int x, int y, int direc) {
			this.x = x;
			this.y = y;
			this.direc = direc;
		}
	}
	
	static void go(int i) {
		int n = i;
		Tank.direc = n;
		int nx = Tank.x + dx[n];
		int ny = Tank.y + dy[n];
		if(nx >= 0 && nx < H && ny >= 0 && ny < W && Map[nx][ny] == '.') Tank = new State(nx, ny, n);
	}
	
	static void shoot() {
//		System.out.println("x: " + Tank.x + ", y: " + Tank.y);
		int x = Tank.x;
		int y = Tank.y;
		while(true) {
			int nx = x + dx[Tank.direc];
			int ny = y + dy[Tank.direc];
//			System.out.println("nx: " + nx + ", ny: " + ny);
			if(nx >= 0 && nx < H && ny >= 0 && ny < W) {
				if(Map[nx][ny] == '.' || Map[nx][ny] == '-') {
//					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
					x = nx;
					y = ny;
					continue;
				} else if (Map[nx][ny] == '*') {
					Map[nx][ny] = '.';
					return;
				} else if (Map[nx][ny] == '#') return;
			} else return;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			Map = new char[H][W];
			for(int i = 0; i < H; i++) {
				String str = bf.readLine();
				for(int j = 0; j < W; j++) {
					Map[i][j] = str.charAt(j);
					if(str.charAt(j) == '^') {
						Tank = new State(i, j, 0);
						Map[i][j] = '.';
					}
					else if (str.charAt(j) == '<') {
						Tank = new State(i, j, 1);
						Map[i][j] = '.';
					}
					else if (str.charAt(j) == 'v') {
						Tank = new State(i, j, 2);
						Map[i][j] = '.';
					}
					else if (str.charAt(j) == '>') {
						Tank = new State(i, j, 3);
						Map[i][j] = '.';
					}
				}
			}
			N = Integer.parseInt(bf.readLine());
			String str = bf.readLine();
			cmd = str.toCharArray();
			for(char c : cmd) {
				if(c == 'U') go(0);
				else if (c == 'L') go(1);
				else if (c == 'D') go(2);
				else if (c == 'R') go(3);
				else if (c == 'S') shoot();
			}
			if(Tank.direc == 0) Map[Tank.x][Tank.y] = '^';
			else if(Tank.direc == 1) Map[Tank.x][Tank.y] = '<';
			else if(Tank.direc == 2) Map[Tank.x][Tank.y] = 'v';
			else if(Tank.direc == 3) Map[Tank.x][Tank.y] = '>';
			System.out.print("#" + test_case + " ");
			for(char[] arr : Map) {
				for(char c : arr) System.out.print(c);
				System.out.println();
			}
		}
	}

}