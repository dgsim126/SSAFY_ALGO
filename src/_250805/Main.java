package _250805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static int arr[];
	public static int is_visited[];
	public static int N;
	public static int S;
	public static int result= 0;
	public static int cnt= 0;
	public static int test[]= new int[5];
	
	public static void dfs(int depth, int sum_) {
		if(depth==N) {
			return;
		}
		if(sum_==S) {
			result+=1;
		}
		int end= Math.min(depth+1, N);
		for(int i=depth; i<end; i++) {
			if(is_visited[i]==0) {
				is_visited[i]=1;
				sum_+=arr[i];

				dfs(depth+1, sum_);
				
				is_visited[i]= 0;
				sum_-=arr[i];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N= Integer.parseInt(st.nextToken());
		S= Integer.parseInt(st.nextToken());
		arr= new int[N];
		is_visited= new int[N];
		
		st= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		
		System.out.println(result);
	}

}
