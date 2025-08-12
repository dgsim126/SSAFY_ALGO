package _250807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dfs로 조합을 만들자
 * 만들다가 이미 합이 넘어가면 컷
 */

public class _5215 {
	
	static int N;
	static int L;
	static int[] score;
	static int[] kcal;
	static int[] is_used;
	static int result;
	
	
	static void dfs(int depth, int total_score, int total_kcal) {
		if(total_kcal>L) {
			return;
		}
		if(depth==N) {
			if(result<total_score) {
				result= total_score;
			}
			return;
		}
		if(result<total_score) {
			result= total_score;
		}
		

	    dfs(depth+1, total_score+score[depth], total_kcal+kcal[depth]);

	    dfs(depth+1, total_score, total_kcal);
		
//		for(int i=depth; i<N; i++) {
//			if(is_used[i]==0) {
//				is_used[i]= 1;
//				
//				dfs(i+1, total_score+score[i], total_kcal+kcal[i]);
//				
//				is_used[i]= 0;
//			}
//		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		for(int i=1; i<T+1; i++) {
			st= new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken());
			L= Integer.parseInt(st.nextToken());
			score= new int[N];
			kcal= new int[N];
			is_used= new int[N];
			result= 0;
			
			for(int j=0; j<N; j++) {
				st= new StringTokenizer(br.readLine());
				score[j]= Integer.parseInt(st.nextToken());
				kcal[j]= Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0, 0);
			System.out.println("#" + i +" " + result);
		}
	}

}
