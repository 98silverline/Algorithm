import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static char[][] board;
	static int R, C, answer = 0;
	public static void DFS(int x, int y, int n, ArrayList<Character> arr) {
		if(n > answer) answer = n;
		for(int i = 0; i < 4; i++) {
			boolean flag = true;
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx >= 0 && nx < R && ny >= 0 && ny < C) {
				for(int j = 0; j < n; j++) {
					if(arr.get(j) == board[nx][ny]) flag = false;
				}
				if(flag) {
					arr.add(board[nx][ny]);
					DFS(nx, ny, n + 1, arr);
				}
			}
		}
		arr.remove(n - 1);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		sc.nextLine();
		ArrayList<Character> arr = new ArrayList<>();
		board = new char[R][C];
		for(int i = 0; i < R; i++) {
			String str = sc.nextLine();
			for(int j = 0; j < str.length(); j++) {
				board[i][j] = str.charAt(j);
			}
		}
		arr.add(board[0][0]);
		DFS(0, 0, 1, arr);
		System.out.println(answer);
	}
}