import java.io.*;
import java.util.*;

public class Main {
	static int N, answer, ansPt1, ansPt2;
	static int[] arr;
	
	
	static void solution() {
		int pt1 = 0;
		int pt2 = N - 1;
		while(pt1 < pt2) {
			int ans = arr[pt1] + arr[pt2];
			if(ans == 0) {
				ansPt1 = pt1;
				ansPt2 = pt2;
				return;
			}
			if(Math.abs(ans) <= answer) {
				answer = Math.abs(ans);
				ansPt1 = pt1;
				ansPt2 = pt2;
			}
			if(ans < 0) pt1++;
			else pt2--;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		answer = Integer.MAX_VALUE;
		ansPt1 = ansPt2 = 0;
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		solution();
		System.out.println(arr[ansPt1] + " " + arr[ansPt2]);
	}
	
}