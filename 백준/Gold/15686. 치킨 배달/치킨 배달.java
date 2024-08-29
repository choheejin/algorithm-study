import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 조합 문제
    // 최악의 경우의 수: 13 C 7 = 1716
    // 1. 치킨집을 정보를 저장하는 리스트 만들어준다.
    // 2. 조합 뽑는다.
    // 3. 뽑힌 치킨집과 집과의 최소 거리들을 구한다.

    static class Customer {
        int x;
        int y;
        int minDistance;

        public Customer(int x, int y) {
            this.x = x;
            this.y = y;
            this.minDistance = Integer.MAX_VALUE;
        }

        public int getDistance(Shop shop) {
            return Math.abs(this.x - shop.x) + Math.abs(this.y - shop.y);
        }

        public void setMinDistance(int distance) {
            this.minDistance = Math.min(distance, this.minDistance);
        }
    }

    static class Shop {
        int x;
        int y;
        public Shop(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int customerIdx, shopIdx;
    static int minResult;
    static char[][] map;
    static Shop[] shops, selectedShops;
    static Customer[] customers;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        shops = new Shop[13];
        selectedShops = new Shop[M];
        customers = new Customer[2 * N];

        for(int i = 0 ; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                switch (map[i][j]) {
                    case '1':
                        customers[customerIdx++] = new Customer(i, j);
                        break;
                    case '2':
                        shops[shopIdx++] = new Shop(i, j);
                        break;
                }
            }
        }

        minResult = Integer.MAX_VALUE;
        combination(0, 0);

        System.out.println(minResult);
    }

    static void combination (int start, int cnt) {
        if(cnt == M) {
            calculateDistance();
            return;
        }

        for(int i = start; i < shopIdx; i++) {
            selectedShops[cnt] = shops[i];
            combination(i + 1, cnt + 1);
        }
    }

    static void calculateDistance() {
        for(int c = 0; c < customerIdx; c++) {
            Customer customer = customers[c];
            for(int s = 0; s < M; s++) {
                int distance = customer.getDistance(selectedShops[s]);
                customer.setMinDistance(distance);
            }
        }

        int result = 0;
        for(int i = 0; i < customerIdx; i++) {
            result += customers[i].minDistance;
            customers[i].minDistance = Integer.MAX_VALUE;
        }

        minResult = Math.min(minResult, result);
    }
}