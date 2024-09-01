import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static class Bacteria {
        int x, y, cnt, dir;

        public Bacteria(int x, int y, int cnt, int dir) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dir = dir;
        }
    }

    static int N, M, K;
    static List<Bacteria> list;
    static int[] dx = {0, -1, 1, 0, 0}, dy = {0, 0, 0, -1, 1};

    // 최초 약품이 칠해진 가장자리 부분 셀에는 미생물 배치가 되지 않기 때문에
    // 0/N-1에서 방향을 계속 반대 방향으로 바꿔주기 때문에 경계선 체크 필요 없다.
    // 1. 이동 시킨다.
    // 2. 동일 좌표 미생물 군집 처리
    // 3. 0/N-1 좌표 처리

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            N = Integer.parseInt(st.nextToken()); // 구역 크기
            M = Integer.parseInt(st.nextToken()); // 격리시간
            K = Integer.parseInt(st.nextToken()); // 미생물 군집의 개수

            list = new ArrayList<>();
            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                list.add(new Bacteria(x, y, cnt, dir));
            }

            for(int time = 0; time < M; time++) {
                move();
                border();
                samePosition();
            }

            int result = 0;
            for(int i = 0; i < list.size(); i++) {
                result += list.get(i).cnt;
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    static void move() {
        for(Bacteria b : list) {
            b.x = dx[b.dir] + b.x;
            b.y = dy[b.dir] + b.y;
        }
    }

    static void samePosition () {
        list.sort((Bacteria b1, Bacteria b2) -> {
            if(b1.x == b2.y && b1.y == b2.y) {
                return Integer.compare(b1.cnt, b2.cnt);
            } else if (b1.x < b2.x) {
                return 1;
            } else if (b1.x > b2.x) {
                return -1;
            } else {
                if (b1.y < b2.y) {
                    return 1;
                } else if (b1.y > b2.y) {
                    return -1;
                }
            }
            return 0;
        });


        for(int i = 0 ; i < list.size(); i++) {
            Bacteria target = list.get(i);

            boolean[] idxs = new boolean[list.size()];
            idxs[i] = true;

            int cnt = 1;
            int total = target.cnt;
            int maxValue = target.cnt;
            int maxIdx = i;

            for(int j = 0; j < list.size(); j++) {
                if(i == j) continue;
                if(target.x == list.get(j).x && target.y == list.get(j).y) {
                    idxs[j] = true;
                    total += list.get(j).cnt;
                    if(maxValue < list.get(j).cnt) {
                        maxValue = list.get(j).cnt;
                        maxIdx = j;
                    }
                    cnt++;
                }
            }

            if(cnt == 1) continue;

            for(int j = list.size() - 1; j >= 0; j--) {
                if(!idxs[j]) continue;
                if(maxIdx == j) {
                    list.get(j).cnt = total;
                }
                else {
                    list.remove(j);
                }
            }
        }
    }

    static void border() {
        for(int i = 0; i < list.size(); i++) {
            Bacteria target = list.get(i);
            if(target.x == 0 || target.x == N-1 || target.y == 0 || target.y == N-1) {
                target.cnt /= 2;
                target.dir = change(target.dir);
            }
        }
    }

    static int change(int dir) {
        if(dir == 1) {
            return 2;
        }
        else if(dir == 2) {
            return 1;
        }
        else if(dir == 3) {
            return 4;
        }
        return 3;
    }
}