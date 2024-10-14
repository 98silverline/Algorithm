

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr;
	static ArrayList<Integer> dp;
	
	static int lower_bound(int target) {
		int start = 0;
		int end = dp.size();
		while(start < end) {
			int mid = (start + end) / 2;
			if(dp.get(mid) >= target) {
				end = mid;
			} else start = mid + 1;
		}
		return end;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		dp = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(dp.isEmpty()) dp.add(arr[i]);
			else {
				int idx = lower_bound(arr[i]);
				if (idx == dp.size()) dp.add(arr[i]);
				else dp.set(idx, arr[i]);
			}
		}
		System.out.println(dp.size());
	}
}
