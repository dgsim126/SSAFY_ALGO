package _250912;

import java.io.*;
import java.util.*;

public class _02_1494_사랑의카운슬러_SWEA {

    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();

        int T= Integer.parseInt(br.readLine().trim());
        for(int tc= 1; tc<= T; tc++){
            int N= Integer.parseInt(br.readLine().trim());
            long[] xs= new long[N];
            long[] ys= new long[N];
            for(int i= 0; i< N; i++){
                StringTokenizer st= new StringTokenizer(br.readLine());
                xs[i]= Long.parseLong(st.nextToken());
                ys[i]= Long.parseLong(st.nextToken());
            }
            long ans= solution(xs, ys);
            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        System.out.print(sb.toString());
    }

    // 로직만 수행 (입출력 없음)
    static long solution(long[] xs, long[] ys){
        int N= xs.length;
        long sumAllX= 0L, sumAllY= 0L;
        for(int i= 0; i< N; i++){
            sumAllX+= xs[i];
            sumAllY+= ys[i];
        }
        long[] best= new long[]{Long.MAX_VALUE};
        comb(0, 0, 0L, 0L, N, sumAllX, sumAllY, xs, ys, best);
        return best[0];
    }

    // 조합 탐색 (N/2개 선택)
    static void comb(int idx, int cnt, long sX, long sY,
                     int N, long sumAllX, long sumAllY,
                     long[] xs, long[] ys, long[] best){
        if(cnt== N/2){
            long dx= 2L*sX - sumAllX;
            long dy= 2L*sY - sumAllY;
            long val= dx*dx + dy*dy;
            if(val< best[0]) best[0]= val;
            return;
        }
        if(idx== N) return;
        if(N - idx < (N/2 - cnt)) return;

        // 선택
        comb(idx+1, cnt+1, sX + xs[idx], sY + ys[idx], N, sumAllX, sumAllY, xs, ys, best);
        // 비선택
        comb(idx+1, cnt, sX, sY, N, sumAllX, sumAllY, xs, ys, best);
    }
}
