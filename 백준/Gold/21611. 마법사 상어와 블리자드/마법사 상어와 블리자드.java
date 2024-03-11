import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] ans = new int[4];
    public static void main(String[] args) throws IOException {

        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sx = N/2, sy = N/2;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            int cnt = 0;
            for(int j=1; j<=s; j++){
                int nx = sx + dx[d]*j;
                int ny = sy + dy[d]*j;

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) break;
                // 구슬 파괴
                map[nx][ny] = 0;
                cnt++;
            }

            // 구슬 이동
            while(cnt-- > 0){
                moveBalls(sx, sy);
            }

            while(true){
                // 폭발 없을때까지
                // 4개이상 구슬 폭발 (폭발 개수 저장)
                int bombCnt = bomb(sx, sy);
                if(bombCnt == 0) break;

                // 구슬 이동
                while(bombCnt-- > 0){
                    moveBalls(sx, sy);
                }
            }

            // 구슬 변화
            // 그룹으로 묶고
            // 구슬 A : 그룹의 구슬 개수
            // 구슬 B : 그룹의 구슬 번호
            changeBalls(sx, sy);
        }

        System.out.println(ans[1] + ans[2] * 2 + ans[3] * 3);

    }

    public static void changeBalls(int sx, int sy){
        int x = sx, y = sy-1;
        int d = 1; // 상하좌우
        // 좌하우상
        int[] dir = {2, 1, 3, 0};
        int len = 1;
        int item = map[x][y];
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Point> temp = new ArrayList<>();
        temp.add(new Point(x, y));

        while (x != 0 || y != 0) {
            for (int i = 1; i <= len; i++) {
                if(x == 0 && y == 0) break;
                x = x + dx[dir[d]];
                y = y + dy[dir[d]];

                if(map[x][y] == item){
                    temp.add(new Point(x, y));
                }else{
                    list.add(temp.size());
                    list.add(map[temp.get(0).x][temp.get(0).y]);

                    item = map[x][y];
                    temp.clear();
                    temp.add(new Point(x, y));
                }
                
            }
            d = (d+1) % 4;

            if (d == 2 || d == 0) len++;
        }
        if(list.size() == 0) return;
        
        x = sx; y = sy-1;
        d = 1;
        len = 1;
        int idx = 0;
        while (x != 0 || y != 0) {
            for (int i = 1; i <= len; i++) {
                if(x == 0 && y == 0) break;
                map[x][y] = list.get(idx++);
                if(list.size() == idx) return;
                x = x + dx[dir[d]];
                y = y + dy[dir[d]];
            }
            d = (d+1) % 4;

            if (d == 2 || d == 0) len++;
        }
    }

    public static int bomb(int sx, int sy){
        int x = sx, y = sy-1;
        int d = 1; // 상하좌우
        // 좌하우상
        int[] dir = {2, 1, 3, 0};
        int len = 1;
        int cnt = 0;
        int item = map[x][y];
        ArrayList<Point> removeList = new ArrayList<>();
        removeList.add(new Point(x, y));
        while (x != 0 || y != 0) {

            for (int i = 1; i <= len; i++) {
                if(x == 0 && y == 0) break;
                x = x + dx[dir[d]];
                y = y + dy[dir[d]];

                if(map[x][y] == item){
                    removeList.add(new Point(x, y));
                }else{
                    if (removeList.size() >= 4) {
                        cnt += removeList.size();
                        for(Point p : removeList){
                            ans[map[p.x][p.y]]++;
                            map[p.x][p.y] = 0;
                        }
                    }
                    removeList.clear();
                    removeList.add(new Point(x, y));
                    item = map[x][y];
                }
            }
            d = (d+1) % 4;

            if (d == 2 || d == 0) len++;
        }

        return cnt;
    }

    public static void moveBalls(int sx, int sy){
        int x = sx, y = sy-1;
        int d = 1; // 상하좌우
        // 좌하우상
        int[] dir = {2, 1, 3, 0};
        int len = 1;
        while (x != 0 || y != 0) {

            for (int i = 1; i <= len; i++) {
                if(x == 0 && y == 0) break;
                if(map[x][y] == 0){
                    map[x][y] = map[x+dx[dir[d]]][y+dy[dir[d]]];
                    map[x+dx[dir[d]]][y+dy[dir[d]]] = 0;
                }
                x = x + dx[dir[d]];
                y = y + dy[dir[d]];
            }
            d = (d+1) % 4;

            if (d == 2 || d == 0) len++;
        }
    }

    public static void print(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
