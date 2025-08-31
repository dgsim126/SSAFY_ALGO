package _250830_시험대비_분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

/**
 * start_y, start_x, end_y, end_x를 구한다.
 * 그걸 4등분을 한 후, 해당 범위 내 모든 값이 다 동일한지 확인한다.
 * 만약 동일하다면 더 함수를 호출할 필요없이 해당 색의 cnt를 +1하면 된다.
 */

public class _02_2630_색종이만들기_BOJ {
	
	static int N;
	static int[][] board;
	static int blue_cnt; // 파란색==1
	static int white_cnt; // 흰색==0
	
	/**
	 * @return 값이 동일하지 않다면 -1, 전부 흰색이면 0, 전부 파란색이면 1
	 */
	static int checkColor(int start_y, int start_x, int end_y, int end_x) {
		int first= board[start_y][start_x];
		
		for(int y=start_y; y<=end_y; y++) {
			for(int x=start_x; x<=end_x; x++) {
				if(board[y][x]!=first) {
					return -1;
				}
			}
		}
		
		return first;
	}
	
	static void dfs(int start_y, int start_x, int end_y, int end_x) {
		// 1. 받아온 4개의 값을 이용해 전체 값이 동일한지 확인한다. checkColor()를 호출한다.
		int flag= checkColor(start_y, start_x, end_y, end_x);
		
		// 2. checkColor가 0혹은 1이면 전역 cnt를 증가시키고 return한다.
		// checkColor가 -1이면 새롭게 4개의 섹션으로 나눠서 dfs를 호출한다.
		if(flag==-1) {
			dfs(start_y, start_x, (start_y+end_y)/2, (start_x+end_x)/2); 
			dfs(start_y, (start_x+end_x)/2+1, (start_y+end_y)/2, end_x);
			dfs((start_y+end_y)/2+1, start_x, end_y, (start_x+end_x)/2);
			dfs((start_y+end_y)/2+1, (start_x+end_x)/2+1, end_y, end_x);
		}else if(flag==0) {
			white_cnt+=1;
			return;
		}else {
			blue_cnt+=1;
			return;
		}
	}
	
	static void solution() {
		// 1. 초기 2개의 좌표의 인덱스를 구한다.
		int start_y= 0;
		int start_x= 0;
		int end_y= N-1;
		int end_x= N-1;
		
		// 2. dfs에 해당 값들을 넣는다.
		dfs(start_y, start_x, end_y, end_x);
		
		System.out.println(white_cnt);
		System.out.println(blue_cnt);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N= Integer.parseInt(br.readLine());
		board= new int[N][N];
		
		for(int y=0; y<N; y++) {
			st= new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) {
				board[y][x]= Integer.parseInt(st.nextToken());
			}
		}
		
		solution();
	}

}
