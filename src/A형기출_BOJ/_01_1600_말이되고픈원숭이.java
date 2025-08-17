package A형기출_BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 원숭이는 말처럼 k번만 움직일 수 있다.
 * 현 위치에서 말처럼, 혹은 원숭이처럼 이동하는 모든 경우를 고려한다(말 4개 원숭이 4개 총 8가지 경우의 수)
 * 방문 여부를 계속 갱신하며 지워야 하기에 반드시 dfs를 사용해야 한다.
 * 목적지에 도달할 경우 얼마나 움직였는지를 계산하고 최솟값과 비교한다.
 */

public class _01_1600_말이되고픈원숭이 {
	
	static int K;
	static int W; // x
	static int H; // y
	static int[][] board;
	
	static int min_;
	
	static int d_y[]= {-1, 0, 1, 0}; // 북 동 남 서
	static int d_x[]= {0, 1, 0, -1}; 
	static int jump_d_y[]= {-2, -1, 1, 2, 2, 1, -1, -2}; // 1시 ~ 11시 (시계 방향)
	static int jump_d_x[]= {1, 2, 2, 1, -1, -2, -2, -1};
	
	static void dfs(int y, int x, int move, int jump) {
		
		if(move>min_) {
			return;
		}
		
		if(y==H-1 && x==W-1) {
			if(move<min_) {
				min_= move;
			}
			
			return;
		}
		
		if(jump>0) {
			for(int i=0; i<8; i++) {
				int n_y= y+jump_d_y[i];
				int n_x= x+jump_d_x[i];
				
				if(0<=n_y && n_y<H && 0<=n_x && n_x<W && board[n_y][n_x]!=1) {
					board[n_y][n_x]= 1;
					
					dfs(n_y, n_x, move+1, jump-1);
					
					board[n_y][n_x]= 0;
				}
			}
		}
		
		for(int i=0; i<4; i++) {
			int n_y= y+d_y[i];
			int n_x= x+d_x[i];
			
			if(0<=n_y && n_y<H && 0<=n_x && n_x<W && board[n_y][n_x]!=1) {
				board[n_y][n_x]= 1;
				
				dfs(n_y, n_x, move+1, jump);
				
				board[n_y][n_x]= 0;
			}
		}
		
	}
	
	static void solution() {
		
		dfs(0, 0, 0, K);
		
		if(min_==2147483647) {
			min_= -1;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K= Integer.parseInt(br.readLine());
		st= new StringTokenizer(br.readLine());
		W= Integer.parseInt(st.nextToken());
		H= Integer.parseInt(st.nextToken());
		board= new int[H][W];
		min_= 2147483647;
		
		for(int y=0; y<H; y++) {
			st= new StringTokenizer(br.readLine());
			for(int x=0; x<W; x++) {
				board[y][x]= Integer.parseInt(st.nextToken());
			}
		}
		
		solution();
		
		System.out.println(min_);
	}

}
