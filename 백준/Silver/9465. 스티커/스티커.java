import java.io.*;
import java.util.*;

public class Main {
	static int T, N;
	static int[][] arr, answer;

	static void solution() {
		answer[0][1] = arr[0][1];
		answer[1][1] = arr[1][1];
		int idx = 2;
		while(idx <= N) {
			answer[0][idx] = Math.max(answer[1][idx - 1], answer[1][idx - 2]) + arr[0][idx];
			answer[1][idx] = Math.max(answer[0][idx - 1], answer[0][idx - 2]) + arr[1][idx];
			idx++;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		for(int test_case = 0; test_case < T; test_case++) {
			N = Integer.parseInt(bf.readLine());
			arr = new int[2][N + 1];
			answer = new int[2][N + 1];
			for(int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j = 1; j <= N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
			}
			
			solution();
			System.out.println(Math.max(answer[0][N], answer[1][N]));
		}
	}
}