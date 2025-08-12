package _250811;

import java.io.*;
import java.util.*;
 
public class _5650{
 
    static int T;
    static int N;
    static int[][] map;
    static Map<Integer, ArrayList<int[]>> holes; 
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1}; 
    static int[][] blocks = {
            {0, 1, 2, 3},
            {2, 0, 3, 1},
            {2, 3, 1, 0},
            {1, 3, 0, 2},
            {3, 2, 0, 1},
            {2, 3, 0, 1}
    };
    static int[] oob = {2,3,0,1};
    static int max;
     
     
    public static int explore(int startX, int startY, int d) {
        int x = startX;
        int y = startY;
        x = x + dx[d];
        y = y + dy[d];
        int score = 0;
         
         
        while(true) {
            if (x == startX && y == startY) { return score;}
            if(x < 0 || x >= N || y < 0 || y >= N) {
                score++;
                d = oob[d];
                x = x + dx[d];
                y = y + dy[d];
                continue;
            }else{
                if(map[y][x] == -1) { return score; }
                else if(map[y][x] == 0) {
                    x = x + dx[d];
                    y = y + dy[d];
                }
                else if(map[y][x]>=1 && map[y][x]<=5) {
                    score++;
                    d = blocks[map[y][x]][d];
                    x = x + dx[d];
                    y = y + dy[d];
                }
                else if(map[y][x]>=6 && map[y][x]<=10) {
                    int[] xy1 = holes.get(map[y][x]).get(0);
                    int[] xy2 = holes.get(map[y][x]).get(1);
                    int holeX = xy1[0];
                    int holeY = xy1[1];
                    int holeX2 = xy2[0];
                    int holeY2 = xy2[1];
                     
                     
                    if(x == holeX && y == holeY) {
                        x = holeX2 + dx[d];
                        y = holeY2 + dy[d];
                    }
                    else {
                        x = holeX + dx[d];
                        y = holeY + dy[d];
                    }
                }
            }
     
        }
     
    }
 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
     
        for(int t=1; t<T+1; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            holes = new HashMap<>();
            max = -1;
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    // 웜홀 처리
                    if(map[i][j] >= 6) {
                        if(holes.containsKey(map[i][j])) {
                            int[] temp = {j, i};
                            holes.get(map[i][j]).add(temp);
                        }
                        else {
                            int[] temp = {j, i};
                            holes.put(map[i][j], new ArrayList<int[]>());
                            holes.get(map[i][j]).add(temp);
                        }
                    }
                }
            }
             
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] == 0) {
                        for(int k=0; k<4; k++ ) {
                                 
                            max = Math.max(max, explore(j, i, k));
                             
                        }
                    }
                }
            }
            System.out.println("#"+t+" "+max);
        }
         
         
    }
 
}