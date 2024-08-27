import java.io.*;
import java.util.*;

public class Main {

	
	static long N, M, K;
	static long pow(long a, long b) {
		if(b == 0) return 1 % K;
		
		long ans = pow(a, b / 2) % K;
		
		if(b % 2 == 0) { //짝수인 경우
			return ans * ans % K;
		} else {	//홀수인 경우
			return (ans * ans % K) * (a % K);
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		long answer = pow(N, M);
		System.out.println(answer % K);
	} 
}