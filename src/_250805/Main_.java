package _250805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 친구, 친구의 친구
 * 주어지는 값은 (a, b) a<b
 * 그렇다면 내 친구들의 수를 구해 배열에 저장하고, 해당 배열에 해당하는 친구들의 값들을 배열에 저장한 후
 * 두 배열을 합쳐 set으로 변경
 */

public class Main_ {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n= Integer.parseInt(br.readLine()); // 동기의 수 n
		int m= Integer.parseInt(br.readLine()); // 친구 관계 리스트 수 m
		
		int a[]= new int[m];
		int b[]= new int[m];
		
		for(int i=0; i<m; i++) {
			st= new StringTokenizer(br.readLine());
			a[i]= Integer.parseInt(st.nextToken());
			b[i]= Integer.parseInt(st.nextToken());
		}
		
		// 내 친구 찾기
		Set<Integer> set= new HashSet<>();
		for(int i=0; i<m; i++) {
			if(a[i]==1) {
				set.add(b[i]);
			}
		}
		
		Set<Integer> set2= new HashSet<>();
		
		// 내 친구의 친구 찾기
		for(int i=0; i<m; i++) {
			if(set.contains(a[i])) {
				set2.add(b[i]);
			}else if(set.contains(b[i])) {
				set2.add(a[i]);
			}
		}
		
		Set<Integer> result= new HashSet<>();
		result.addAll(set);
		result.addAll(set2);
		//System.out.println(result);
		result.remove(1);
		//System.out.println(result);
		System.out.println(result.size());

	}

}
