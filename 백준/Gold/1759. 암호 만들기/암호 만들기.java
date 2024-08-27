import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static String str;
	static char[] origin, arr;
	static int[] ch;
	static ArrayList<String> answer;
	
	public static void DFS(int L, char[] cArr, int num) {
		if(L == N) {
			Arrays.sort(cArr);
			String ans = String.valueOf(cArr);
			int cnt = 0;
			for(int i = 0; i < N; i++ ) {
				if(cArr[i] == 'a' || cArr[i] == 'e' || cArr[i] == 'i' || cArr[i] == 'o' || cArr[i] == 'u') cnt++;
			}
			if(cnt > 0 && (N - cnt) >= 2) answer.add(ans); 
		}
		else {
			for(int i = num; i < M; i++) {
				cArr[L] = str.charAt(i);
				DFS(L + 1, cArr, i + 1);
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N];
		answer = new ArrayList<>();
		str = "";
		
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < M; i++) {
			str += st.nextToken();
		}
		
		origin = str.toCharArray();
		Arrays.sort(origin);
		str = String.valueOf(origin);
		DFS(0, arr, 0);
		for(String string : answer) System.out.println(string);
	}
}