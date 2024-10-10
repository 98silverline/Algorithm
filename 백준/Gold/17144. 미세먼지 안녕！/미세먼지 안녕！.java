

import java.util.*;
import java.io.*;

public class Main {
	static int R, C, T, answer;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static Queue<Point> que;
	static ArrayList<Point> AirFresher;
	
	
	static class Point {
		int x, y, dust, ch;

		public Point(int x, int y, int dust, int ch) {
			this.x = x;
			this.y = y;
			this.dust = dust;
			this.ch = ch;
		}
	}
	
	static void diffuse () {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] > 0) que.offer(new Point(i, j, map[i][j], 0));
			}
		}
		while(!que.isEmpty()) {
			Point p = que.poll();
			if(p.ch == 0) {
//				System.out.println(p.x + ", " + p.y);
				int blow = p.dust / 5;
				int cnt = 0;
				for (int i = 0; i < 4; i++) {
					int nx = dx[i] + p.x;
					int ny = dy[i] + p.y;
//					System.out.println("nx: " + nx + ", ny: " + ny );
					if(nx >= 0 && ny >= 0 && nx < R && ny < C && map[nx][ny] != -1) {
//						System.out.println("map[nx][ny]: " + map[nx][ny]);
						cnt++;
						que.offer(new Point(nx, ny, blow, 1));
					}
					map[p.x][p.y] = p.dust - (blow * cnt);
				}
//				System.out.println("퍼지는 먼지: " + cnt);
			} else {
				map[p.x][p.y] += p.dust;
			}
		}
	}
	
	static void blow() {
		//0열 처리
		for(int i = AirFresher.get(0).x - 1; i > 0; i--) map[i][0] = map[i - 1][0];
		for(int i = AirFresher.get(1).x + 1; i < R - 1; i++) map[i][0] = map[i + 1][0];
		
		//0, R - 1행 처리
		for(int i = 0; i < C - 1; i++) {
			map[0][i] = map[0][i + 1];
			map[R - 1][i] = map[R - 1][i + 1];
		}
		
		//C - 1열 처리
		for(int i = 0; i < AirFresher.get(0).x; i++) map[i][C - 1] = map[i + 1][C - 1];
		for(int i = R - 1; i > AirFresher.get(1).x; i--) map[i][C - 1] = map[i - 1][C - 1];
		
		//공청기 행 처리
		for(int i = C - 1; i > 0; i--) {
			map[AirFresher.get(0).x][i] = map[AirFresher.get(0).x][i - 1];
			map[AirFresher.get(1).x][i] = map[AirFresher.get(1).x][i - 1];
		}
		map[AirFresher.get(0).x][1] = 0;
		map[AirFresher.get(1).x][1] = 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(st.nextToken());	//행
		C = Integer.parseInt(st.nextToken());	//열
		T = Integer.parseInt(st.nextToken());	//시간
		map = new int[R][C];
		que = new LinkedList<>();
		AirFresher = new ArrayList<>();
		
		//지도 입력받기
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < C; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				
				//공기청정기 좌표 입력받기
				if(n == -1) AirFresher.add(new Point(i, j, 0, 0));
			}
		}
		for(int i = 0; i < T; i++) {
			diffuse();
			blow();
		}
		for(int[] arr : map) {
			for(int n : arr) answer += n;
		}
		answer += 2;
		System.out.println(answer);
	}
}
