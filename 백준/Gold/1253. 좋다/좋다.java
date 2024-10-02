

import java.util.*;
import java.io.*;

public class Main {
	static int N, answer;
	static int[] arr;
	static Map<Integer, Integer> map;

	static void solution() {
		int idx = 0;
		while(idx < N) {
//			System.out.println("좋은 수일까?: " + arr[idx]);
			int start = 0;
			int end = N - 1;
			while(arr[start] <= arr[idx] / 2) {
				if(start == idx) {
					start++;
					continue;
				}
				int find = (arr[idx] - arr[start]);
				boolean flag = false;
				int lt = start + 1;
				int rt = end;
				while(lt <= rt) {
//					System.out.println(lt + ", " + rt);
					int mid = rt - ((rt - lt) / 2);
//					System.out.println(find);
//					System.out.println(arr[mid]);
					if(arr[mid] == find) {
						flag = true;
//						System.out.println("좋은 수는 " + arr[idx]);
						if(mid != idx) answer++;
						else {
							int n = map.get(arr[mid]) - 1;
							if(arr[mid] == arr[start]) n = n - 1;
							if(n > 0) answer++;
							else flag = false;
						}
//						System.out.println(idx + ", " + "start: " + start + ", mid: " + mid);
						break;
					} else if (arr[mid] > find) rt = mid - 1;
					else lt = mid + 1;
				}
				if(flag) break;
				else {
					start++;
				}
			}
			idx++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		answer = 0;
		map = new HashMap<>();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}
		Arrays.sort(arr);
		solution();
		System.out.println(answer);
	}
}
