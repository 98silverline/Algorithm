import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(bf.readLine()));
        }
        list.sort((Comparator.naturalOrder()));
        for (Integer i : list) {
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
}
