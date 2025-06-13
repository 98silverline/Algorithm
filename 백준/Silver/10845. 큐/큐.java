import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, last;
    static Queue<Integer> que;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        que = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String str = st.nextToken();
            int num = 0;
            if(st.hasMoreTokens()) num = Integer.parseInt(st.nextToken());
            switch (str) {
                case "push":
                    que.add(num);
                    last = num;
                    break;
                case "pop":
                    if(que.isEmpty()) sb.append("-1").append("\n");
                    else sb.append(que.poll()).append("\n");
                    break;
                case "size":
                    sb.append(que.size()).append("\n");
                    break;
                case "empty":
                    if(que.isEmpty()) sb.append("1").append("\n");
                    else sb.append("0").append("\n");
                    break;
                case "front":
                    if(que.isEmpty()) sb.append("-1").append("\n");
                    else sb.append(que.peek()).append("\n");
                    break;
                case "back":
                    if(que.isEmpty()) sb.append("-1").append("\n");
                    else sb.append(last).append("\n");
                    break;
            }
        }

        System.out.println(sb);
    }
}
