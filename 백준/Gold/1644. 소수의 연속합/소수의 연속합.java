import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static int[] arr;
	static ArrayList<Integer> list;
	
	static void solution() {
		int rt = 0;
		int lt = 0;
		int cur = list.get(0);
		while(rt < list.size()) {
			if(cur == N) {
				answer++;
				rt++;
				if(rt < list.size()) cur += list.get(rt);
			} else if (cur < N) {
				rt++;
				if(rt < list.size()) cur += list.get(rt);
			} else {
				cur -= list.get(lt);
				lt++;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N + 1];
		list = new ArrayList<>();
		answer = 0;
		if(N == 1 || N == 2) {
			if(N == 1) System.out.println(0);
			if(N == 2) System.out.println(1);
		} else {
			for(int i = 2; i <= N / 2; i++) {
				if(arr[i] == 1) continue;
				for(int j = i + i; j <= N; j += i) {
					arr[j] = 1;
				}
			}
			
			for(int i = 2; i <= N; i++) {
				if(arr[i] == 0) list.add(i);
			}
			
			solution();
			System.out.println(answer);
		}
	}
}