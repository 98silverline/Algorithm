import java.io.*;
import java.util.*;

public class Main {
	static int N, M, answer, cctvNum, max;
	static int[][] map, simulMap;
	static int[] ch;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static ArrayList<CCTV> arr;
	
	static class CCTV {
		int x;
		int y;
		int type;
		int cases;
		public CCTV(int x, int y, int type, int cases) {
			this.x = x;
			this.y = y;
			this.type = type;
			this.cases = cases;
		}
	}
	
	static void simulation() {
		int cnt = 0;
		for(int i = 0; i < cctvNum; i++) {
			CCTV cctv = arr.get(i);
			if(cctv.type == 1) cnt += case1(cctv, i);
			else if (cctv.type == 2) cnt += case2(cctv, i);
			else if (cctv.type == 3) cnt += case3(cctv, i);
			else if (cctv.type == 4) cnt += case4(cctv, i);
			else if (cctv.type == 5) cnt += case5(cctv);
		}
		answer = Math.min(answer, max - cnt);
	}
	
	static void solution(int L) {
		if(L == cctvNum) {
			//simulMap은 감시영역 겹침 중복 방지용
			simulMap = new int[N][M];
			simulation();
			return;
		} else {
			CCTV cctv = arr.get(L);
			for(int i = 0; i < cctv.cases; i++) {
				ch[L] = i;
				solution(L + 1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		arr = new ArrayList<>();
		cctvNum = 0;
		max = N * M;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if(n >= 1 && n <= 5) {
					max--;
					cctvNum++;
					int a = 0;
					if(n == 1 || n == 3 || n == 4) a = 4;
					else if (n == 2) a = 2;
					else a = 1;
					arr.add(new CCTV(i, j, n, a));
				} else if (n == 6) max--;
			}
		}
		ch = new int[cctvNum];
		answer = max;
		solution(0);
		System.out.println(answer);
	}
	
	static int case1(CCTV cctv, int n) {
		int cnt = 0;
		int dist = 1;
		n = ch[n];
		while(true) {
			int nx = cctv.x + (dx[n] * dist);
			int ny = cctv.y + (dy[n] * dist);
			if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
				if(map[nx][ny] == 6) break;	//벽을 만나면 그대로 종료
				if(simulMap[nx][ny] == 0 && map[nx][ny] == 0) { //아무것도 없으면서 아직 체크되지 않은 경우
					simulMap[nx][ny] = 1;
					cnt++;
					dist++;
				} else {
					dist++;
					continue;
				}
			} else break;	//더 이상 이 방향으로 갈 수 없으면 종료
		}
		return cnt;
	}
	
	static int case2(CCTV cctv, int n) {
		int[] idx = new int[4];	//1이면 해당 방향으로 체크할 것
		//n이 0이면 상하, 1이면 좌우
		if(ch[n] == 0) idx[0] = idx[2] = 1;
		else idx[1] = idx[3] = 1;
		int cnt = 0;
		int dist = 1;
		int i = 0;
		while(i < 4) {
			if(idx[i] == 0) {
				i++;
				continue; //해당 방향 체크 안하는거면 그냥 패스
			}
			int nx = cctv.x + (dx[i] * dist);
			int ny = cctv.y + (dy[i] * dist);
			if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
				if(map[nx][ny] == 6) {	//벽이라면 해당 방향으로 더 이상 못감. 다음 방향으로 진행
					i++;
					dist = 1;
					continue;
				}
				if(simulMap[nx][ny] == 0 && map[nx][ny] == 0) { //아무것도 없으면서 아직 체크되지 않은 경우
					simulMap[nx][ny] = 1;
					cnt++;
					dist++;
				} else {
					dist++;
					continue;
				}
			} else {	//map 바깥으로 벗어나 해당 방향으로 더 이상 못감
				i++;
				dist = 1;
			}
		}
		return cnt;
	}
	
	static int case3(CCTV cctv, int n) {
		int[] idx = new int[4];	//1이면 해당 방향으로 체크할 것
		//n이 0이면 상하, 1이면 좌우
		if(ch[n] == 0) idx[0] = idx[3] = 1;
		else if (ch[n] == 1) idx[2] = idx[3] = 1;
		else if (ch[n] == 2) idx[1] = idx[2] = 1;
		else if (ch[n] == 3) idx[0] = idx[1] = 1;
		int cnt = 0;
		int dist = 1;
		int i = 0;
		while(i < 4) {
			if(idx[i] == 0) {
				i++;
				continue;//해당 방향 체크 안하는거면 그냥 패스
			}
			int nx = cctv.x + (dx[i] * dist);
			int ny = cctv.y + (dy[i] * dist);
			if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
				if(map[nx][ny] == 6) {	//벽이라면 해당 방향으로 더 이상 못감. 다음 방향으로 진행
					i++;
					dist = 1;
					continue;
				}
				if(simulMap[nx][ny] == 0 && map[nx][ny] == 0) { //아무것도 없으면서 아직 체크되지 않은 경우
					simulMap[nx][ny] = 1;
					cnt++;
					dist++;
				} else {
					dist++;
					continue;
				}
			} else {	//map 바깥으로 벗어나 해당 방향으로 더 이상 못감
				i++;
				dist = 1;
			}
		}
		return cnt;
	}
	
	static int case4(CCTV cctv, int n) {
		int[] idx = {1, 1, 1, 1};	//1이면 해당 방향으로 체크할 것
		//n이 0이면 상하, 1이면 좌우
		if(ch[n] == 0) idx[0] = 0;
		else if (ch[n] == 1) idx[1] = 0;
		else if (ch[n] == 2) idx[2] = 0;
		else if (ch[n] == 3) idx[3] = 0;
		int cnt = 0;
		int dist = 1;
		int i = 0;
		while(i < 4) {
			if(idx[i] == 0)  {
				i++;
				continue; //해당 방향 체크 안하는거면 그냥 패스
			}
			int nx = cctv.x + (dx[i] * dist);
			int ny = cctv.y + (dy[i] * dist);
			if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
				if(map[nx][ny] == 6) {	//벽이라면 해당 방향으로 더 이상 못감. 다음 방향으로 진행
					i++;
					dist = 1;
					continue;
				}
				if(simulMap[nx][ny] == 0 && map[nx][ny] == 0) { //아무것도 없으면서 아직 체크되지 않은 경우
					simulMap[nx][ny] = 1;
					cnt++;
					dist++;
				} else {
					dist++;
					continue;
				}
			} else {	//map 바깥으로 벗어나 해당 방향으로 더 이상 못감
				i++;
				dist = 1;
			}
		}
		return cnt;
	}
	
	static int case5(CCTV cctv) {
		int cnt = 0;
		int dist = 1;
		int i = 0;
		while(i < 4) {
			int nx = cctv.x + (dx[i] * dist);
			int ny = cctv.y + (dy[i] * dist);
			if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
				if(map[nx][ny] == 6) {	//벽이라면 해당 방향으로 더 이상 못감. 다음 방향으로 진행
					i++;
					dist = 1;
					continue;
				}
				if(simulMap[nx][ny] == 0 && map[nx][ny] == 0) { //아무것도 없으면서 아직 체크되지 않은 경우
					simulMap[nx][ny] = 1;
					cnt++;
					dist++;
				} else {
					dist++;
					continue;
				}
			} else {	//map 바깥으로 벗어나 해당 방향으로 더 이상 못감
				i++;
				dist = 1;
			}
		}
		return cnt;
	}
}