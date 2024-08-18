import java.io.*;
import java.util.*;

public class Main {
	static int N, M, trueN, answer;
	static int[] parent, arr;
	static int[][] party;
	
	static void Union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x < y) parent[y] = x;
		else parent[x] = y;
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		else return(find(parent[x]));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());	//사람 수
		M = Integer.parseInt(st.nextToken());	//파티 수
		parent = new int[N + 1];
		party = new int[M][N + 1];
		for(int i = 1; i <= N; i++) parent[i] = i; 
		st = new StringTokenizer(bf.readLine());
		trueN = Integer.parseInt(st.nextToken());
		arr = new int[trueN + 1];
		
		for(int i = 1; i <= trueN; i++) {	//진실을 아는 사람들의 부모는 모두 0
			int n = Integer.parseInt(st.nextToken());
			arr[i] = n;
			Union(arr[i - 1], arr[i]);
		}
		
		for(int i = 0; i < M; i++) {	//파티 저장
			st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			party[i][0] = n;	//파티 참여 사람 수
			for(int j = 1; j <= n; j++) {
				party[i][j] = Integer.parseInt(st.nextToken());
			}
			if(n >= 2) {	//파티 2명 이상인 경우
				for(int j = 2; j <= n; j++) {
					Union(party[i][j - 1], party[i][j]);	//싹다 묶어줌
				}
			}
		}
		
		for(int i = 0; i < M; i++) {
			int cnt = 0;
			for(int j = 1; j <= party[i][0]; j++) {
				if(find(party[i][j]) == 0) continue;	//진실을 아는 사람이 있으면 패스
				else cnt++;
			}
			if(cnt == party[i][0]) answer++;
		}
		
		System.out.println(answer);
	}
}