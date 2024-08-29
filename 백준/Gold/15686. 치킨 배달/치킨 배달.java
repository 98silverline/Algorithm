import java.io.*;
import java.util.*;

public class Main {

	static int N, M, answer = Integer.MAX_VALUE;
	static int[] ch;
	static int[][] map;
	static ArrayList<Point> hm, BBQ;
	public static class Point{
		int x;
		int y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void DFS(int L, int s) {
		if (L == M) {
			int sum = 0;
			for(Point h : hm) {
				int dis = Integer.MAX_VALUE;
				for(int i : ch) {
					dis = Math.min(dis, Math.abs(h.x - BBQ.get(i).x) + Math.abs(h.y - BBQ.get(i).y));
				}
				sum += dis;
			}
			answer = Math.min(answer, sum);
		}
		else {
			for(int i = s; i < BBQ.size(); i++) {
				ch[L] = i;
				DFS(L + 1, i + 1);
			}
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		hm = new ArrayList<>();
		BBQ = new ArrayList<>();
		ch = new int[M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				map[i][j] = a;
				if(a == 1) hm.add(new Point(i, j));
				if(a == 2) BBQ.add(new Point(i, j));
			}
		}
		
		DFS(0, 0);
		System.out.println(answer);
	}
}