package _250813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * k위치가 정상.
 * k위치를 기준으로 왼쪽 오른쪽 몇 개까지 되는지 while문 두 개를 돌린다
 * 왼쪽 2개, 오른쪽 2개라면 2*2
 */

public class _03_4796_의석이의우뚝선산 {
	
	static int N;
	static int[] arr;
	static int cnt;
	
	static void solution() {
		
		for(int k=1; k<N-1; k++) {
			int current= k;
			int left_cnt= 0;
			int right_cnt= 0;
			
			// 왼쪽
			while(0<=current-1 && arr[current-1]<arr[current]) {
				left_cnt+=1;
				current-=1;
			}
			if(left_cnt==0) {
				continue;
			}
			
			current= k;
			
			// 오른쪽
			while(current+1<N && arr[current+1]<arr[current]) {
				right_cnt+=1;
				current+=1;
			}
			if(right_cnt==0) {
				continue;
			}

			cnt+=(left_cnt*right_cnt);
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		for(int i=1; i<T+1; i++) {
			N= Integer.parseInt(br.readLine());
			arr= new int[N];
			cnt= 0;
			
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[j]= Integer.parseInt(st.nextToken());
			}
			

			solution();
				
			System.out.println("#" + i + " " + cnt);
			
			
			
		}

	}

}
