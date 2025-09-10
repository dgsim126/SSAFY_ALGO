package _250910;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 방법은 2가지
 * 1. 노드 개수 만큼 다익스트라 구한 후, 다익스트라 lst를 다 더해 최솟값을 찾는 방법
 * 2. 플로이드워셜을 사용하는 방법
 * 
 * 방금 배운 2번을 활용해보자!
 * 가중치는 전부 1이라고 가정하자. 
 */

public class _01_1263_사람네트워크2_SWEA {
	
	static int N;
	static int board[][]; // y= 출발지, x= 도착지
	
	static void floyd() {
		for(int i=1; i<N+1; i++) { // 경유지
			for(int j=1; j<N+1; j++) { // 출발지
				for(int k=1; k<N+1; k++) { // 도착지
					board[j][k]= Math.min(board[j][k], board[j][i]+board[i][k]);
				}
			}
		}
	}
	
	static int getResult() {
		int sum_= 2147483647;
		for(int i=1; i<N+1; i++) {
			int temp= 0;
			for(int j=1; j<N+1; j++) {
				temp+=board[i][j];
			}
			if(temp<sum_) {
				sum_= temp;
			}
		}
		
		return sum_;
	}
	
	static int solution() {
		// 1. 플로이드 워셜
		floyd();
		
//		for(int i=1; i<N+1; i++) { // 경유지
//			for(int j=1; j<N+1; j++) { // 출발지
//				System.out.print(board[i][j]+ " ");
//			}
//			System.out.println();
//		}
		
		// 2. 결과 구하기(행이나 열의 합을 구한 후 가장 작은 값을 가진 index 값을 반환하면 된다.)
		return getResult();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			st= new StringTokenizer(br.readLine());
			
			N= Integer.parseInt(st.nextToken());
			board= new int[N+1][N+1];
			
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<N+1; j++) {
					int temp= Integer.parseInt(st.nextToken());
					if(temp==0) {
						board[i][j]= 1000000000;
					}else {
						board[i][j]= temp;
					}
				}
				board[i][i]= 0;
			}
			
			System.out.println("#"+tc+" "+solution());
			// solution();
		}

	}

}
