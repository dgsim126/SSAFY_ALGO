package _250902_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dp문제에서 중요한 건 점화식(규칙을 찾아내는 것이다)
 * dp[n]= dp[n-1]+dp[n-2]+dp[n-3]
 */

public class _01_01_9095_123더하기_BOJ {
	
	static int dp(int n) {
		if(n==1) {
			return 1;
		}
		if(n==2) {
			return 2;
		}
		if(n==3) {
			return 4;
		}
		
		return dp(n-1)+dp(n-2)+dp(n-3);
	}
	
	static void solution(int n) {
		
		int result= dp(n);
		System.out.println(result);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			int n= Integer.parseInt(br.readLine());
			solution(n);
		}
	}

}
