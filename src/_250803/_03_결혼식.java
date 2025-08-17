package _250803;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 1과 연결된 값들을 저장한다.
 * 그 값들과 연결된 값들을 set에 저장한다.
 * 두 개의 set을 합친다.
 */

public class _03_결혼식 {
	
	static int n;
	static int m;
	static int lst[][];
	static Set<Integer> set1= new HashSet<>();
	static Set<Integer> set2= new HashSet<>();
	static Set<Integer> result= new HashSet<>();	
	static void solution() {
		
		for(int i=0; i<m; i++) {
			if(lst[i][0]==1) {
				set1.add(lst[i][1]);
			}
		}
		
		for(int i=0; i<m; i++) {
			if(set1.contains(lst[i][0])) {
				set2.add(lst[i][1]);
			}else if(set1.contains(lst[i][1])) {
				set2.add(lst[i][0]);
			}
		}
		
		result.addAll(set1);
		result.addAll(set2);
		result.remove(1);
		System.out.println(result.size());
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n= Integer.parseInt(br.readLine());
		m= Integer.parseInt(br.readLine());
		lst= new int[m][2];
		
		for(int i=0; i<m; i++) {
			st= new StringTokenizer(br.readLine());
			lst[i][0]= Integer.parseInt(st.nextToken());
			lst[i][1]= Integer.parseInt(st.nextToken());
		}
		
		solution();

	}

}
