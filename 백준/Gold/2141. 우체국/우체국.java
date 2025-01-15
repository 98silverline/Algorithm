import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long max;
    static ArrayList<Town> arr;

    static class Town implements Comparable<Town>{
        int location;
        int population;

        public Town(int location, int population) {
            this.location = location;
            this.population = population;
        }

        @Override
        public int compareTo(Town o) {
            return this.location - o.location;
        }
    }

    static long solution(long mid) {
        long sum = 0;
        for (Town town : arr) {
            sum += town.population;
            if(sum >= mid) return town.location;
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new ArrayList<>();
        max = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int location = Integer.parseInt(st.nextToken());
            int population = Integer.parseInt(st.nextToken());
            arr.add(new Town(location, population));
            max += population;
        }

        Collections.sort(arr);

        System.out.println(solution((max + 1) / 2));
    }
}