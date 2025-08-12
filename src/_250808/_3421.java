package _250808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 재료 1~N
 * 
 */
public class _3421 {
	
	static int T;
	static int N;
	static int M;
	static int ingre[][];
	
	static void solution() {
		
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T= Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			st= new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken());
			M= Integer.parseInt(st.nextToken());
			ingre= new int[M][2];
			
			for(int j=0; j<M; j++) {
				st= new StringTokenizer(br.readLine());
				ingre[j][0]= Integer.parseInt(st.nextToken());
				ingre[j][1]= Integer.parseInt(st.nextToken());
			}
			
			solution();
		}
		

	}

}
