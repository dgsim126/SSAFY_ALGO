package _250821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 주의해야할 건 위치 방향 이동하는 것 뿐
 * board, visited만들고 board보면서 가능한 위치를 이동시키며 visited를 1로 변경
 * 그 후 visited 1의 개수를 count하면 끝
 * 
 * 현재 내가 있는 위치에서 갈 수 있는 방향에 위치한 인덱스만 보드에 넣을 것
 * 
 * 다음 파이프랑 이어져 있는지 확인해야한다!
 * 
 */

public class _01_1953_탈주범검거_SWEA {
	
	static int N; // y
	static int M; // x
	static int R; // start_y
	static int C; // start_x
	static int L; // bfs 탐색 depth
	
	static int board[][];
	static int visited[][];
	
	static int dy[]= {-1, 0, 1, 0, -1}; // 복 동 남 서 북
	static int dx[]= {0, 1, 0, -1, 0};
	
	static int solution() {
	    Queue<int[]> queue= new LinkedList<>();

	    // 처음 위치 삽입
	    queue.offer(new int[] {R, C, 1});

	    while(!queue.isEmpty()) {
	        int current[]= queue.poll();
	        int y= current[0];
	        int x= current[1];
	        int time= current[2];

	        // 방문처리
	        visited[y][x]= 1;

	        // 시간만큼 최대로 움직이면 확장 중단(방문은 인정)
	        if(time==L) continue;

	        if(board[y][x]==1) { // U R D L
	            for(int i=0; i<4; i++) {
	                int ny= y+dy[i];
	                int nx= x+dx[i];
	                int flag= 0;

	                if(0<=ny && ny<N && 0<=nx && nx<M && visited[ny][nx]==0 && board[ny][nx]!=0) {
	                    if(i==0 && (board[ny][nx]==1 || board[ny][nx]==2 || board[ny][nx]==5 || board[ny][nx]==6)) flag=1; 
	                    if(i==1 && (board[ny][nx]==1 || board[ny][nx]==3 || board[ny][nx]==6 || board[ny][nx]==7)) flag=1; 
	                    if(i==2 && (board[ny][nx]==1 || board[ny][nx]==2 || board[ny][nx]==4 || board[ny][nx]==7)) flag=1; 
	                    if(i==3 && (board[ny][nx]==1 || board[ny][nx]==3 || board[ny][nx]==4 || board[ny][nx]==5)) flag=1; 
	                    if(flag==1) {
	                        visited[ny][nx]= 1;
	                        queue.offer(new int[] {ny, nx, time+1});
	                    }
	                }
	            }
	        } else if(board[y][x]==2) { // U, D
	            for(int i=0; i<4; i+=2) {
	                int ny= y+dy[i];
	                int nx= x+dx[i];
	                int flag= 0;

	                if(0<=ny && ny<N && 0<=nx && nx<M && visited[ny][nx]==0 && board[ny][nx]!=0) {
	                    if(i==0 && (board[ny][nx]==1 || board[ny][nx]==2 || board[ny][nx]==5 || board[ny][nx]==6)) flag=1; 
	                    if(i==2 && (board[ny][nx]==1 || board[ny][nx]==2 || board[ny][nx]==4 || board[ny][nx]==7)) flag=1; 
	                }

	                if(flag==1) {
	                    visited[ny][nx]= 1;
	                    queue.offer(new int[] {ny, nx, time+1});
	                }
	            }
	        } else if(board[y][x]==3) { // R, L
	            for(int i=1; i<4; i+=2) {
	                int ny= y+dy[i];
	                int nx= x+dx[i];
	                int flag= 0;

	                if(0<=ny && ny<N && 0<=nx && nx<M && visited[ny][nx]==0 && board[ny][nx]!=0) {
	                    if(i==1 && (board[ny][nx]==1 || board[ny][nx]==3 || board[ny][nx]==6 || board[ny][nx]==7)) flag=1; 
	                    if(i==3 && (board[ny][nx]==1 || board[ny][nx]==3 || board[ny][nx]==4 || board[ny][nx]==5)) flag=1; 
	                    if(flag==1) {
	                        visited[ny][nx]= 1;
	                        queue.offer(new int[] {ny, nx, time+1});
	                    }
	                }
	            }

	        } else if(board[y][x]==4) { // U, R
	            for(int i=0; i<2; i++) {
	                int ny= y+dy[i];
	                int nx= x+dx[i];
	                int flag= 0;

	                if(0<=ny && ny<N && 0<=nx && nx<M && visited[ny][nx]==0 && board[ny][nx]!=0) {
	                    if(i==0 && (board[ny][nx]==1 || board[ny][nx]==2 || board[ny][nx]==5 || board[ny][nx]==6)) flag=1; 
	                    if(i==1 && (board[ny][nx]==1 || board[ny][nx]==3 || board[ny][nx]==6 || board[ny][nx]==7)) flag=1; 
	                    if(flag==1) {
	                        visited[ny][nx]= 1;
	                        queue.offer(new int[] {ny, nx, time+1});
	                    }
	                }
	            }
	        } else if(board[y][x]==5) { // R, D
	            for(int i=1; i<3; i++) {
	                int ny= y+dy[i];
	                int nx= x+dx[i];
	                int flag= 0;

	                if(0<=ny && ny<N && 0<=nx && nx<M && visited[ny][nx]==0 && board[ny][nx]!=0) {
	                    if(i==1 && (board[ny][nx]==1 || board[ny][nx]==3 || board[ny][nx]==6 || board[ny][nx]==7)) flag=1; 
	                    if(i==2 && (board[ny][nx]==1 || board[ny][nx]==2 || board[ny][nx]==4 || board[ny][nx]==7)) flag=1; 
	                    if(flag==1) {
	                        visited[ny][nx]= 1;
	                        queue.offer(new int[] {ny, nx, time+1});
	                    }
	                }
	            }
	        } else if(board[y][x]==6) { // D, L
	            for(int i=2; i<4; i++) {
	                int ny= y+dy[i];
	                int nx= x+dx[i];
	                int flag= 0;

	                if(0<=ny && ny<N && 0<=nx && nx<M && visited[ny][nx]==0 && board[ny][nx]!=0) {
	                    if(i==2 && (board[ny][nx]==1 || board[ny][nx]==2 || board[ny][nx]==4 || board[ny][nx]==7)) flag=1; 
	                    if(i==3 && (board[ny][nx]==1 || board[ny][nx]==3 || board[ny][nx]==4 || board[ny][nx]==5)) flag=1; 
	                    if(flag==1) {
	                        visited[ny][nx]= 1;
	                        queue.offer(new int[] {ny, nx, time+1});
	                    }
	                }
	            }
	        } else { // 7: U, L
	            for(int i=0; i<4; i+=3) {
	                int ny= y+dy[i];
	                int nx= x+dx[i];
	                int flag= 0;

	                if(0<=ny && ny<N && 0<=nx && nx<M && visited[ny][nx]==0 && board[ny][nx]!=0) {
	                    if(i==0 && (board[ny][nx]==1 || board[ny][nx]==2 || board[ny][nx]==5 || board[ny][nx]==6)) flag=1; 
	                    if(i==3 && (board[ny][nx]==1 || board[ny][nx]==3 || board[ny][nx]==4 || board[ny][nx]==5)) flag=1; 
	                    if(flag==1) {
	                        visited[ny][nx]= 1;
	                        queue.offer(new int[] {ny, nx, time+1});
	                    }
	                }
	            }
	        }
	    }

	    int result= 0;
	    for(int y=0; y<N; y++) {
	        for(int x=0; x<M; x++) {
	            if(visited[y][x]== 1) result++;
	        }
	    }
	    return result;
	}

	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int t=1; t<T+1; t++) {
			st= new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken());
			M= Integer.parseInt(st.nextToken());
			R= Integer.parseInt(st.nextToken());
			C= Integer.parseInt(st.nextToken());
			L= Integer.parseInt(st.nextToken());
			
			board= new int[N][M];
			visited= new int[N][M];
			
			for(int y=0; y<N; y++) {
				st= new StringTokenizer(br.readLine());
				for(int x=0; x<M; x++) {
					board[y][x]= Integer.parseInt(st.nextToken());
				}
			}
			
			int result= solution();
			
			System.out.println("#" + t + " " + result);
		}

	}

}
