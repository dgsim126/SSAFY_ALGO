package _250902_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2xN 크기의 직사각형을 (1x2)(2x1)의 직사각형으로 채우는 방법의 수를 구하라
 * 
 * 2x1= 1
 * 2x2= 2
 * 2x3= 3
 * 2x4= 5
 * 2x5= 8
 * 2x6= 13
 * dp(n)= dp(n-1)+dp(n-2)
 * 
 */

public class _01_03_11726_2xn타일링_BOJ {
	
	static int solution(int n) {
		int[] dp= new int[n+1];
		
		if(n==1) {
			return 1;
		}
		if(n==2) {
			return 2;
		}
		
		dp[1]= 1;
		dp[2]= 2;
		
		for(int i=3; i<n+1; i++) {
			dp[i]= (dp[i-1]+dp[i-2])%10007;
		}
		
		return dp[n];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int n= Integer.parseInt(br.readLine());
		
		System.out.println(solution(n));
	}

}
