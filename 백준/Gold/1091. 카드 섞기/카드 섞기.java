import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] S, P;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        P = new int[N];
        S = new int[N];

        StringTokenizer st1 = new StringTokenizer(bf.readLine());
        StringTokenizer st2 = new StringTokenizer(bf.readLine());
        for(int i=0 ; i< N; i++) {
            P[i] = Integer.parseInt(st1.nextToken());
            S[i] = Integer.parseInt(st2.nextToken());
        }

        int count = 0;

        while(true) {
            if(isTarget(P)) {
                System.out.println(count);
                return;
            }

            P = shuffle(P);
            count++;

            if(count > N * 2550) {
                System.out.println(-1);
                return;
            }
        }

    }

    static boolean isTarget(int[] cards) {
        for(int i = 0; i< N; i++) {
            if(cards[i] != i % 3) return false;
        }
        return true;
    }

    static int[] shuffle(int[] cards) {
        int[] next = new int[N];

        for (int i = 0; i < N; i++) {
            next[S[i]] = cards[i];
        }

        return next;
    }
}
