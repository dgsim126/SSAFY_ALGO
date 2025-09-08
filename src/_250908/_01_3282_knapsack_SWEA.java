package _250908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _01_3282_knapsack_SWEA {
	
	static int N; // 물건의 개수
	static int K; // 선택한 물건들의 부피합이 K이하
	static int object[][]; // 물건 별 부피와 가치 (부피, 가치)
	
	static int solution() {
		// dp[N(물건의 개수)][K(부피최대)]인 배열 초기화 
		
		// 현 위치에서 나는 (x축이 하나 작은 값==물건을 가방에 넣지 않음) vs (현재 x축(부피최대)-지금 들어올 물건 부피 = 에 해당하는 인덱스 위치의 가치 + 물건의 가치)
		
		// 어떤 순서로 순회하면 되는가? -> 그냥 정 방향으로 하면 된다.
		
		int dp[][]= new int[N+1][K+1];
		
		for(int y=1; y<N+1; y++) { // N(현재 물건)
			for(int x=1; x<K+1; x++) { // K(부피 최대)
				int left_space= x-object[y][0];
				
				if(left_space>=0) {
					dp[y][x]= Math.max(dp[y-1][x], dp[y-1][left_space]+object[y][1]);
				}else {
					dp[y][x]= dp[y-1][x];
				}
			}
		}
		
		return dp[N][K];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			st= new StringTokenizer(br.readLine()); 
			
			N= Integer.parseInt(st.nextToken());
			K= Integer.parseInt(st.nextToken());
			object= new int[N+1][2];
			
			for(int i=1; i<N+1; i++) {
				st= new StringTokenizer(br.readLine()); 
				object[i][0]= Integer.parseInt(st.nextToken());
				object[i][1]= Integer.parseInt(st.nextToken());
			}
			
			int result= solution();
			System.out.println("#"+tc+" "+result);
		}

	}

}
