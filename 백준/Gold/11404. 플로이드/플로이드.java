import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] graph;
	
	public static void Solution() {
		for(int k = 1; k <= N; k++) {		//거쳐갈 간선
			for(int i = 1; i <= N; i++) {	//시작점
				for(int j = 1; j <= N; j++) { //목표지점
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(graph[i][j] != 100_000_000) System.out.print(graph[i][j] + " ");
				else System.out.print(0 + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		M = Integer.parseInt(bf.readLine());
		graph = new int[N + 1][N + 1];
		for(int [] arr : graph) Arrays.fill(arr, 100_000_000);
		for(int i = 1; i <= N; i++) graph[i][i] = 0;
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(graph[a][b] > c) graph[a][b] = c;
		}
		Solution();
	}
}