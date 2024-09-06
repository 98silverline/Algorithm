import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, K, len, answer;
	static TreeSet<Integer> set;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			len = N / 4;
			set = new TreeSet<>(Collections.reverseOrder());
			String str = bf.readLine();
			for(int i = 0; i < len; i++) {
				for(int j = 0; j < N; j += len) {
					set.add(Integer.parseInt(str.substring(j, j + len), 16));
				}
				str = str.substring(1, N) + str.charAt(0);
			}
			int i = 0;
			for(int n : set) {
				i++;
				if(i == K) {
					sb.append(n + "\n");
				}
			}
		}
		System.out.println(sb.toString());
	}
}