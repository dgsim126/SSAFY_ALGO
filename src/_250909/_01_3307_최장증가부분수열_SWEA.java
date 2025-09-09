package _250909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. height에서 인덱스 순서대로 값을 하나 뺸다.
 * 2. dp에 접근하여 적절한 위치에 넣는다.(나보다 큰 값이 나오는 순간의 위치를 대체한다.)
 * 3. dp에 들어간 값의 개수를 측정하면 된다.
 */

public class _01_3307_최장증가부분수열_SWEA {
	
	static int N;
	static int height[];
	static int dp[];
	static int len;
	
	/**
	 * 적절한 위치의 current를 넣으면 된다.
	 * 나보다 큰 인형이 나오는 시점에 해당 인형을 현재의 current로 변경해야한다.(그 앞에는 나보다 작은 값이 있어야 한다.)
	 * N의 크기가 매우 클 가능성이 있으니 이진탐색을 이용하는 것이 바람직하다.
	 * 
	 * @param current= 현재 내가 선택한 인형의 키
	 */
	static void settingDP(int current) {
		
		int start= 0;
		int end= len-1;
		
		while(start<=end) {
			int mid= (start+end)/2;
			
			if(dp[mid]<current) {
				start= mid+1;
			}else {
				end= mid-1;
			}
		}
		
		dp[start]= current;
		if(start==len) {
			len+=1;
		}
		
	}
	
	static void solution() {
		
		for(int i=0; i<N; i++) {
			settingDP(height[i]);  // 현재 빼낸 값의 키
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			N= Integer.parseInt(br.readLine());
			height= new int[N];
			dp= new int[N];
			len= 0;
			
			st= new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				height[i]= Integer.parseInt(st.nextToken());
			}
			
			solution();
			
			System.out.println("#"+tc+" "+len);
		}

	}

}
