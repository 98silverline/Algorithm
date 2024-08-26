import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int C;
	static char[][] roadMap;
	static int[] pos;
	static char answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		roadMap = new char[R][C];
		pos = new int[2];
		int[][] dirs = {{-1,0},{0,-1},{0,1},{1,0}};
		int cnt = 0;

		for(int i = 0; i < R; i++) {
			roadMap[i] = br.readLine().toCharArray();
		}
		
		Tag : for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				cnt = 0;
				
				if(roadMap[i][j] != '.') {
					continue;
				}

				for(int[] dir : dirs) {
					int dx = i + dir[0];
					int dy = j + dir[1];

					if(0 <= dx && dx < R && 0 <= dy && dy < C) {
						if(roadMap[dx][dy] != '.') {
							cnt++;
						}
					}
				}

				if(cnt > 1) {
					pos[0] = i;
					pos[1] = j;
					if(checkUpDown(i, j) && checkRightLeft(i, j)) {
						answer = '+';
						break Tag;
					} else if(checkUpDown(i, j)) {
						answer = '|';
						break Tag;
					} else if(checkRightLeft(i, j)) {
						answer = '-';
						break Tag;
					} else if(checkDownRight(i, j)) {
						answer = '1';
						break Tag;
					} else if(checkUpRight(i, j)) {
						answer = '2';
						break Tag;
					} else if(checkUpLeft(i, j)) {
						answer = '3';
						break Tag;
					} else if(checkDownLeft(i, j)) {
						answer = '4';
						break Tag;
					}
				}
			}
		}

		System.out.println((pos[0] + 1) + " " + (pos[1] + 1) + " " + answer);

	}

	public static void checkRoad(int x, int y) {
		
	}

	public static boolean checkUpDown(int x, int y) {
		if(0 <= (x + 1) && (x + 1) < R && 0 <= (x - 1) && (x - 1) < R) {
			if(roadMap[x-1][y] == '|' || roadMap[x-1][y] == '+' || roadMap[x-1][y] == '1' || roadMap[x-1][y] == '4') {
				if(roadMap[x+1][y] == '|' || roadMap[x+1][y] == '+' || roadMap[x+1][y] == '2' || roadMap[x+1][y] == '3') {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkRightLeft(int x, int y) {
		if(0 <= (y + 1) && (y + 1) < C && 0 <= (y - 1) && (y - 1) < C) {
			if(roadMap[x][y-1] == '-' || roadMap[x][y-1] == '+' || roadMap[x][y-1] == '1' || roadMap[x][y-1] == '2') {
				if(roadMap[x][y+1] == '-' || roadMap[x][y+1] == '+' || roadMap[x][y+1] == '3' || roadMap[x][y+1] == '4') {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkDownRight(int x, int y) {
		if(0 <= (x + 1) && (x + 1) < R && 0 <= (y + 1) && (y + 1) < C) {
			if(roadMap[x+1][y] == '|' || roadMap[x+1][y] == '+' || roadMap[x+1][y] == '2' || roadMap[x+1][y] == '3') {
				if(roadMap[x][y+1] == '-' || roadMap[x][y+1] == '+' || roadMap[x][y+1] == '3' || roadMap[x][y+1] == '4') {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkDownLeft(int x, int y) {
		if(0 <= (x + 1) && (x + 1) < R && 0 <= (y - 1) && (y - 1) < C) {
			if(roadMap[x+1][y] == '|' || roadMap[x+1][y] == '+' || roadMap[x+1][y] == '2' || roadMap[x+1][y] == '3') {
				if(roadMap[x][y-1] == '-' || roadMap[x][y-1] == '+' || roadMap[x][y-1] == '1' || roadMap[x][y-1] == '2') {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkUpRight(int x, int y) {
		if(0 <= (x - 1) && (x - 1) < R && 0 <= (y + 1) && (y + 1) < C) {
			if(roadMap[x-1][y] == '|' || roadMap[x-1][y] == '+' || roadMap[x-1][y] == '1' || roadMap[x-1][y] == '4') {
				if(roadMap[x][y+1] == '-' || roadMap[x][y+1] == '+' || roadMap[x][y+1] == '3' || roadMap[x][y+1] == '4') {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkUpLeft(int x, int y) {
		if(0 <= (x - 1) && (x - 1) < R && 0 <= (y - 1) && (y - 1) < C) {
			if(roadMap[x-1][y] == '|' || roadMap[x-1][y] == '+' || roadMap[x-1][y] == '1' || roadMap[x-1][y] == '4') {
				if(roadMap[x][y-1] == '-' || roadMap[x][y-1] == '+' || roadMap[x][y-1] == '1' || roadMap[x][y-1] == '2') {
					return true;
				}
			}
		}
		return false;
	}

}