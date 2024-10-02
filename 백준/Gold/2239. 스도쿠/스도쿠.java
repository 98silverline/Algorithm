
import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] map, row, col, ch, zone, answer;
	static ArrayList<Point> arr;
	static boolean flag;
	
	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void setZone() {
		int n = 0;
		int r = 0;
		int c = 0;
		while(n < 9) {
			for(int i = r; i < r + 3; i++) {
				for(int j = c; j < c + 3; j++) {
					ch[i][j] = n;
				}
			}
			c += 3;
			if(c == 9) {
				c = 0;
				r += 3;
			}
			n++;
		}
	}
	
	static void DFS(int L) {
		if(L == arr.size()) {
			flag = false;
			answer = map;
			return;
		} else {
			Point p = arr.get(L);
			for(int i = 1; i <= 9; i++) {
				if(canThis(p.x, p.y, i)) {
					map[p.x][p.y] = i;
					zone[ch[p.x][p.y]][i] = 1;
					row[p.x][i] = 1;
					col[p.y][i] = 1;
					DFS(L + 1);
					if(flag) {
						map[p.x][p.y] = 0;
						zone[ch[p.x][p.y]][i] = 0;
						row[p.x][i] = 0;
						col[p.y][i] = 0;
					}
				}
			}
		}
	}
	
	static boolean canThis(int x, int y, int n) {
		if(zone[ch[x][y]][n] != 0 || row[x][n] == 1 || col[y][n] == 1) return false; 
		for(int i = 0; i < 9; i++) {
			if(map[x][i] == n || map[i][y] == n) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		zone = new int[9][10];
		row = new int[9][10];
		col = new int[9][10];
		ch = new int[9][9];
		answer = new int[9][9];
		arr = new ArrayList<>();
		flag = true;
		setZone();
		for(int i = 0; i < 9; i++) {
			String str = bf.readLine();
			for(int j = 0; j < 9; j++) {
				int n = str.charAt(j) - '0';
				map[i][j] = n;
				if(n == 0) arr.add(new Point(i , j));
				else {
					row[i][n] = 1;
					col[j][n] = 1;
					zone[ch[i][j]][n] = 1;
				}
			}
		}
		for(int i = 1; i <= 9; i++) {
			if(canThis(arr.get(0).x, arr.get(0).y, i)) {
				map[arr.get(0).x][arr.get(0).y] = i;
				zone[ch[arr.get(0).x][arr.get(0).y]][i] = 1;
				row[arr.get(0).x][i] = 1;
				col[arr.get(0).y][i] = 1;
				DFS(1);
				if (flag) {
					map[arr.get(0).x][arr.get(0).y] = 0;
					zone[ch[arr.get(0).x][arr.get(0).y]][i] = 0;
					row[arr.get(0).x][i] = 0;
					col[arr.get(0).y][i] = 0;
				}
			}
		}
		for(int[] ar : answer) {
			for(int a : ar) System.out.print(a);
			System.out.println();
		}
	}
}
