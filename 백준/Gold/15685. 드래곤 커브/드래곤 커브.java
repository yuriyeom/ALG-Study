import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
*
* 0세대 : 길이 1 선분
* 1세대 : 시계방향 90도 회전해서 추가
* 2세대 :  1세대처럼
*
* K세대 : K-1세대 끝점 기준으로 90도 시계 회전
*
* 기존 선분을 그대로 회전시키는거다
*
* */
public class Main {
    static int N;
    static int[][] map;
    static int[] dx = {0, -1, 0, 1}; // 우상좌하
    static int[] dy = {1, 0, -1, 0};
    static int[]  nd = {1, 2, 3, 0}; // 우상좌하의 90도 시계
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[101][101];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken()); // 시작 y
            int x = Integer.parseInt(st.nextToken()); // 시작 x
            int d = Integer.parseInt(st.nextToken()); // 시작 방향
            int g = Integer.parseInt(st.nextToken()); // 세대

            // 0세대
            map[x][y] = 1;
            map[x+dx[d]][y+dy[d]] = 1;

            // K-1세대까지의 방향을 저장하는 스택
            Stack<Integer> dirStack = new Stack<>();
            dirStack.add(d);

            // stack에서 pop 후 다시 저장하기 위한 임시 리스트
            ArrayList<Integer> tempList = new ArrayList<>();
            tempList.add(d);

            int ex=x + dx[d], ey=y + dy[d]; // 끝점
            int dir; // 방향
            for(int j=1; j<=g; j++){
                while(!dirStack.isEmpty()){
                    // 가장 최근부터 방향 꺼내오기
                    dir = dirStack.pop();

                    // 회전 방향 결정하고 이동
                    int nnd = nd[dir];
                    ex = ex + dx[nnd];
                    ey = ey + dy[nnd];

                    // 사용한 방향 다시 저장하기 위해 임시 리스트에 저장
                    tempList.add(nnd);

                    // 방문한 곳 표시
                    map[ex][ey] = 1;
                }
                // 임시 리스트에서 방향 스택으로 복사
                dirStack.addAll(tempList);
            }
        }

        System.out.println(countRect());
    }

    public static int countRect(){
        // 2x2 크기의 1로 구성된 배열이 있으면 카운트
        int cnt = 0;
        for(int i=0; i<=99; i++){
            for(int j=0; j<=99; j++){

                boolean isRect = true;
                for(int m=0; m<2; m++){
                    for(int n=0; n<2; n++){
                        if(map[i+m][j+n] == 0){
                            isRect = false;
                            break;
                        }
                    }
                    if(!isRect) break;
                }
                if(isRect) cnt++;
            }
        }

        return cnt;
    }
}
