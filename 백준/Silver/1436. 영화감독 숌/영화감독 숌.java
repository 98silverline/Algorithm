import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int answer = 0;
        int num = 666;
        while(N != answer) {
            if(String.valueOf(num).contains("666")) answer++;
            num++;
        }

        System.out.println(num - 1);
    }
}
