import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String start, end;

    static boolean solution() {
        while(end.length() > start.length()) {
            if(end.charAt(end.length() - 1) == 'B') {
                end = end.substring(0, end.length() - 1);
                StringBuilder sb = new StringBuilder();
                sb.append(end);
                end = sb.reverse().toString();
            } else end = end.substring(0, end.length() - 1);
        }
        return end.equals(start);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        start = bf.readLine();
        end = bf.readLine();
        if(solution()) System.out.println(1);
        else System.out.println(0);
    }
}
