import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Test {

	public static void main(String[] args) {
		
		/**
		 * ArrayList 배열 만들
		 */
		ArrayList<Integer>[] lst= new ArrayList[3];
		
		for(int i=0; i<3; i++) {
			lst[i]= new ArrayList<>();
		}
		
		lst[1].add(4);
		lst[1].add(5);
		
		System.out.println(lst[0].toString() + lst[1].toString());
		
		/**
		 * Queue에 여러가지값 넣기
		 */
		 Queue<int[]> queue= new LinkedList<>();
		 
		 queue.offer(new int[] {1, 2});
		

	}

}
