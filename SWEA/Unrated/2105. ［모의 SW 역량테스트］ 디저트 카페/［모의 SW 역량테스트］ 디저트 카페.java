import java.io.*;
import java.util.*;

public class Solution {
	/*
	 * 
	 * */
	static int T, N, answer;
	static int[] ch;
	static int[][] map;
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	
	static void solution(int len1, int len2, int startX, int startY) {
		int x = startX;
		int y = startY;
		int n = 0; 		//dx, dy 인덱스용
		int L1 = 1;
		int L2 = 1;
		int cnt = 1;
		ch = new int[101];
		ch[map[x][y]] = 1;
		while(n < 4) { 	//한바퀴 다 돌면 끝내기 위함
			if(L1 == len1) {
				L1 = 1;
				n++;
			} else if (L2 == len2) {
				L2 = 1;
				n++;
			}
			int nx = x + dx[n];
			int ny = y + dy[n];
			if(nx == startX && ny == startY) {
				break;
			}
			if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
				if(ch[map[nx][ny]] == 0) {
					ch[map[nx][ny]] = 1;
					cnt++;
					x = nx;
					y = ny;
					if(n == 0 || n == 2) L1++;
					else L2++;
				} else return;
			} else return;
		}
		answer = Math.max(answer, cnt);
	}
	public static void main(String[] args ) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			N = Integer.parseInt(bf.readLine());
			map = new int[N][N];
			answer = -1;
			
			//map 정보 받기
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			//solution 함수 실행
			for(int p = 2; p < N; p++) {
				for(int k = 2; k < N; k++) {
					for(int i = 0; i < N; i++) {
						for(int j = 0; j < N; j++) {
							solution(p, k, i, j);
							if(answer == 2 * (N - 1)) break;
						}
						if(answer == 2 * (N - 1)) break;
					}
					if(answer == 2 * (N - 1)) break;
				}
				if(answer == 2 * (N - 1)) break;
			}
			sb.append(answer + "\n");
		}
		System.out.println(sb.toString());
	}
}