package _250912_시험대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 기존에 풀었던 문제와 비슷하다. 그러나 지금의 경우에는 N번쨰에 해당하는 물건의 개수가 무한대라는 점이다.
 * 즉, 1번만 넣는 것이 아니라 무게가 허락한다면 무한히 많은 수를 넣을 수 있다.
 * 
 * 로직은 크게 다르지 않다. 현 최대무게에 맞게 넣을 수 있는 만큼 넣으면 된다.
 * 
 */

public class _02_12920_knapsack_BOJ {
	
	static int N; // 물건의 개수
	static int K; // 최대 무게
	static int product[][]; // {무게, 가치, 물건의 개수}
	static int dp[];
	
	static void solution() {
		for(int y=1; y<N+1; y++) {
			for(int x=K; x>0; x--) {
				int index= x-product[y][0];
				int current_product_cnt= product[y][2];
				int flag= 1;
				
				// 현재 물건을 넣을 수 있는 경우
				while(index>=0 && flag<=current_product_cnt) { 
					dp[x]= Math.max(dp[index]+(product[y][1]*flag), dp[x]); // 넣는 경우와, 넣지 않는 경우를 비교한다
					
					index-=product[y][0];
					flag+=1;
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
		
		product= new int[N+1][3]; 
		dp= new int[K+1];
		
		for(int i=1; i<N+1; i++) {
			st= new StringTokenizer(br.readLine());
			product[i][0]= Integer.parseInt(st.nextToken());
			product[i][1]= Integer.parseInt(st.nextToken());
			product[i][2]= Integer.parseInt(st.nextToken());
		}
		
		// System.out.println(Arrays.deepToString(product));
		
		solution();
	}

}
