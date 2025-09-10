package _250910;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 키가 더 작은 학생 -> 키가 큰 학생
 * 
 * 플로이드워샬
 * 학생 수 만큼의 배열을 만든 후, 플로이드워셜을 돌린다.
 * 모든 플로이드워셜을 완료한 뒤, infinite가 없는 from 행을 cnt하면 된다.
 * 
 */

public class _00_5643_키순서_SWEA {
	
	static int N; // 학생 수
	static int M; // 두 학생의 키를 비교한 횟수
	static int board[][]; // y= from, x= to
	
	static int getResult() {
		int result= 0;
		
		for(int i=1; i<N+1; i++) {
			int temp= 0;
			for(int j=1; j<N+1; j++) {
				if((board[i][j]!=0 && board[i][j]!=1000000000) || (board[j][i]!=0 && board[j][i]!=1000000000)) {
					temp+=1;
				}
			}
			if(temp==N-1) {
				result+=1;
			}
		}
		
		return result;
	}
	
	
	static void dp() {
		for(int i=1; i<N+1; i++) { // 경유지
			for(int j=1; j<N+1; j++) { // 출발지
				for(int k=1; k<N+1; k++) { // 도착지
					board[j][k]= Math.min(board[j][k], board[j][i]+board[i][k]);
				}
			}
		}
		
//		for(int i=1; i<N+1; i++) {
//			for(int j=1; j<N+1; j++) {
//				System.out.print(board[i][j]+" ");
//			}
//			System.out.println();
//		}
	}
	
	static int solution() {
		// 플로이드워셜로 board 완성
		dp();
		
		// 결과 찾기
		return getResult();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			N= Integer.parseInt(br.readLine());
			M= Integer.parseInt(br.readLine());
			
			board= new int[N+1][N+1];
			for(int y=0; y<N+1; y++) {
				for(int x=0; x<N+1; x++) {
					if(x==y) {
						board[y][x]= 0;
					}else {
						board[y][x]= 1000000000;
					}
				}
			}
			
			for(int i=0; i<M; i++) {
				st= new StringTokenizer(br.readLine());
				int from= Integer.parseInt(st.nextToken());
				int to= Integer.parseInt(st.nextToken());
				
				board[from][to]= 1;
			}
			
			System.out.println("#"+tc+" "+solution());
		}
		
		

		
	}

}
