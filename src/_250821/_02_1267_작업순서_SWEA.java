package _250821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Node들은 본인이 화살표로 가리키는 Node들을 저장하는 ArrayList를 가진다.
 * 
 * 또한, 본인을 가리키고 있는 Node들의 개수가 몇 개인지를 가지고 있는 배열도 하나 생성한다.(lst)
 * 
 * 1. lst에서 0인 값을 찾는다.
 * 2. 해당 Node 인덱스를 큐에 넣는다.
 * 2. 해당 Node로 접속하여 ArrayList를 돌며, 해당 Node의 인덱스를 구하고 lst[index]-=1를 한다.
 * 4. 해당 Node 인덱스를 결과 리스트에 넣는다.
 * 
 * 
 */

public class _02_1267_작업순서_SWEA {
	
	static class Node{
		List<Integer> connected= new ArrayList<>();
	}
	
	static int V;
	static int E;
	
	static Node[] node;
	static int intro[];
	
	static int toFindIntroValIsZero() {
		for(int i=1; i<V+1; i++) {
			if(intro[i]==0) {
				intro[i]= -1;
				return i;
			}
		}
		
		return -1;
	}
	
	static void solution() {
		
		Queue<Integer> queue= new LinkedList<>();
		Queue<Integer> result= new LinkedList<>();
		
		for(int i=0; i<V; i++) { // 노드의 개수만큼
			int index= toFindIntroValIsZero();
			
			queue.offer(index);
			
			while(!queue.isEmpty()){
				int current= queue.poll();
				
				for(int n : node[current].connected) {
					intro[n]-=1;
				}
			}
			
			result.add(index);
		}
		
		while(!result.isEmpty()) {
			System.out.print(result.poll() + " ");
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t=1; t<11; t++) {
			st= new StringTokenizer(br.readLine());
			V= Integer.parseInt(st.nextToken());
			E= Integer.parseInt(st.nextToken());
			
			
			node= new Node[V+1];
			intro= new int[V+1];
			
			for(int i=0; i<V+1; i++) {
				node[i]= new Node();
			}
			
			st= new StringTokenizer(br.readLine());
			for(int i=0; i<E; i++) {
				int from= Integer.parseInt(st.nextToken());
				int to= Integer.parseInt(st.nextToken());
				
				node[from].connected.add(to);
				intro[to]+=1;
			}
			System.out.print("#" + t + " ");
			solution();
			
			System.out.println();
		}
		
		
	}

}
