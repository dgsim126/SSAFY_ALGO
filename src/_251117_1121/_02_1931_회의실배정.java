package _251117_1121;

import java.io.*;
import java.util.*;

/**
 * 1. (시작시간, 종료시간)으로 이루어진 회의들을 종료시간을 기준으로 오름차순 정렬한다.
 * 2. 맨 앞의 회의를 우선 선택한다.
 * 3. 그 다음 회의를 선택할 때는 이전 회의의 종료시간 이후의 시작시간을 가진 회의를 선택하면 된다.
 */

public class _02_1931_회의실배정 {
	
	static int N;
	static int arr[][];
	static int result;
	
	static void solution() {
		
		// 종료시간 기준 오름차순 정렬
		Arrays.sort(arr, (a, b) -> {
			if(a[1]==b[1]) {
				return a[0]-b[0];
			}else {
				return a[1]-b[1];
			}
		});
		
		int before_endTime= -1;
		
		for(int i=0; i<N; i++) {
			if(before_endTime<=arr[i][0]) {
				before_endTime= arr[i][1];
				result+=1;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N= Integer.parseInt(br.readLine());
		arr= new int[N][2];
		result= 0;
		
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			arr[i][0]= Integer.parseInt(st.nextToken());
			arr[i][1]= Integer.parseInt(st.nextToken());
		}
		
		solution();
		
		System.out.println(result);
	}

}
