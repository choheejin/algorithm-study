import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static int n, m, a, b, x, y;
    static int[][] map = new int[101][101];
    static int[][] dp = new int[101][101];
    static ArrayList<Pair> items = new ArrayList<>();

    static class Pair implements Comparable<Pair> {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.first == other.first) {
                return Integer.compare(this.second, other.second);
            }
            return Integer.compare(this.first, other.first);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        n = scanner.nextInt();
        m = scanner.nextInt();
        a = scanner.nextInt();
        b = scanner.nextInt();

        while (a-- > 0) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            items.add(new Pair(x, y));
        }

        while (b-- > 0) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            map[x][y] = 1;
        }

        items.add(new Pair(1, 1));
        Collections.sort(items);
        items.add(new Pair(n, m));
        
        dp[1][1] = 1;
        for (int i = 0; i <= n; i++) map[i][0] = 1;
        for (int i = 0; i <= m; i++) map[0][i] = 1;

        for (int i = 1; i < items.size(); i++) {
            int sx = items.get(i - 1).first;
            int sy = items.get(i - 1).second;
            int ex = items.get(i).first;
            int ey = items.get(i).second;

            if (sx == ex && sy == ey) continue;

            for (int j = sx; j <= ex; j++) {
                for (int k = sy; k <= ey; k++) {
                    if (map[j][k] == 1) continue;

                    if (map[j - 1][k] != 1 && map[j][k - 1] != 1) {
                        dp[j][k] = dp[j - 1][k] + dp[j][k - 1];
                    } else if (map[j - 1][k] != 1) {
                        dp[j][k] = dp[j - 1][k];
                    } else if (map[j][k - 1] != 1) {
                        dp[j][k] = dp[j][k - 1];
                    }
                }
            }
        }
        
        System.out.println(dp[n][m]);
        scanner.close();
    }
}