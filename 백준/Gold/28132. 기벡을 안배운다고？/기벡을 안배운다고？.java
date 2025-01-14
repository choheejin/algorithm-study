import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Map<Pair, Integer> orthDict = new HashMap<>();
        int zeros = 0;
        long ans = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            // 영벡터 처리
            if (x == 0 && y == 0) {
                zeros++;
                continue;
            }

            // x축 위의 벡터 처리
            if (x == 0) {
                y = 1;
            }
            // y축 위의 벡터 처리
            else if (y == 0) {
                x = 1;
            }
            // 일반 벡터 처리 (기약분수 형태로 변환)
            else {
                long g = gcd(Math.abs(x), Math.abs(y));
                x /= g;
                y /= g;
            }

            // 현재 벡터에 수직인 벡터가 등장한 횟수를 더함
            Pair current = new Pair(x, y);
            ans += orthDict.getOrDefault(current, 0);

            // 현재 벡터에 수직인 벡터를 기록
            orthDict.put(new Pair(-y, x), orthDict.getOrDefault(new Pair(-y, x), 0) + 1);
            orthDict.put(new Pair(y, -x), orthDict.getOrDefault(new Pair(y, -x), 0) + 1);
        }

        // 영벡터와 관련된 쌍의 개수를 계산
        ans += (long) zeros * (N - zeros) + ((long) zeros * (zeros - 1) / 2);

        System.out.println(ans);
    }

    // 최대공약수 계산 함수
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 좌표를 저장하기 위한 Pair 클래스
    static class Pair {
        long x, y;

        Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}