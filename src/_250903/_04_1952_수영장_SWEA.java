package _250903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 전에는 현재 월을 기준으로 3가지 경우를 선택하며 다음 dfs를 호출했다.
 * 
 * 이걸 dp로 어떻게 풀지?
 * 매 달마다 최적의 값을 저장하는 메오이제이션을 사용해야 하나? 
 * 
 * 가중치를 저장할 weight를 미리 만든다.
 * 첫 월에 이용 계획이 있다면 1일권 vs 한달권을 비교해 더 작은 값을 넣어놓는다.
 * 
 * 6월이다. (1~5월 weight[]에는 최소가중치가 저장되어 있다고 가정하자)
 * 그럼 나는 다음을 고려하면 된다.
 * 1. weight[3]+ 3달권 금액(4,5,6)
 * 2. weight[5]+ 1달권 금액(6)
 * 3. weight[5]+ 1일권 금액 x 이용계획
 * 
 * 다음과 같이 3개를 구한 후 최소를 weight[6]에 저장하면 된다!!!!!!!!!!!!!
 * 
 * 그렇다면 내가 초기에 구해야 하는 값은 
 * weight[1]-> (1일권x이용계획 vs 한달권) 초기에 설정할 것
 * weight[2]-> 조건 2, 3만 적용
 * weight[3]-> 조건 전부 적용(3은 안 구해도 된다. weight[0]은 0으로 사용하지 않기에 3번 조건 적용 가능
 * 
 * 
 */

public class _04_1952_수영장_SWEA {
	
	static int[] price; // 1일권, 1달권, 3달권, 12달권
	static int[] plan;
	static int[] weight;
	
	static int solution() {
		if(plan[1]!=0) {
			weight[1]= Math.min(plan[1]*price[0], price[1]);
		}
		
		weight[2]= Math.min(weight[1]+price[1], weight[1]+(plan[2]*price[0]));
		
		for(int i=3; i<13; i++) {
			weight[i]= Math.min(weight[i-3]+price[2], weight[i-1]+price[1]);
			weight[i]= Math.min(weight[i], weight[i-1]+plan[i]*price[0]); 
		}
		
		return Math.min(weight[12], price[3]);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			price= new int[4];
			plan= new int[13];
			weight= new int[13];
			
			st= new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				price[i]= Integer.parseInt(st.nextToken());
			}
			
			st= new StringTokenizer(br.readLine());
			for(int i=1; i<13; i++) {
				plan[i]= Integer.parseInt(st.nextToken());
			}
			
			int result= solution();
			
			System.out.println("#"+tc+" "+result);
		}
	}
}
