package _250813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1년 이용권 금액을 넘기는 조합은 잘라낼 것
 * 현재 월에서 3가지 경우를 확인하면 된다.
 * - 1일권으로 해당 월을 넘기는 경우(depth+1)
 * - 한달권으로 해당 월을 넘기는 경우(depth+1)
 * - 3달권으로 3개의 월을 넘기는 경우(depth+3)
 * 
 * depth가 12를 넘기면 stop
 * 1년 이용권 넘기는 조합도 stop
 */

public class _02_1952_수영장 {
	
	static int[] price; // 1일권, 1달, 3달, 1년
	static int[] plan; 
	static int min_price;
	
	static void dfs(int month, int sum_) {
		
		if(sum_>min_price) {
			return;
		}
		
		if(month>12) {
			min_price= sum_;
			return;
		}
		
		// 3달 
		dfs(month+3, sum_+price[2]);
		
		// 1달
		dfs(month+1, sum_+price[1]);
		
		// 1일권으로 1달
		dfs(month+1, sum_+(plan[month]*price[0]));
	}
	
	
	static void solution() {
		dfs(1, 0);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			price= new int[4];
			plan= new int[16];
			
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				price[j]= Integer.parseInt(st.nextToken());
			}
			
			st= new StringTokenizer(br.readLine());
			for(int j=1; j<13; j++) {
				plan[j]= Integer.parseInt(st.nextToken());
			}
			
			min_price= price[3];
			
			solution();
			
			System.out.println("#" + i + " " + min_price);
		}
		

	}

}
