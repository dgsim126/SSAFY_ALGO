package _250829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 어려워 보이는데 사실 어렵지 않다. 깊이우선탐색!
 * 처음에 (start_y, start_x, end_y, end_x)의 범위가 전부 동일한지 확인한다.
 * 전부 동일하지 않다면 4개의 섹터로 나눠야한다. 이떄 해당 4개의 섹터를 다음 dfs로 넘기면 된다.
 * 
 */

public class 시험문제_03_1992_쿼드트리_BOJ {
	
	static int N;
	static int board[][];
	
	/**
	 * 해당 범위가 전부 같은 숫자인지 확인한다.
	 * 
	 * -1: 같지 않다면
	 * 0: 0으로 모두 동일하다면
	 * 1: 1로 모두 동일하다면
	 */
	static int checkAllSame(int start_y, int start_x, int end_y, int end_x) {
		int check= board[start_y][start_x];
		
		for(int y=start_y; y<=end_y; y++) {
			for(int x=start_x; x<=end_x; x++) {
				if(board[y][x]!=check) {
					return -1;
				}
			}
		}
		return check;
	}
	
	static int[][] divideSector(int start_y, int start_x, int end_y, int end_x) {
		int result[][]= new int[4][4];

		int mid_y= (start_y+end_y)/2;
		int mid_x= (start_x+end_x)/2;

		// 좌상단
		result[0][0]= start_y;
		result[0][1]= start_x;
		result[0][2]= mid_y;
		result[0][3]= mid_x;

		// 우상단
		result[1][0]= start_y;
		result[1][1]= mid_x+1;
		result[1][2]= mid_y;
		result[1][3]= end_x;

		// 좌하단
		result[2][0]= mid_y+1;
		result[2][1]= start_x;
		result[2][2]= end_y;
		result[2][3]= mid_x;

		// 우하단
		result[3][0]= mid_y+1;
		result[3][1]= mid_x+1;
		result[3][2]= end_y;
		result[3][3]= end_x;

		return result;
	}
	
	static void dfs(int start_y, int start_x, int end_y, int end_x) {
		
		// 1. 현재 범위 전체가 동일한 숫자인지 확인한다.
		// 동일한 숫자라면 해당 숫자를 출력한다. return
		int flag= checkAllSame(start_y, start_x, end_y, end_x); 
		
		// 2. 동일한 숫자가 아니라면 4개의 섹터로 나눠야한다.
		// 4개의 섹터로 나눈 후, dfs에 순서대로 호출한다.
		
		if(flag!=-1) {
			System.out.print(flag);
			return;
		}
		
	
		int temp[][]= divideSector(start_y, start_x, end_y, end_x);
			
		System.out.print("(");
		dfs(temp[0][0], temp[0][1], temp[0][2], temp[0][3]);
		dfs(temp[1][0], temp[1][1], temp[1][2], temp[1][3]);
		dfs(temp[2][0], temp[2][1], temp[2][2], temp[2][3]);
		dfs(temp[3][0], temp[3][1], temp[3][2], temp[3][3]);
		System.out.print(")");
	}
	
	static void solution() {
		
		int start_y= 0;
		int start_x= 0;
		int end_y= N-1;
		int end_x= N-1;
		
		dfs(start_y, start_x, end_y, end_x);
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		N= Integer.parseInt(br.readLine());
		board= new int[N][N];
		
		for(int y=0; y<N; y++) {
			String temp= br.readLine();
			for(int x=0; x<N; x++) {
				board[y][x]= temp.charAt(x)-'0';
			}
		}
		
		solution();
	}

}
