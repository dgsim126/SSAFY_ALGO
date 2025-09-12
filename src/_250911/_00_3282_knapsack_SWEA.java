package _250911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _00_3282_knapsack_SWEA {
	
	static int N;
	static int K;
	static int point[][]; // []{부피, 가치}
	static int board[][]; // y= 물건 인덱스, x= 현재의 최대 가방 크기
	
	static int solution() {
		
		// dp 계산
		for(int y=1; y<N+1; y++) {
			for(int x=1; x<K+1; x++) {
				// 1. 현재 가방의 최대 부피보다 현재 넣으려는 물건의 부피가 같거나 작은지를 확인해야한다.
				int left= x-point[y][0];
				
				if(left>=0) { // 현재 물건을 넣는 경우를 고려해볼 수 있다.
					board[y][x]= Math.max(board[y-1][x], (point[y][1]+board[y-1][left])); // 물건을 넣지 않는다. vs 물건을 넣는다.
				}else { // 현재 물건을 넣는 경우를 고려해볼 수 없다. 
					board[y][x]= board[y-1][x];
				}
			}
		}
		
		// 결과 계산
		int result= 0;
		for(int i=1; i<K+1; i++) {
			if(board[N][i]>result) {
				result= board[N][i];
			}
		}
		
		return result;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			st= new StringTokenizer(br.readLine());
			
			N= Integer.parseInt(st.nextToken());
			K= Integer.parseInt(st.nextToken());
			
			point= new int[N+1][2];
			board= new int[N+1][K+1];
			
			
			for(int i=1; i<N+1; i++) {
				st= new StringTokenizer(br.readLine());
				point[i][0]= Integer.parseInt(st.nextToken());
				point[i][1]= Integer.parseInt(st.nextToken());
			}
			
			
			System.out.println("#" + tc + " " + solution());
		}

	}

}
