package _250914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * for문을 돌면서 현재 위치를 dp[]에서 나보다 큰 값 바로 앞에서 변경하면 된다.
 */

public class _04_3307_LIS_SWEA {
	
	static int N;
	static int size;
	static int dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			N= Integer.parseInt(br.readLine());
			dp= new int[N];
			size= 0;
			
			st= new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				int current= Integer.parseInt(st.nextToken());
				
				int position= Arrays.binarySearch(dp, 0, size, current);
				position= -position-1;
				
				if(position>=size) {
					dp[position]= current;
					size+=1;
				}else {
					dp[position]= current;
				}
			}
			
			System.out.println("#" + tc + " " + size);
		}
		
		
		
	}

}
