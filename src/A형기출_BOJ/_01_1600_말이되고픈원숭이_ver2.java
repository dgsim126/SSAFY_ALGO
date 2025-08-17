package A형기출_BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class _01_1600_말이되고픈원숭이_ver2 {

    static int k,w,h;
    static int[][] map;
    static int[] dx= {1,-1,0,0}, dy= {0,0,1,-1};
    static int[] hx= {1,2,2,1,-1,-2,-2,-1};
    static int[] hy= {2,1,-1,-2,-2,-1,1,2};

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        k= Integer.parseInt(br.readLine());

        StringTokenizer st= new StringTokenizer(br.readLine());
        w= Integer.parseInt(st.nextToken());
        h= Integer.parseInt(st.nextToken());

        map= new int[h][w];
        for(int i=0;i<h;i++){
            st= new StringTokenizer(br.readLine());
            for(int j=0;j<w;j++){
                map[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(0,0));
    }

    static int bfs(int x,int y){
        if(w==1 && h==1) return 0;

        Queue<int[]> q= new ArrayDeque<>();
        boolean[][][] check= new boolean[h][w][k+1];

        check[y][x][0]= true;
        q.add(new int[]{x,y,0,0}); // x,y,chance,move

        while(!q.isEmpty()){
            int[] cur= q.poll();
            int px= cur[0], py= cur[1];
            int chance= cur[2], move= cur[3];

            // 원숭이 보행 4방
            for(int i=0;i<4;i++){
                int nx= px+dx[i];
                int ny= py+dy[i];
                if(nx<0 || nx>=w || ny<0 || ny>=h) continue;
                if(map[ny][nx]==1) continue;
                if(check[ny][nx][chance]) continue;

                if(nx==w-1 && ny==h-1) return move+1;
                check[ny][nx][chance]= true;
                q.add(new int[]{nx,ny,chance,move+1});
            }

            // 말 점프 8방
            if(chance<k){
                int nch= chance+1;
                for(int i=0;i<8;i++){
                    int nx= px+hx[i];
                    int ny= py+hy[i];
                    if(nx<0 || nx>=w || ny<0 || ny>=h) continue;
                    if(map[ny][nx]==1) continue;
                    if(check[ny][nx][nch]) continue;

                    if(nx==w-1 && ny==h-1) return move+1;
                    check[ny][nx][nch]= true;
                    q.add(new int[]{nx,ny,nch,move+1});
                }
            }
        }
        return -1;
    }
}
