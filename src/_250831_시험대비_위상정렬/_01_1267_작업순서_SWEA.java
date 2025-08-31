package _250831_시험대비_위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 진입차수만 기억하면 된다.
 * Node class를 만든다.
 * 그리고 각각의 node는 connected[]를 갖는다(현재 노드에서 나가는 방향)
 * 
 * weight[]를 만든다. 특정 node로 화살표가 향한다면, 해당 index를 +1한다.
 * 
 * 1. weight를 돌며 weight[index]==0인 값들을 queue에 넣는다.
 * 2. 큐에서 값을 하나 뺀다(이 시점에 index번호 출력)
 * 3. 해당 노드와 연결된 connected를 찾아 weight를 감소시킨다. (해당 노드는 이미 뺏으니 weight를 -1처리)
 * 4. 만약 더 이상 큐에 값이 없다면 다시 0을 찾는다.
 */

public class _01_1267_작업순서_SWEA {

	static class Node{
		List<Integer> connected= new ArrayList<>();
	}
	
	static int V;
	static int E;
	static Node node[];
	static int weight[]; // 진입차수 기록(나에게로 화살표가 들어오는 경우를 cnt한다.)
	
	static int toFindZero() {
		for(int i=1; i<V+1; i++) {
			if(weight[i]==0) {
				weight[i]= -1;
				return i;
			}
		}
		
		return -1;
	}
	
	static void solution() {
		Queue<Integer> queue= new LinkedList<>();
		
		for(int i=0; i<V; i++) { // 노드의 개수만큼
			int temp= toFindZero();
			
			queue.add(temp);
			
			while(!queue.isEmpty()) {
				int current= queue.poll();
				System.out.print(current+" ");
				
				for(int c : node[current].connected) {
					weight[c]-=1;
				}
			}
			
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= 10;
		
		for(int tc=1; tc<T+1; tc++) {
			st= new StringTokenizer(br.readLine());
			V= Integer.parseInt(st.nextToken());
			E= Integer.parseInt(st.nextToken());
			
			node= new Node[V+1]; // 0번 index 사용x
			weight= new int[V+1];
			
			for(int i=1; i<V+1; i++) {
				node[i]= new Node();
			}
			
			st= new StringTokenizer(br.readLine());
			for(int i=0; i<E; i++) {
				int from= Integer.parseInt(st.nextToken());
				int to= Integer.parseInt(st.nextToken());
				node[from].connected.add(to);
				weight[to]+=1;
			}
			
			System.out.print("#" + tc + " ");
			solution();
			System.out.println();
		}
	}

}
