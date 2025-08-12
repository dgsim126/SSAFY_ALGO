package _250807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N의 최대는 15
 * 15*15
 * 아까 풀었던 2차원 배열 누적합 활용
 */

public class _2001 {
	
	static int N;
	static int M;
	static int board[][];
	static int result;
	
	
	static void getMax() {
		
		for(int y=1; y<N+2-M; y++) { 
			for(int x=1; x<N+2-M; x++) { 
				int start_y= y;
				int start_x= x;
				int end_y= start_y+M-1;
				int end_x= start_x+M-1;
				
				int temp= (board[end_y][end_x]
		       			  		-board[start_y-1][end_x]
		       		            -board[end_y][start_x-1]
		       		            +board[start_y-1][start_x-1]);
		       	if(temp>result) {
		       		result= temp;
		       	}
			}
		}
		

		
	}
	
	static void cal() {
		for(int y=1; y<N+1; y++) { 
			for(int x=1; x<N+1; x++) { 
				board[y][x]= board[y][x] + board[y][x-1] + board[y-1][x] - board[y-1][x-1];
			}
		}	
	}
	
	static void solution() {
		// board를 전부 누적합으로 바꾸기
		cal();
		
		// 범위 M내 최대값 구하기
		getMax();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		for(int i=1; i<T+1; i++) {
			st= new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken());
			M= Integer.parseInt(st.nextToken());
			board= new int[N+1][N+1];
			result= 0;
			
			for(int y=1; y<N+1; y++) {
				st= new StringTokenizer(br.readLine());
				for(int x=1; x<N+1; x++) {
					board[y][x]= Integer.parseInt(st.nextToken());
				}
			}
			
			solution();
			System.out.println("#" + i + " " + result);
		}
		

	}

}
