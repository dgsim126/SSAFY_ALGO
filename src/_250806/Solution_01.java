package _250806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 밑에서부터 올라갈건데 어떻게?
 * 1. 현 위치에서 왼쪽 오른쪽 탐색
 * 2-1. (오른쪽이 1이라면) while을 돌며 오른쪽이 0나오기 전까지 이동
 * 2-2. (왼쪽이 1이라면) while을 돌며 왼쪽이 0나오기 전까지 이동
 * 2-3. 두 가지에 해당되지 않는다면 위로 한 칸 이동후 1번 반복
 */

public class Solution_01 {
	
	static int T;
	
	public static int toFindStart(int board[][]) {
		for(int x=0; x<100; x++) {
			if(board[99][x]==2) {
				return x;
			}
		}
		return -1;
	}
	
	public static int solution(int board[][]) {
		
		// x의 위치 찾기
		int y= 99;
		int x= toFindStart(board);
		
		// y인덱스가 0이될때까지 반복
		while(y!=0) {
			// 오른쪽
			if(x+1<100 && board[y][x+1]==1) { // 오른쪽
				x+=1;
				while(x+1 <100 && board[y][x+1]==1) {
					x+=1;
				}
				y-=1;
			}else if(0<=x-1 && board[y][x-1]==1){ // 왼쪽
				x-=1;
				while(0<=x-1 && board[y][x-1]==1){
					x-=1;
				}
				y-=1;
			}else { // 위로 이동
				y-=1;
			}
		}
		
		
		return x;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T= 10;
		for(int t=1; t<T+1; t++) {
			int board[][]= new int[100][100];
			br.readLine();
			
			for(int i=0; i<100; i++) {
				st= new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					board[i][j]= Integer.parseInt(st.nextToken());
				}
			}
			int result= solution(board);
			System.out.println("#" + t + " " + result);

		}


	}

}
