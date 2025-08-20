//package _250819;
//
//import java.io.*;
//import java.util.*;
//
//public class _01_5644_무선충전 {
//
//    static int M; // 총 이동시간
//    static int A; // BC 개수
//    static int[] moveA;
//    static int[] moveB;
//    static BC[] bcList;
//
//    // 사용자 A, B의 위치
//    static int ax,ay;
//    static int bx,by;
//
//    // 이동 방향 (0:이동X, 1:상, 2:우, 3:하, 4:좌)
//    static int[] dx={0,0,1,0,-1};
//    static int[] dy={0,-1,0,1,0};
//
//    static class BC {
//        int x,y,c,p;
//        BC(int x,int y,int c,int p){
//            this.x=x;
//            this.y=y;
//            this.c=c;
//            this.p=p;
//        }
//    }
//
//    static int getDist(int x1,int y1,int x2,int y2){
//        return Math.abs(x1-x2)+Math.abs(y1-y2);
//    }
//
//    static int getMaxCharge(){
//        // 각 사용자가 접속 가능한 BC 후보 구하기
//        List<Integer> listA=new ArrayList<>();
//        List<Integer> listB=new ArrayList<>();
//        for(int i=0;i<A;i++){
//            if(getDist(ax,ay,bcList[i].x,bcList[i].y)<=bcList[i].c){
//                listA.add(i);
//            }
//            if(getDist(bx,by,bcList[i].x,bcList[i].y)<=bcList[i].c){
//                listB.add(i);
//            }
//        }
//
//        int max=0;
//        if(listA.isEmpty()&&listB.isEmpty()){
//            return 0;
//        }else if(listA.isEmpty()){
//            for(int b:listB){
//                max=Math.max(max,bcList[b].p);
//            }
//        }else if(listB.isEmpty()){
//            for(int a:listA){
//                max=Math.max(max,bcList[a].p);
//            }
//        }else{
//            for(int a:listA){
//                for(int b:listB){
//                    if(a==b){
//                        max=Math.max(max,bcList[a].p/2+bcList[b].p/2);
//                    }else{
//                        max=Math.max(max,bcList[a].p+bcList[b].p);
//                    }
//                }
//            }
//        }
//        return max;
//    }
//
//    static int solution(){
//        int total=0;
//        // 초기 위치
//        ax=1; ay=1;
//        bx=10; by=10;
//
//        // 0초부터 충전
//        total+=getMaxCharge();
//
//        for(int t=0;t<M;t++){
//            ax+=dx[moveA[t]];
//            ay+=dy[moveA[t]];
//            bx+=dx[moveB[t]];
//            by+=dy[moveB[t]];
//            total+=getMaxCharge();
//        }
//        return total;
//    }
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        int T=Integer.parseInt(br.readLine());
//        for(int tc=1;tc<=T;tc++){
//            st=new StringTokenizer(br.readLine());
//            M=Integer.parseInt(st.nextToken());
//            A=Integer.parseInt(st.nextToken());
//            moveA=new int[M];
//            moveB=new int[M];
//
//            st=new StringTokenizer(br.readLine());
//            for(int i=0;i<M;i++){
//                moveA[i]=Integer.parseInt(st.nextToken());
//            }
//            st=new StringTokenizer(br.readLine());
//            for(int i=0;i<M;i++){
//                moveB[i]=Integer.parseInt(st.nextToken());
//            }
//
//            bcList=new BC[A];
//            for(int i=0;i<A;i++){
//                st=new StringTokenizer(br.readLine());
//                int x=Integer.parseInt(st.nextToken());
//                int y=Integer.parseInt(st.nextToken());
//                int c=Integer.parseInt(st.nextToken());
//                int p=Integer.parseInt(st.nextToken());
//                bcList[i]=new BC(x,y,c,p);
//            }
//
//            int result=solution();
//            System.out.println("#"+tc+" "+result);
//        }
//    }
//}
