import java.util.*;
import java.io.*;

public class Solution {

	static long N;
	static int T;
	static PriorityQueue<NumCount> que;
	static class NumCount {
		long num;
		int cnt;
		public NumCount(long num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
	
	static int sol(long n) {
		int cnt = 0;
		while(n != 2) {
			long sqrt = (long) Math.sqrt(n);
			if(sqrt * sqrt == n) {
				n = (long) Math.sqrt(n);
				cnt++;
			} else {
				cnt += Math.pow(sqrt + 1, 2) - n;
				n = (long) Math.pow(sqrt + 1, 2);
			}
		}
		return cnt;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Long.parseLong(bf.readLine());
			que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cnt, o2.cnt));
			System.out.println("#" + test_case + " " + sol(N));;
		}
	}
}