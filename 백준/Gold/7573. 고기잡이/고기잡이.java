import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, I, M;
    static List<int[]> fish = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        I = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            fish.add(new int[]{x * 2, y * 2});
        }

        int result = 0;

        for (int h = 1; h < I / 2; h++) {
            int w = (I / 2) - h;
            result = Math.max(result, check(h, w));
        }

        System.out.println(result);
    }

    public static int check(int h, int w) {
        h *= 2;
        w *= 2;

        int max = 0;

        for (int[] f : fish) {
            int fx = f[0];
            int fy = f[1];

            for (int x1 = fx - h; x1 <= fx; x1++) {
                if (x1 < 0 || x1 + h > N * 2) continue;

                for (int y1 = fy - w; y1 <= fy; y1++) {
                    if (y1 < 0 || y1 + w > N * 2) continue;

                    int cnt = 0;

                    for (int[] p : fish) {
                        int px = p[0];
                        int py = p[1];

                        if (px >= x1 && px <= x1 + h &&
                            py >= y1 && py <= y1 + w) {
                            cnt++;
                        }
                    }

                    max = Math.max(max, cnt);
                }
            }
        }

        return max;
    }
}
