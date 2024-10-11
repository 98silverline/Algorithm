

import java.io.*;
import java.util.*;

public class Main {
	static String str1, str2;
	static int[][] dp;
	static Stack<Character> stk;
	static String str;
	
	static void solution() {
		int i = str1.length();
		int j = str2.length();
		
		while(stk.size() < dp[str1.length()][str2.length()]) {
			if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
				stk.push(str1.charAt(i - 1));
				i--;
				j--;
			}
			else if (dp[i][j] == dp[i - 1][j]) i--;
			else j--;
		}
		StringBuilder sb = new StringBuilder();
		while(!stk.isEmpty()) sb.append(stk.pop());
		str = sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		str1 = bf.readLine();
		str2 = bf.readLine();
		dp = new int[Math.max(str1.length(), str2.length()) + 1][Math.max(str1.length(), str2.length()) + 1];
		stk = new Stack<>();
		
		for(int i = 1; i <= str1.length(); i++) {
			char cur1 = str1.charAt(i - 1);
			for(int j = 1; j <= str2.length(); j++) {
				char cur2 = str2.charAt(j - 1);
				if(cur1 == cur2) dp[i][j] = dp[i - 1][j - 1] + 1;
				else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		solution();
		System.out.println(dp[str1.length()][str2.length()]);
		System.out.println(str);
		
	}
}
