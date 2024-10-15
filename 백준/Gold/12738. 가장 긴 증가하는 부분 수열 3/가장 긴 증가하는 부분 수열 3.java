import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr;
	static ArrayList<Integer> dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		dp = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			int idx = Collections.binarySearch(dp, n);
			if (idx < 0) idx = -(idx + 1);
			if(idx == dp.size()) dp.add(n);
			else dp.set(idx, n);
		}
		System.out.println(dp.size());
	}
}

