
import java.util.*;
import java.io.*;

public class Main {
	static int N, lisLength;
	static int[] arr, lisIndex, lis;
	static ArrayList<Integer> dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		lisLength = 0;
		dp = new ArrayList<>();
		arr = new int[N];
		lisIndex = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			int n = arr[i];
			int idx = Collections.binarySearch(dp, n);
			if(idx < 0) idx = -(idx + 1);
			if(idx == dp.size()) dp.add(n);
			else dp.set(idx, n);
			lisIndex[i] = idx;
			lisLength = Math.max(lisLength, idx + 1);
		}
		
		lis = new int[lisLength];
		for(int i = N - 1; i >= 0; i--) {
			if(lisIndex[i] == lisLength - 1) {
				lis[lisLength - 1] = arr[i];
				lisLength--;
			}
		}
		System.out.println(dp.size());
		for(int n : lis) System.out.print(n + " ");
	}
}

