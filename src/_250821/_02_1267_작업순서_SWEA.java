package _250821;
/**
 * 방문여부를 확인해야 하니 좌표는 dfs로 이동시킨다.
 * 현 위치에서 pointer에 따라 갈 수 있는 다음 위치는 다르다.
 * 처음 방향이 꺾일 때의 경우 해당 길이를 기억해둔다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class _02_1267_작업순서_SWEA {
	
	static int N;
	static int[][] board;
	static int result;
	static int d[][]= {{1, -1}, // flag==1
			 			{1, 1},  // flag==2
			 			{-1, 1}, // flag==3
			 			{-1,-1}};// flag==4
	
	/**
	 * 
	 * @param y 현재 좌표
	 * @param x
	 * @param first_side 처음 변의 길이
	 * @param second_side 두 번째 변의 길이
	 * @param flag
	 */
	static void dfs(int y, int x, int first_side, int second_side, int flag, Set set) {
		
		
		
		
		int n_y_1= y;
		int n_x_1= x;
		int n_y_2= y;
		int n_x_2= x;
		
		if(flag==1) {
			n_y_1+=d[0][0];
			n_x_1+=d[0][1];
			
			if(0<=n_y_1 && n_y_1<N && 0<=n_x_1 && n_x_1<N) {
				int set_length= set.size();
				set.add(board[n_y_1][n_x_1]);
				int set_length2= set.size();
				
				if(set_length!=set_length2) {
					dfs(n_y_1, n_x_1, first_side+1, second_side, flag, set);
				}
				
				set.remove(board[n_y_1][n_x_1]);
			}
			
			
			if(first_side>1) {
				n_y_2+=d[1][0];
				n_x_2+=d[1][1];
				
				if(0<=n_y_2 && n_y_2<N && 0<=n_x_2 && n_x_2<N) {
					int set_length= set.size();
					set.add(board[n_y_2][n_x_2]);
					int set_length2= set.size();
					
					if(set_length!=set_length2) {
						dfs(n_y_2, n_x_2, first_side, second_side+1, flag+1, set);
					}
					
					set.remove(board[n_y_2][n_x_2]);
				}
			}
			
		}else if(flag==2) {
			n_y_1+=d[1][0];
			n_x_1+=d[1][1];
			
			if(0<=n_y_1 && n_y_1<N && 0<=n_x_1 && n_x_1<N) {
				int set_length= set.size();
				set.add(board[n_y_1][n_x_1]);
				int set_length2= set.size();
				
				if(set_length!=set_length2) {
					dfs(n_y_1, n_x_1, first_side, second_side+1, flag, set);
				}
				
				set.remove(board[n_y_1][n_x_1]);
				
			}
			
			if(second_side>1) {
				n_y_2+=d[2][0];
				n_x_2+=d[2][1];
				
				if(0<=n_y_2 && n_y_2<N && 0<=n_x_2 && n_x_2<N) {
					int set_length= set.size();
					set.add(board[n_y_2][n_x_2]);
					int set_length2= set.size();
					
					if(set_length!=set_length2) {
						dfs(n_y_2, n_x_2, first_side, second_side, flag+1, set);
					}
					
					set.remove(board[n_y_2][n_x_2]);
					
				}
			}
			
		}else if(flag==3) {
			for(int i=0; i<first_side-2; i++) {
				y+=d[2][0];
				x+=d[2][1];
				
				int set_length= set.size();
				set.add(board[y][x]);
				int set_length2= set.size();
				
				if(set_length==set_length2) {
					return;
				}
				
				
			}
			
			for(int i=0; i<second_side-2; i++) {
				y+=d[3][0];
				x+=d[3][1];
				
				int set_length= set.size();
				set.add(board[y][x]);
				int set_length2= set.size();
				
				if(set_length==set_length2) {
					return;
				}
			}
			result+=1;
		}
	}
	
	static void solution() {
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				Set<Integer> set= new HashSet<>();
				dfs(y, x, 1, 1, 1, set);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			N= Integer.parseInt(br.readLine());
			board= new int[N][N];
			result= 0;
			
			for(int y=0; y<N; y++) {
				st= new StringTokenizer(br.readLine());
				for(int x=0; x<N; x++) {
					board[y][x]= Integer.parseInt(st.nextToken());
				}
			}
			solution();
		}
		
		System.out.println(result);

	}

}
