package _250909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 계단은 한 칸 혹은 두 칸을 오를 수 있다.
 * 연속된 3개의 계단을 밟아서는 안된다.(첫 시작점은 무시)
 * 마지막 계단은 반드시 밟아야 한다.
 * 
 * 모든 계단을 다 밟는 경우가 가장 베스트겠지만, 연속으로 3개의 계단을 밟을 수 없다.
 * 
 * 
 */

public class _02_2579_계단오르기_BOJ {
	
	static int N;
	static int stair[];
	static int dp[];
	
	static int solution() {
		if(N==1) {
			return stair[1];
		}
		if(N==2) {
			return stair[1]+stair[2];
		}
		if(N==3) {
			return Math.max(stair[1]+stair[3], stair[2]+stair[3]);
		}
		
		dp[1]= stair[1];
		dp[2]= stair[1]+stair[2];
		dp[3]= Math.max(stair[1]+stair[3], stair[2]+stair[3]);
		
		for(int i=4; i<N+1; i++) {
			dp[i]= Math.max((dp[i-3]+stair[i-1]+stair[i]), (dp[i-2]+stair[i]));
		}
		
		return dp[N];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		N= Integer.parseInt(br.readLine());
		
		stair= new int[N+1];
		dp= new int[N+1];

		for(int i=1; i<N+1; i++) {
			stair[i]= Integer.parseInt(br.readLine());
		}
		
		System.out.println(solution());
	}

}
