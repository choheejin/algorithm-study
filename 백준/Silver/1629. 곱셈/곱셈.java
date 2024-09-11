import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        System.out.print(multiple(a, b, c));
    }

    static long multiple(long a, long b, long c) {
        if(b == 0) return 1; // 기저
        if(b%2 == 1) return (a * multiple(a, b -1, c)) % c;

        long result = multiple(a, b/2 , c) % c;

        return (result * result) % c;
    }
}