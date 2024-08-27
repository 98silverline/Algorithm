import java.io.*;
import java.util.*;

public class Main {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		int a = 1;
		int b = 0;
		for(int i = 0; i < N; i++) {
			int tmp = b;
			b += a;
			a = tmp;
		}
		System.out.println(a + " " + b);
	}
}