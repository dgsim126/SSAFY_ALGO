package _250909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _03_12015_가장긴증가하는부분수열2_BOJ {
	
	static int N;
	static int height[];
	static int dp[];
	static int dp_len;
	
	/**
	 * dp에서 current가 위치할 곳을 찾으면 된다.
	 * current는 본인보다 큰 값이 나오는 인덱스에 들어가야 한다.(대체)
	 * @param current= 현재 height
	 */
	static void findDP(int current) {
		int start= 0;
		int end= dp_len-1;
		
		while(start<=end) {
			int mid= (start+end)/2;
			
			if(dp[mid]>=current) {
				end= mid-1;
				continue;
			}
			
			if(dp[mid]<current) {
				start= mid+1;
				continue;
			}
		}
		
		
		dp[start]= current;
		
		if(start==dp_len) {
			dp_len+=1;
		}
	}
	
	static void solution() {
		for(int i=0; i<N; i++) {
			findDP(height[i]);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N= Integer.parseInt(br.readLine());
		height= new int[N];
		dp= new int[N];
		dp_len= 0;
		
		st= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			height[i]= Integer.parseInt(st.nextToken());
		}
		
		solution();
		System.out.println(dp_len);
	}

}
