package _250914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class _01_12865_knapsack_BOJ {
	
	static int N; // 물품의 수
	static int K; // 최대 무게
	static int product[][]; // [] {각 물건 무게, 각 물건 가치}
	static int dp[][]; // {y: 물건, x: 물건 무게}.
	
	// 현재의 내가 선택되는 경우와 그렇지 않은 경우를 고려한다.
	static void solution() {
		for(int y=1; y<N+1; y++) {
			for(int x=1; x<K+1; x++) {
				int index= x-product[y][0];
				
				if(index>=0) { 
					dp[y][x]= Math.max(dp[y-1][index]+product[y][1], dp[y-1][x]);
				}else {
					dp[y][x]= dp[y-1][x];
				}
			}
		}
		
		int result= 0;
		for(int i=0; i<K+1; i++) {
			if(dp[N][i]>result) {
				result= dp[N][i];
			}
		}
		
		System.out.println(result);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N= Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken());
		
		product= new int[N+1][2];
		dp= new int[N+1][K+1];
		
		
		for(int i=1; i<N+1; i++) {
			st= new StringTokenizer(br.readLine());
			product[i][0]= Integer.parseInt(st.nextToken());
			product[i][1]= Integer.parseInt(st.nextToken());
		}
		
		solution();
	}

}
