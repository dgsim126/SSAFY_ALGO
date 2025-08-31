package _250831_시험대비_서로소집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * n= 주어지는 숫자
 * m= 입력으로 주어지는 연산의 개수
 * 
 * 합집합(0) a집합과 b집합을 합친다는 의미
 * 같은 집합인지 확인(1) a집합과 b집합이 같은 집합인지
 * 
 * parent[]만 잘 관리하면 된다.
 * 두 집합이 같은 집합인지를 확인할 때는 parent[a], parent[b]를 타고 올라간 루트를 확인하면 된다.
 * 두 집합을 합칠 때는 parnet[a], parent[b]의 루트까지 올라간 후 더 작은 숫자가 루트가 되도록 관계를 설정해주면 된다.
 * 
 */

public class _01_3289_서로소집합_SWEA {
	
	static int N;
	static int M;
	static int order[][]; // 명령어 저장 (명령어, a, b)
	static int parent[]; // 각각의 노드의 부모를 저장하는 배열
	
	/**
	 * a의 루트 parent를 리턴한다.
	 * @param a
	 * @return a의 루트 parent
	 */
	static int findRootVer1(int a) {
		while(parent[a]!=a) {
			a= parent[a];
		}
		
		return a;
	}
	
	static int findRoot(int a) {
		if(parent[a]==a) {
			return a;
		}
		
		return parent[a]= findRoot(parent[a]); // 경로 압축
	}
	
	
	
	/**
	 * a와 b의 루트 parent가 동일한지 확인하면 된다.
	 * @param a
	 * @param b
	 * @return 1(루트가 같다) or 1(루트가 다르다)
	 */
	static int find(int a, int b) {
		a= findRoot(a);
		b= findRoot(b);
		
		if(a==b) {
			return 1;
		}else {
			return 0;
		}
	}
	
	/**
	 * 두 개의 최상단 루트를 구한 후, 더 작은 값이 큰 값의 부모가 되도록 설정한다.
	 * @param a
	 * @param b
	 */
	static void union(int a, int b) {
		a= findRoot(a);
		b= findRoot(b);
		
		if(a>b) {
			parent[a]= b;
		}else {
			parent[b]= a;
		}
	}
	
	static void solution() {
		
		for(int i=0; i<M; i++) {
			int oper= order[i][0]; // 0 or 1
			int a= order[i][1];
			int b= order[i][2];
			
			if(oper==0) { // a, b를 합치는 함수 호출
				union(a, b);
			}else { // a, b가 같은 집합에 속하는지 확인하는 함수 호출
				System.out.print(find(a, b));
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			st= new StringTokenizer(br.readLine());
			
			N= Integer.parseInt(st.nextToken());
			M= Integer.parseInt(st.nextToken());
			
			parent= new int[N+1]; // 0 제외
			order= new int[M][3];
			
			// parent 초기화(처음엔 모든 부모가 자기 자신)
			for(int i=1; i<N+1; i++) {
				parent[i]= i;
			}
			
			for(int i=0; i<M; i++) {
				st= new StringTokenizer(br.readLine());
				order[i][0]= Integer.parseInt(st.nextToken());
				order[i][1]= Integer.parseInt(st.nextToken());
				order[i][2]= Integer.parseInt(st.nextToken());
			}
			
			System.out.print("#" + tc+ " ");
			solution();
			System.out.println();
		}

	}

}
