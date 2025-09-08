package _250904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;

public class _01_5648_원자소멸시뮬레이션_SWEA {

    static int N;
    static int total_energy;

    static Queue<int[]> queue;

    // 상 하 좌 우
    static int[] dy= {1, -1, 0, 0};
    static int[] dx= {0, 0, -1, 1};

    static final int MAX= 4000; // 2배

    static void solution() {
        while (!queue.isEmpty()) {
            int size = queue.size();

            // (동시 처리)
            HashMap<Long, Integer> sum= new HashMap<>(); // 해당 칸의 에너지 합
            HashMap<Long, Integer> cnt= new HashMap<>(); // 해당 칸의 개수
            HashMap<Long, int[]> last= new HashMap<>();  // 단독 생존 시 다시 큐

            for(int i= 0; i<size; i++) {
                int[] cur= queue.poll();
                int y= cur[0];
                int x= cur[1];
                int d= cur[2];
                int e= cur[3];

                int ny= y+dy[d];
                int nx= x+dx[d];

                // 범위 밖
                if(ny<0 || ny>MAX || nx<0 || nx>MAX) { 
                	continue;
                }

                long key= ((long) ny) * 5000 + (long) nx; // 충돌 없는 좌표 키
                sum.put(key, sum.getOrDefault(key, 0) + e);
                cnt.put(key, cnt.getOrDefault(key, 0) + 1);
                last.put(key, new int[]{ny, nx, d, e}); // 다시 큐에 넣으려고
            }

            // 모인칸 합치기
            for(Map.Entry<Long, Integer> ent : cnt.entrySet()) {
                long key= ent.getKey();
                if(ent.getValue()>=2) {
                    total_energy+=sum.get(key);
                }else {
                    queue.add(last.get(key));
                }
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T= Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            N= Integer.parseInt(br.readLine());

            queue= new LinkedList<>();
            total_energy= 0;

            for(int i=0; i<N; i++) {
                st= new StringTokenizer(br.readLine());
                int x= Integer.parseInt(st.nextToken());
                int y= Integer.parseInt(st.nextToken());
                int d= Integer.parseInt(st.nextToken());
                int e= Integer.parseInt(st.nextToken());

                // 좌표 2배 
                int sy= (y+1000)*2;
                int sx= (x+1000)*2;

                if(0<=sy && sy<=MAX && 0<=sx && sx<=MAX) {
                    queue.add(new int[]{sy, sx, d, e});
                }
            }

            solution();
            System.out.println("#" + tc + " " + total_energy);
        }
    }
}
