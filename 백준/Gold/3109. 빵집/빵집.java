import java.util.*;
import java.io.*;

public class Main {
	static int R, C, answer;
	static int[][] map, ch;
	static int[] dx = {-1, 0, 1};
	static int[] dy = {1, 1, 1};
	static int[] ch2;
	

	static boolean solution(int x, int y) {
		map[x][y] = 1;
		if(y == C - 1) {
			return true;
		} else {
			if(x > 0 && map[x - 1][y + 1] == 0) {
				if(solution(x - 1, y + 1)) return true;
			} 
			if (map[x][y + 1] == 0) {
				if(solution(x, y + 1)) return true;
			} 
			if (x < R - 1 && map[x + 1][y + 1] == 0)
				if(solution(x + 1, y + 1)) return true;
		}
		return false;
	}
	public static void main(String[] args ) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		ch = new int[R][C];
		ch2 = new int[R];
		answer = 0;
		for(int i = 0; i < R; i++) {
			String str = bf.readLine();
			for(int j = 0; j < C; j++) {
				if(str.charAt(j) == 'x') map[i][j] = -1;
			}
		}
		for(int i = 0; i < R; i++) if(solution(i, 0)) answer++;
		System.out.println(answer);
	}
}