
import java.io.*;
import java.util.*;

public class Main {
	static int R, C, M, answer;
	static int[][] map;
	static PriorityQueue<Shark> pq;
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, 1, -1};
	
	static class Shark implements Comparable<Shark>{
		int x, y, speed, dist, size, time;

		public Shark(int x, int y, int speed, int dist, int size, int time) {
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.dist = dist;
			this.size = size;
			this.time = time;
		}

		@Override
		public int compareTo(Shark o) {
			//시간순 오름차순으로 1순위 정렬
			if(this.time == o.time) {
				//같은 시간대고 동일한 행일 경우 사이즈가 작은 것부터 출력
				if(this.x == o.x) return this.size - o.size;
				//가장 높은 행 먼저 출력
				else return this.x - o.x;
			} else return this.time - o.time;
		}
	}
	
	static void solution() {
		for(int i = 0; i < C; i++) {
			int[][] newMap = new int[R][C];
			int cnt = pq.size();
			boolean flag = true;
			for(int j = 0; j < cnt; j++) {
				Shark s = pq.poll();
				//같은 칸에 상어 2마리 이상이면 작은 상어 없어짐
				if(map[s.x][s.y] > 1) {
					map[s.x][s.y]--;
					continue;
				}
				
				//낚시꾼이 있는 열에서 가장 높은 곳(땅과 가까운)에 있음.
				if(flag && s.y == i) {
					flag = false;
					map[s.x][s.y]--;
					answer += s.size;
					continue;
				}
				
				//상어 이동
				map[s.x][s.y]--;
				int loop = 0;
				if(s.dist == 1 || s.dist == 2) loop = (s.x + s.x) + ((R - s.x - 1) + (R - s.x - 1));
				else if(s.dist == 3 || s.dist == 4) loop = (s.y + s.y) + ((C - s.y - 1) + (C - s.y - 1));
				for(int k = 0; k < s.speed % loop; k++) {
					int nx = dx[s.dist] + s.x;
					int ny = dy[s.dist] + s.y;
					//벽과 아직 안 부딪힘
					if(nx >= 0 && ny >= 0 && nx < R && ny < C) {
						s.x = nx;
						s.y = ny;
					} 
					//벽과 부딪힘
					else {
						k--;
						if(s.dist == 1) s.dist = 2;
						else if (s.dist == 2) s.dist = 1;
						else if (s.dist == 3) s.dist = 4;
						else if (s.dist == 4) s.dist = 3;
					}
				}
				newMap[s.x][s.y]++;
				pq.offer(new Shark(s.x, s.y, s.speed, s.dist, s.size, s.time + 1));
			}
			for(int j = 0; j < R; j++) map[j] = newMap[j].clone();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		answer = 0;
		pq = new PriorityQueue<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c]++;
			pq.add(new Shark(r, c, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0));
		}
		solution();
		System.out.println(answer);
	}
}
