
import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static String num;
    static StringBuilder sb;
    static Deque<Integer> dq;
    static void solution() {
        int idx = 0;
        while(M > 0 && idx < num.length()) {
//            System.out.println("pl" + dq.peekLast());
            if(dq.isEmpty() || dq.peekLast() >= num.charAt(idx) - '0') {
                dq.offerLast(num.charAt(idx) - '0');
                idx++;
            }
            else {
                dq.pollLast();
                M--;
            }
        }
//        System.out.println("size" + dq.size());
        while(M > 0) {
            dq.pollLast();
            M--;
        }
        while(!dq.isEmpty()) {
//            System.out.println("dq.peek: " + dq.peekFirst());
            sb.append(dq.pollFirst());
        }
//        System.out.println(num.substring(idx));
        sb.append(num.substring(idx));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = bf.readLine();
        dq = new ArrayDeque<>();
        sb = new StringBuilder();
        solution();
        System.out.println(sb.toString());
    }
}
