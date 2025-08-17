package _250803;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _07_구간합구하기4 {
	
	static int N;
	static int M;
	static int arr[];
	static int request[][];
	
	static void calCum() {
		for(int i=1; i<N+1; i++) {
			arr[i]= arr[i-1]+arr[i];
		}
		
		// System.out.println(Arrays.toString(arr));
	}
	
	static void calBetween(int start, int end) {
		int result= arr[end]-arr[start-1];
		System.out.println(result);
	}
	
	static void solution() {
		calCum();
		
		for(int i=0; i<M; i++) {
			calBetween(request[i][0], request[i][1]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		arr= new int[N+1];
		request= new int[M][2];
		
		st= new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<M; i++) {
			st= new StringTokenizer(br.readLine());
			request[i][0]= Integer.parseInt(st.nextToken());
			request[i][1]= Integer.parseInt(st.nextToken());
		}
		
		solution();
	}

}
