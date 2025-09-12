package _250911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 내게 주어진 숫자들을 이용해 가장 가까운 숫자를 만들어야 한다.
 * 
 * 배열에는 내가 이용할 수 있는 숫자들이 존재한다.
 * 만들 수 있는 모든 순열을 만들고, 내 목표 값과의 차를 구하면 될 것 같다.
 * 
 * 만약 현재 채널이 5자리라면 4자리, 5자리, 6자리 모두를 만들어봐야한다.
 * 이거 시간 못 줄이나?
 * 
 * 
 */

public class _03_1107_리모컨_1107_BOJ {
	
	static int N;
	static int N_size;
	static int M;
	static Set<Integer> set;
	static int board[];
	
	static int current[];
	
	static int result;
	
	static void check(int len) {
		if(len<=0) {
			return;
		}
		
		String temp= "";
		for(int i=0; i<len; i++) {
			temp+=current[i];
		}
		
	
		int value= Integer.parseInt(temp);
	    int temp_result= Math.abs(value-N) + len;

	    if(result>temp_result) {
	        result= temp_result;
	    }
	}
	
	static void dfs(int depth) {
		if(depth==N_size-1) {
			check(N_size-1);
		}else if(depth==N_size) {
			check(N_size);
		}else if(depth==N_size+1) {
			check(N_size+1);
			return;
		}
		
		for(int i=0; i<10-M; i++) {
			current[depth]= board[i];
			
			dfs(depth+1);
			
			current[depth]= 0;
		}
		
		
	}
	
	static int solution() {
		// 기본값
	    result= Math.abs(N-100);

	    // 모든 버튼 고장 
	    if(M==10) {			
	    	return result;
	    }
		
		board= new int[10-M]; // 현재 사용 가능한 버튼들이 담겨있는 배열
		current= new int[N_size+1];
		int index= 0;
		
		for(int i=0; i<10; i++) {
			if(!set.contains(i)) {
				board[index]= i;
				index+=1;
			}
		}
		
		dfs(0);
		
		return result;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N= Integer.parseInt(br.readLine());
		M= Integer.parseInt(br.readLine());
		set= new HashSet<>();
		result= 2147483647;
		
		String temp= String.valueOf(N);
		N_size= temp.length();
		
		 if(M>0) {               
			 st= new StringTokenizer(br.readLine());
		     for(int i=0; i<M; i++) {
		    	 set.add(Integer.parseInt(st.nextToken()));
		     }
		 }

		System.out.println(solution());
	}

}
