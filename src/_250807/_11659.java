package _250807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 매번 범위만큼 더하는 것은 1억번 연산 내 불가
 * 각 위치에 더한 값을 저장할 것
 * 만약 1, 3 범위 합을 구할 때는 arr[3]-arr[0] 이런 느낌>??
 */

public class _11659 {
	
	static int N;
	static int M;
	static int[] arr;
	
	static void cal() {
		for(int i=1; i<N; i++) {
			arr[i]= arr[i]+arr[i-1];
		}
		
	}
	
	static void solution(int start, int end) {
		if(start==1) {
			System.out.println(arr[end-1]);
		}else {
			System.out.println(arr[end-1]-arr[start-2]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		arr= new int[N];
		
		st= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}
		
		// 누적합 배열 계산
		cal();
		
		// 범위별 결과 계산
		for(int i=0; i<M; i++) {
			st= new StringTokenizer(br.readLine());
			int start= Integer.parseInt(st.nextToken());
			int end= Integer.parseInt(st.nextToken());
			solution(start, end);
		}

	}

}
