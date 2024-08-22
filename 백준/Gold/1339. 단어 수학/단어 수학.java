
import java.net.Inet4Address;
import java.util.*;
import java.io.*;

public class Main {
    static int N, answer;
    static PriorityQueue<Integer> pq;
    static HashMap<Character, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new HashMap<>();
        pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            String str = bf.readLine();
            int n = 1;
            for (int j = str.length() - 1; j >= 0; j--) {
                map.put(str.charAt(j), map.getOrDefault(str.charAt(j), 0) + n);
                n *= 10;
            }
        }

        for (Character key : map.keySet()) {
            pq.add(map.get(key));
        }
        int cnt = 9;
        while(!pq.isEmpty()) {
            answer += pq.poll() * cnt;
            cnt--;
        }

        System.out.println(answer);
    }
}