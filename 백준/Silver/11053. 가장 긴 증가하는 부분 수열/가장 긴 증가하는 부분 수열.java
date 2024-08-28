import java.io.*;
import java.util.*;

public class Main {
	static int N, answer, idx;
	static int[] arr, ansArr;
	
	static void solution() {
		int cnt = 1;
		while(cnt <= N) {
			int n = 0;
			for(int i = cnt; i >= 0; i--) {
				if(arr[cnt] > arr[i]) n = Math.max(n, ansArr[i]);
			}
			ansArr[cnt] = n + 1;
			answer = Math.max(answer, ansArr[cnt]);
			cnt++;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N + 1];
		ansArr = new int[N + 1];
		answer = 0;
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
		solution();
		System.out.println(answer);
	}
}