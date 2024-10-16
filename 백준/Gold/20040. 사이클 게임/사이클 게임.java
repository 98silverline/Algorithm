

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, answer;
	static int[] parent;
	
	static boolean Union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return false;
		else if (x < y) parent[y] = x;
		else parent[x] = y;
		
		return true;
	}
	
	static int find(int x) {
		if(parent[x] != x) return find(parent[x]);
		else return x;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = 0;
		parent = new int[N];
		for(int i = 0; i < N; i++) parent[i] = i;
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(bf.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			if(!Union(node1, node2)) {
				answer = i;
				break;
			}
		}
		System.out.println(answer);
	}
}
