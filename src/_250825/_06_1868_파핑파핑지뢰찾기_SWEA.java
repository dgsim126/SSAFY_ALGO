package _250825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class _06_1868_파핑파핑지뢰찾기_SWEA {
	
	static int N;
	static int[][] board;   
	static int[][] visited; 
	
	static int[] dy= {-1, -1, 0, 1, 1, 1, 0, -1}; // 북 -> 시계
	static int[] dx= {0, 1, 1, 1, 0, -1, -1, -1};
	
	static int justCheckBomb(int y, int x) {
		int cnt= 0;
		for(int i=0; i<8; i++) {
			int ny= y + dy[i];
			int nx= x + dx[i];
			if(0<=ny && ny<N && 0<=nx && nx<N) {
				if(board[ny][nx]=='*') {	
					cnt++;
				}
			}
		}
		return cnt;
	}

	static int checkBomb(int y, int x) {
		int cnt= 0;
		for (int i=0; i<8; i++) {
			int ny= y+ dy[i];
			int nx= x+ dx[i];
			if(0<=ny && ny<N && 0<=nx && nx<N) {
				if(board[ny][nx]=='*') {
					cnt++;
				}
			}
		}
		board[y][x] = cnt; 
		return cnt;
	}
	

	static void chainBomb(int y, int x) {
		Queue<int[]> queue = new LinkedList<>();
		
		checkBomb(y, x);
		visited[y][x] = 1;
		queue.add(new int[] {y, x});
		
		while(!queue.isEmpty()) {
			int[] cur= queue.poll();
			int cy= cur[0];
			int cx = cur[1];
			
			for(int i=0; i<8; i++) {
				int ny= cy+dy[i];
				int nx= cx+dx[i];
				if(0<=ny && ny<N && 0<=nx && nx<N) {
					if(board[ny][nx]=='*' || visited[ny][nx]==1) {
						continue;
					}
					
					int cnt= checkBomb(ny, nx); 
					if(cnt==0) {
						visited[ny][nx]= 1;     
						queue.add(new int[] {ny, nx});
					}
				}
			}
		}
	}
	
	static int solution() {
		int result = 0;
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(board[y][x]=='.' && visited[y][x]==0) {
					if(justCheckBomb(y, x)==0) {
						result+=1;          
						chainBomb(y, x);   
					}
				}
			}
		}
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(board[y][x]=='.') {
					result+=1;
				}
			}
		}
		
		return result;
	}
 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N= Integer.parseInt(br.readLine());
			
			board= new int[N][N];
			visited= new int[N][N];
			
			for(int y=0; y<N; y++) {
				String temp= br.readLine();
				for(int x=0; x<N; x++) {
					board[y][x]= temp.charAt(x);
				}
			}
			
			int result= solution();
			System.out.println("#" + tc + " " + result);
		}
	}
}
