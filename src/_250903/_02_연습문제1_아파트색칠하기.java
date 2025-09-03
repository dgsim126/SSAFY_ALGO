package _250903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * f(1)=2
 * f(2)=3
 * f(3)=5
 * f(4)=8
 * f(5)=13
 * f(6)=30
 * ==> f(n)=f(n-1)+f(n-2)
 */
public class _02_연습문제1_아파트색칠하기 {
	
	static int dp[];
	
	static int solution(int n) {
		dp[1]= 2;
		dp[2]= 3;
		
		for(int i=3; i<n+1; i++) {
			dp[i]= dp[i-1]+dp[i-2];
		}
		
		return dp[n];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int n= Integer.parseInt(br.readLine());
		
		dp= new int[n+1];
		
		System.out.println(solution(n));
		
	}

}
