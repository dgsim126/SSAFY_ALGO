package _250820_Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node{
    List<int[]> connected= new ArrayList<>(); // {연결된 노드 번호, 거리}
}

public class _01_1753_최단경로_BOJ_ver2 {
    
    static int V; // 정점의 개수
    static int E; // 간선의 개수
    static int start_node;
    static Node[] node;
    static int[] visited;
    static int[] weight;
    static final int INF = 2147483647;

    // 기존 선형 탐색 함수는 더 이상 사용하지 않지만, 구조 최소 변경을 위해 남겨둠
    static int toFindMinWeightNode() {
        int min_= INF;
        int index= -1;
        for(int i=1; i<V+1; i++) {
            if(visited[i]!=1) {
                if(weight[i]<min_) {
                    min_= weight[i];
                    index= i;
                }
            }
        }
        return index;
    }
    
    static void solution() {
        // 1) 시작 노드 세팅
        weight[start_node]= 0; 

        // 2) 우선순위 큐 기반 다익스트라 (거리 오름차순)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, start_node}); // {현재거리, 노드}

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0];
            int u = cur[1];

            // 이미 최단거리로 확정된 정점이면 스킵
            if (visited[u] == 1) continue;
            visited[u] = 1;

            // 인접 정점 완화
            for (int[] n : node[u].connected) {
                int to = n[0];
                int dist = n[1];

                long cand = (long)d + dist; // 오버플로 방지용 long 비교
                if (cand < weight[to]) {
                    weight[to] = (int)cand;
                    pq.offer(new int[]{weight[to], to});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        
        V= Integer.parseInt(st.nextToken());
        E= Integer.parseInt(st.nextToken());
        start_node= Integer.parseInt(br.readLine());
        
        node= new Node[V+1]; // 0번 인덱스 사용하지 않음
        for(int i=0; i<V+1; i++) node[i]= new Node();
        
        visited= new int[V+1];
        weight= new int[V+1];
        Arrays.fill(weight, INF);
        
        for(int i=0; i<E; i++) {
            st= new StringTokenizer(br.readLine());
            int from= Integer.parseInt(st.nextToken());
            int to= Integer.parseInt(st.nextToken());
            int w= Integer.parseInt(st.nextToken());
            node[from].connected.add(new int[] {to, w});
        }
        
        solution();
        
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<V+1; i++) {
            if(weight[i]==INF) sb.append("INF\n");
            else sb.append(weight[i]).append('\n');
        }
        System.out.print(sb.toString());
    }
}
