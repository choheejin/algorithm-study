import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static class BC {
        int x, y;
        int c;
        int p;

        public BC(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
    }

    static int[] dx = {0,  0, 1, 0, -1}, dy = {0, -1, 0, 1, 0};
    static int[] pathA, pathB;
    static BC[] batterys;
    static int M, A, aX, aY, bX, bY, totalCharge;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            pathA = new int[M + 1];
            pathB = new int[M + 1];
            batterys = new BC[A];

            StringTokenizer st1 = new StringTokenizer(bf.readLine());
            StringTokenizer st2 = new StringTokenizer(bf.readLine());

            for (int m = 1; m <= M; m++) {
                pathA[m] = Integer.parseInt(st1.nextToken());
                pathB[m] = Integer.parseInt(st2.nextToken());
            }

            for (int a = 0; a < A; a++) {
                st = new StringTokenizer(bf.readLine());
                batterys[a] = new BC(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            aX = 1;
            aY = 1;
            bX = 10;
            bY = 10;

            totalCharge = 0;
            for (int m = 0; m <= M; m++) {
                aX += dx[pathA[m]];
                aY += dy[pathA[m]];
                bX += dx[pathB[m]];
                bY += dy[pathB[m]];

                int maxCharge = 0;
                for (int a = 0; a < A; a++) {
                    for (int b = 0; b < A; b++) {
                        int sum = 0;

                        if(isAvailable(batterys[a], aX, aY) && isAvailable(batterys[a], bX, bY) && a == b) {
                            sum += batterys[a].p;
                        } else {
                            if (isAvailable(batterys[a], aX, aY)) {
                                sum += batterys[a].p;
                            }
                            if (isAvailable(batterys[b], bX, bY)) {
                                sum += batterys[b].p;
                            }
                        }

                        maxCharge = Math.max(maxCharge, sum);
                    }
                }

                totalCharge += maxCharge;
            }
            System.out.println("#" + tc + " " + totalCharge);
        }
    }

    static boolean isAvailable(BC battery, int x, int y) {
        return (Math.abs(battery.x - x) + Math.abs(battery.y - y)) <= battery.c;
    }
}