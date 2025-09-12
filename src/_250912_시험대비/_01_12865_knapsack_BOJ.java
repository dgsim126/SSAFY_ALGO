package _250912_시험대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * knapsack문제
 * 
 * 각각의 물건을 포함하는 경우와 포함하지 않는 경우로 나뉘어진다.
 * y축을 물건, x축을 가방의 최대무게로 봤을 때, 특정 위치에서 각각의 값은
 * 선택하지 않는 경우(y-1)위치의 가치 vs 현재 [y-1][최대무게-지금 넣으려는 가방의 무게] 비교하여 더 큰 값을 넣으면 된다.
 */

public class _01_12865_knapsack_BOJ {
	
	static int N; // 물건의 개수
	static int K; // 최대 무게
	static int product[][]; // {무게, 가치}
	static int dp[];
	
	static void solution() {
		for(int y=1; y<N+1; y++) {
			for(int x=K; x>0; x--) {
				int index= x-product[y][0];
				if(index>=0) { // 현재 물건을 넣을 수 있다. 다만, 넣는 것과 넣지 않는 것 중 최적은 내가 판단
					dp[x]= Math.max(dp[index]+product[y][1], dp[x]); // 넣는 경우의 가치, 넣지 않는 경우의 가치
				}
			}
		}
		
		// System.out.println(Arrays.toString(dp));
		
		// 결과 도출
		int max_= 0;
		for(int i=0; i<dp.length; i++) {
			if(dp[i]>max_) {
				max_= dp[i];
			}
		}
		
		System.out.println(max_);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N= Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken());
		
		product= new int[N+1][2]; 
		dp= new int[K+1];
		
		for(int i=1; i<N+1; i++) {
			st= new StringTokenizer(br.readLine());
			product[i][0]= Integer.parseInt(st.nextToken());
			product[i][1]= Integer.parseInt(st.nextToken());
		}
		
		solution();
	}

}
