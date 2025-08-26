package _250826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 최소간선트리를 구현하는 방법 중 크루스칼 알고리즘을 활용할 예정이다.
 * from, to, length 를 가진 arr를 length를 기준으로 정렬한다.
 * union find를 활용한다.
 * 
 * 모든 섬들간의 거리를 모두 구해야하나?
 * 구할건데 어떻게 구하지?
 * 
 * 
 */

public class _02_1251_하나로_SWEA {
	
	static int N;
	static int[] x_list;
	static int[] y_list;
	static double[][] all; // [from, to, distance]
	static int[] parent;
	
	static void init() {
		all= new double[N*(N-1)/2][3];
		int index=0;
		
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				int dx= x_list[i]-x_list[j];
				int dy= y_list[i]-y_list[j];
				
				all[index][0] = i;   // from
	            all[index][1] = j;   // to
				all[index][2]= Math.sqrt(dx*dx + dy*dy); 
				index+=1;
			}
		}
	}
	
	static void initParent() {
		parent= new int[N];
		
		for(int i=0; i<N; i++) {
			parent[i]= i;
		}
	}
	
	// 가장 상위 루트 찾기 (경로 압축)
	static int find(int x) {
	    if (parent[x] == x) return x;
	    return parent[x] = find(parent[x]);
	}

	// 두 집합 병합 (작은 루트가 상위가 되도록)
	static void union(int a, int b) {
	    int ra = find(a);
	    int rb = find(b);
	    if (ra == rb) return;
	    if (ra < rb) parent[rb] = ra;
	    else parent[ra] = rb;
	}

	
	static void solution() {
		// 받은 값을 가공해서 모든 거리를 다 계산한다. (all 완성)
		init();
		
		// all을 distance가 적은 순서대로 정렬
		Arrays.sort(all, (a,b) -> Double.compare(a[2], b[2]));
		
		// union-find를 응용하기 전 parent 초기화
		initParent();
		
		// 크루스칼
		int taken= 0;
		for(int i=0; i<all.length; i++) {
			int start= (int) all[i][0];
			int end= (int) all[i][1];
			
			if(find(start)!=find(end)) {
				union(start, end);
				taken+=1;
				if(taken==N-1) {
					break;
				}
			}
		}
		
	}
	
 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			N= Integer.parseInt(br.readLine());
			x_list= new int[N];
			y_list= new int[N];
			
			st= new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				x_list[i]= Integer.parseInt(st.nextToken());
			}
			
			st= new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				y_list[i]= Integer.parseInt(st.nextToken());
			}
			
			solution();
		}

	}

}
