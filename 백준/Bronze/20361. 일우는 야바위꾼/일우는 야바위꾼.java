import java.util.*;
import java.io.*;
public class Main {
	static int T, N, X, K, answer;
	static ArrayList<Point> arr;
	
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new ArrayList<>();
		answer = X;
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(bf.readLine());
			arr.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		for(Point p : arr) {
			if(p.x != answer && p.y != answer) continue;
			else if (p.x == answer) {
				answer = p.y;
			} else if (p.y == answer) answer = p.x;
		}
		System.out.println(answer);
	}
}