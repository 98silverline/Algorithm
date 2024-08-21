import java.util.*;
import java.io.*;

public class Main {
	static int N, answer;
	static int[] col, ch1, ch2;


	static void DFS(int y) {
		if(y == N) {
			answer++;
			return;
		}
		for(int x = 0; x < N; x++) {
			if(col[x] == 0 && ch1[x + y] == 0 && ch2[x - y + N - 1] == 0) {
				col[x] = ch1[x + y] = ch2[x - y + N - 1] = 1;
				DFS(y + 1);
				col[x] = ch1[x + y] = ch2[x - y + N - 1] = 0;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		col = new int[N];
		ch1 = new int[2 * N - 1];
		ch2 = new int[2 * N - 1];
		answer = 0;
		DFS(0);
		System.out.println(answer);
	}
}