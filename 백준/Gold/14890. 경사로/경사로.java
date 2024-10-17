
import java.io.*;
import java.util.*;

public class Main {
	static int N, L, answer;
	static int[][] map;
	
	static void solution() {
		for(int i = 0; i < N; i++) {
			int high = map[i][0];
			int cnt = 1;
			boolean need = false;
			boolean flag = true;
			for(int j = 1; j < N; j++) {
				int now = map[i][j];
				//활주로 지어야돼서 땅 길이 재고 있었는데 길이 나온 경우
				if(need && cnt == L) {
					cnt = 0;
					need = false;
				}
				if(now != high) {
					//그냥 평지 지나가던 경우
					if(!need) {
						//경사로 길이가 1을 초과해야 하는 경우 break
						if(Math.abs(now - high) > 1) {
							flag = false;
							break;
						}
						//지금 온 땅이 이전 땅보다 큰 경우(이전 땅에다가 경사로 지어야 됨)
						if(now > high) {
							//지나쳐온 이전 땅이 경사로 지을 길이가 되는가?
							if(cnt >= L) {
								high = now;
								cnt = 1;
								continue;
							} else {
								flag = false;
								break;
							}
						//지금 온 땅이 이전 땅보다 낮은 경우(지금부터 경사로 길이 재봐야 됨)
						} else {
							if(need) {
								flag = false;
								break;
							}
							if(N - j < L) {
								flag = false;
								break;
							} else {
								need = true;
								high = now;
								cnt = 1;
								continue;
							}
						}
					} else {
						flag = false;
						break;
					}
				} else cnt++;
			}
			if(flag) {
				answer++;
			}
			high = map[0][i];
			cnt = 1;
			need = false;
			flag = true;
			for(int j = 1; j < N; j++) {
				int now = map[j][i];
				//활주로 지어야돼서 땅 길이 재고 있었는데 길이 나온 경우
				if(need && cnt == L) {
					cnt = 0;
					need = false;
				}
				if(now != high) {
					//그냥 평지 지나가던 경우
					if(!need) {
						//경사로 길이가 1을 초과해야 하는 경우 break
						if(Math.abs(now - high) > 1) {
							flag = false;
							break;
						}
						//지금 온 땅이 이전 땅보다 큰 경우(이전 땅에다가 경사로 지어야 됨)
						if(now > high) {
							//지나쳐온 이전 땅이 경사로 지을 길이가 되는가?
							if(cnt >= L) {
								high = now;
								cnt = 1;
								continue;
							} else {
								flag = false;
								break;
							}
						//지금 온 땅이 이전 땅보다 낮은 경우(지금부터 경사로 길이 재봐야 됨)
						} else {
							if(need) {
								flag = false;
								break;
							}
							if(N - j < L) {
								flag = false;
								break;
							} else {
								need = true;
								high = now;
								cnt = 1;
								continue;
							}
						}
					} else {
						flag = false;
						break;
					}
				} else cnt++;
			}
			if(flag) answer++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		answer = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine().trim());
			for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		solution();
		System.out.println(answer);
	}
}
