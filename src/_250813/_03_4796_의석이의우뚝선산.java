package _250813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _03_4796_의석이의우뚝선산 {

    static int N;
    static int[] arr;
    static long cnt; // 구간 수는 long!

    static void solution() {
        for (int k = 1; k < N - 1; k++) {
            int left_cnt = 0, right_cnt = 0;

            // 왼쪽: ... < arr[k-1] < arr[k]
            int current = k;
            while (current - 1 >= 0 && arr[current - 1] < arr[current]) {
                left_cnt++;
                current--;
            }
            if (left_cnt == 0) continue;

            // 오른쪽: arr[k] > arr[k+1] > ...
            current = k;
            while (current + 1 < N && arr[current] > arr[current + 1]) {
                right_cnt++;
                current++;
            }
            if (right_cnt == 0) continue;

            cnt += 1L * left_cnt * right_cnt;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            arr = new int[N];
            cnt = 0L;

            // 숫자 N개를 모을 때까지 안전하게 파싱
            int filled = 0;
            while (filled < N) {
                String line = br.readLine();
                if (line == null) break; // 방어
                StringTokenizer st = new StringTokenizer(line);
                while (st.hasMoreTokens() && filled < N) {
                    arr[filled++] = Integer.parseInt(st.nextToken());
                }
            }

            if (N <= 2) {
                sb.append('#').append(tc).append(' ').append(0).append('\n');
                continue;
            }

            solution();
            sb.append('#').append(tc).append(' ').append(cnt).append('\n');
        }

        System.out.print(sb.toString());
    }
}
