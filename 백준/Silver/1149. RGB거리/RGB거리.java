import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static int[][] cost, board;
	
	static void solution() {
		for(int i = 1; i <= N; i++) {
			board[0][i] = Math.min(board[1][i - 1], board[2][i - 1]) + cost[0][i];
			board[1][i] = Math.min(board[0][i - 1], board[2][i - 1]) + cost[1][i];
			board[2][i] = Math.min(board[0][i - 1], board[1][i - 1]) + cost[2][i];
		}
		answer = Math.min(Math.min(board[0][N], board[1][N]), board[2][N]);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		cost = new int[3][N + 1];
		board = new int[3][N + 1];
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < 3; j++) {
				cost[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		solution();
		System.out.println(answer);
	}
}