package _1_A형학습_SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.xml.sax.helpers.AttributeListImpl;

/**
 * 선반의 높이 B이상을 조합으로 만들어내면 될 듯
 * 
 */

public class _04_1486_장훈이의높은선반 {
	
	
	static int N;
	static int B;
	static int[] arr;
	static int fit;
	
	
	static void dfs(int index, int sum_) {
		
		int temp= sum_-B;
		if(temp>=0 && temp<fit) {
			fit= temp;
		}
		
		if(index==N) {
			return;
		}
		
		for(int i=index; i<N; i++) {
			
			dfs(i+1, sum_+arr[i]);
			
		}
	}
	
	static int solution() {
		
		dfs(0, 0);
		
		return fit;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		for(int i=1; i<T+1; i++) {
			st= new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken());
			B= Integer.parseInt(st.nextToken());
			arr= new int[N];
			fit= 2147483647;
			
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[j]= Integer.parseInt(st.nextToken());
			}
			
			int result= solution();
			System.out.println("#" + i + " " + result);
		}

	}

}
