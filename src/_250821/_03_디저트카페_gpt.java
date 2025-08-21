package _250821;

import java.io.*;
import java.util.*;

public class _03_디저트카페_gpt {
    static int N, ans;
    static int[][] map;
    static boolean[] eaten;       // 디저트 종류 중복 체크 (0..100)
    static boolean[][] visited;   // 좌표 재방문 방지
    static final int[] dr = {1, 1, -1, -1};   // ↘, ↙, ↖, ↗
    static final int[] dc = {1, -1, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = -1;
            eaten = new boolean[101];
            visited = new boolean[N][N];

            // 모든 시작점 시도
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    Arrays.fill(eaten, false);
                    for (int i = 0; i < N; i++) Arrays.fill(visited[i], false);

                    eaten[map[r][c]] = true;
                    visited[r][c] = true;

                    // 첫 걸음을 반드시 dir=0(↘) 또는 그대로 진행하는 DFS에서 처리
                    dfs(r, c, r, c, 0, 1);

                    eaten[map[r][c]] = false;
                    visited[r][c] = false;
                }
            }

            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        System.out.print(sb.toString());
    }

    /**
     * @param r   현재 r
     * @param c   현재 c
     * @param sr  시작 r
     * @param sc  시작 c
     * @param dir 현재 진행 방향 인덱스(0..3). 같은 방향 유지 또는 +1만 허용
     * @param cnt 지금까지 먹은 디저트 수
     */
    static void dfs(int r, int c, int sr, int sc, int dir, int cnt) {
        // 같은 방향으로 가거나(dir), 다음 방향으로 한 번 꺾기(dir+1) 두 선택
        for (int turn = 0; turn <= 1; turn++) {
            int ndir = dir + turn;
            if (ndir > 3) continue;

            int nr = r + dr[ndir];
            int nc = c + dc[ndir];

            // 범위 밖
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            // 시작점으로 복귀 체크 (마지막 방향일 때만 유효)
            if (nr == sr && nc == sc && ndir == 3 && cnt >= 4) {
                ans = Math.max(ans, cnt);
                continue;
            }

            int dessert = map[nr][nc];
            if (visited[nr][nc]) continue;       // 좌표 재방문 금지
            if (eaten[dessert]) continue;        // 디저트 종류 중복 금지

            visited[nr][nc] = true;
            eaten[dessert] = true;

            dfs(nr, nc, sr, sc, ndir, cnt + 1);

            visited[nr][nc] = false;
            eaten[dessert] = false;
        }
    }
}
