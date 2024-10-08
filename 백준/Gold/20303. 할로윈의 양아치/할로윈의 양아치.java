
import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K;
	static int[] child, parents, groupSize, groupCandy;
	
	static boolean Union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) return false;
		else if (x > y) parents[x] = y;
		else parents[y] = x;
		return true;
		
	}
	
	static int find(int x) {
        if (parents[x] != x) {
            parents[x] = find(parents[x]);  // 경로 압축
        }
        return parents[x];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken()); 	//아이들의 수
		M = Integer.parseInt(st.nextToken()); 	//관계 수
		K = Integer.parseInt(st.nextToken());	//공명하기 위한 아이들의 수
		child = new int[N + 1];					//아이들이 가진 사탕 수
		parents = new int[N + 1];				//부모
		groupSize = new int[N + 1];
		groupCandy = new int[N + 1];
		st = new StringTokenizer(bf.readLine());
		for(int i = 1; i <= N; i++) child[i] = Integer.parseInt(st.nextToken());
		for(int i = 1; i <= N; i++) parents[i] = i;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			Union(c1, c2);
		}
		
		for(int i = 1; i <= N; i++) {
			int parent = find(i);
			groupCandy[parent] += child[i];
			groupSize[parent]++;
		}
		
		int[] dp = new int[K];
		for(int i = 0; i <= N; i++) {
			if(groupSize[i] != 0) {
				for(int j = K - 1; j >= groupSize[i]; j--) {
					dp[j] = Math.max(dp[j], dp[j - groupSize[i]] + groupCandy[i]);
				}
			}
		}
		System.out.println(dp[K - 1]);
 	}	
}
