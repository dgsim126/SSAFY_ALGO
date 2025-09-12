//package _250911;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class _01_2383_점심식사시간_SWEA {
//
//    static int people_cnt;                
//    static int result;
//
//    // 계단의 위치
//    static int stair_1_y, stair_1_x;
//    static int stair_2_y, stair_2_x;
//
//    // 계단 길이
//    static int stair_1_length;
//    static int stair_2_length;
//
//    // 사람의 위치
//    static List<int[]> people; // {y, x}
//
//    // 계단과 사람 간의 거리(입구 도착까지 시간)
//    static List<Integer> stair1_between_person; // 계단1로 가는 사람들의 도착시간
//    static List<Integer> stair2_between_person; // 계단2로 가는 사람들의 도착시간
//
//    static int cal_distance(int PR, int PC, int SR, int SC) {
//        return Math.abs(PR - SR) + Math.abs(PC - SC);
//    }
//
//    
//    static int simul(List<Integer> arrivals, int stairLen) {
//        if(arrivals.isEmpty()) {		
//        	return 0;
//        }
//
//        List<Integer> a= new ArrayList<>(arrivals);
//        Collections.sort(a);
//
//        int time= 0;
//        int i= 0;                      // 아직 처리하지 않은 도착 인덱스
//        int n= a.size();
//        int finished= 0;
//
//        // 계단에서 내려가는 중인 사람들의 남은 하강시간
//        List<Integer> moving= new ArrayList<>(3);
//
//        // 입구 대기 인원 수
//        int waiting= 0;
//
//        while(finished < n){
//            // 1. 이번 초에 입장 가능 사람들 waiting에 추가 
//            while(i < n && a.get(i)+1 <= time) {
//                waiting++;
//                i++;
//            }
//
//            // 2. 계단 수용 인원(최대 3명)까지 입장
//            while(waiting > 0 && moving.size() < 3) {
//                moving.add(stairLen); // 막 입장한 사람의 남은 하강시간
//                waiting--;
//            }
//
//            // 3. 1초 경과: 내려가는 사람들의 남은 시간 감소
//            for (int k = 0; k < moving.size(); k++) {
//                moving.set(k, moving.get(k)-1);
//            }
//
//            // 4. 이번 초에 내려 끝난 사람들 제거
//            for (int k= moving.size()-1; k>=0; k--) {
//                if (moving.get(k) == 0) {
//                    moving.remove(k);
//                    finished++;
//                }
//            }
//
//            // 5. 한 초 증가
//            time++;
//        }
//        return time;
//    }
//
//
//    static int getTime() {
//        int t1= simul(stair1_between_person, stair_1_length);
//        int t2= simul(stair2_between_person, stair_2_length);
//        return Math.max(t1, t2);
//    }
//
//    static void dfs(int depth) {
//        if (depth==people_cnt) {
//            int temp= getTime();
//            if (temp<result) {
//            	result = temp;
//            }
//            return;
//        }
//
//        int[] p= people.get(depth);
//
//        // 계단 1로 배정
//        int d1= cal_distance(p[0], p[1], stair_1_y, stair_1_x);
//        stair1_between_person.add(d1);
//        dfs(depth+1);
//        stair1_between_person.remove(stair1_between_person.size()-1);
//
//        // 계단 2로 배정
//        int d2= cal_distance(p[0], p[1], stair_2_y, stair_2_x);
//        stair2_between_person.add(d2);
//        dfs(depth+1);
//        stair2_between_person.remove(stair2_between_person.size()-1);
//    }
//
//    static void solution() {
//        stair1_between_person= new ArrayList<>();
//        stair2_between_person= new ArrayList<>();
//        dfs(0);
//    }
//
//    public static void main(String[] args) throws NumberFormatException, IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        int T= Integer.parseInt(br.readLine());
//
//        for(int tc=1; tc<=T; tc++) {
//            int N= Integer.parseInt(br.readLine());
//
//            people= new ArrayList<>();
//            people_cnt= 0;
//            result= Integer.MAX_VALUE;
//
//            int flag= 0; // 0이면 아직 계단1 미배정
//
//            for(int y=0; y<N; y++) {
//                st= new StringTokenizer(br.readLine());
//                for(int x=0; x<N; x++) {
//                    int temp= Integer.parseInt(st.nextToken());
//
//                    if(temp==0) continue;
//
//                    if(temp==1) {
//                        people.add(new int[]{y, x});
//                        people_cnt++;
//                    }else { // 계단(길이 >= 2)
//                        if(flag==0) {
//                            stair_1_length= temp;
//                            stair_1_y= y;
//                            stair_1_x= x;
//                            flag= 1;
//                        } else {
//                            stair_2_length = temp;
//                            stair_2_y = y;
//                            stair_2_x = x;
//                        }
//                    }
//                }
//            }
//
//            solution();
//            System.out.println("#" + tc + " " + result);
//        }
//    }
//}
