import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] importance = new int[26];
	static int[] assign = new int[26];
	static int[] numArr = new int[10];
	static char[][] inputArr;
	static boolean[] available = new boolean[10];
	static int answer;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inputArr = new char[N][];
		answer = 0;

		for(int i = 9; i >= 0; i--) {
			numArr[9-i] = i;
		}

		for(int i = 0; i < N; i++) {
			inputArr[i] = br.readLine().toCharArray();

			for(int j = 0; j < inputArr[i].length; j++) {
				importance[inputArr[i][inputArr[i].length - j - 1] - 'A'] += Math.pow(10, j);
			}
		}

		getAnswer();
		System.out.println(answer);
	}

	public static void getAnswer() {
		
		for(int j = 0; j < 26; j++) {
			int maxNum = -1;
			int numIndex = 0;

			for(int i = 0; i < 26; i++) {
				if(importance[i] > maxNum && importance[i] != 0) {
					maxNum = importance[i];
					numIndex = i;
				}
			}
			
			if(maxNum == -1) {
				break;
			}

			for(int i = 0 ; i< 10; i++) {
				if(!available[i]) {
					assign[numIndex] = numArr[i];
					importance[numIndex] = 0;
					available[i] = true;
					break;
				}
			}
		}
		
		
		for(int i = 0; i < N; i++) {
			int temp = 0;
			for(int j = 0; j < inputArr[i].length; j++) {
				temp += assign[inputArr[i][inputArr[i].length - j - 1] - 'A'] * Math.pow(10, j);
			}
			answer += temp;
		}

	}

}