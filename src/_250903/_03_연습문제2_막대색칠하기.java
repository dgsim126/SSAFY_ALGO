package _250903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1cm 파란 막대
 * 1cm 노란 막대
 * 2cm 빨간 막대
 * 
 * 이 막대를 연결하여 길이가 n인 막대를 만드는 방법의 수
 * 
 * f(1)=2
 * f(2)=5
 * f(3)=12
 * f(4)=29
 * f(5)=70
 */
public class _03_연습문제2_막대색칠하기 {

static int dp[];
	
	static int solution(int n) {
		dp[1]= 2;
		dp[2]= 5;
		
		for(int i=3; i<n+1; i++) {
			dp[i]= (dp[i-1]*2)+dp[i-2];
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