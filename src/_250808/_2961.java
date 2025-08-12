package _250808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 모든 부분집합을 다 구하자
 * dfs를 활용
 */

public class _2961 {
	
	static int N;
	static int S[];
	static int B[];
	static int diff_min= 2147483647;
	static int is_used[];
	
	static void dfs(int depth, int proS, int sumB, int count) {
			
		if(count>0) {
			int diff= Math.max(proS, sumB) - Math.min(proS, sumB);
			if(diff_min>diff) {
				diff_min= diff;
			}
		}	
		
		if(depth==N) {
			return;
		}
		
		for(int i= depth; i<N; i++) {	
			dfs(i+1, proS*S[i], sumB+B[i], count+1);	
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N= Integer.parseInt(br.readLine());
		S= new int[N];
		B= new int[N];
		is_used= new int[N];
		
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			S[i]= Integer.parseInt(st.nextToken());
			B[i]= Integer.parseInt(st.nextToken());
		}
		
		if(N==1) {
			diff_min= Math.max(S[0], B[0]) - Math.min(S[0], B[0]);
		}else {
			dfs(0, 1, 0, 0);
		}
		
		
		
		System.out.println(diff_min);
	}

}
