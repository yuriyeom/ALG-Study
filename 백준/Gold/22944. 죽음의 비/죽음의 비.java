import java.awt.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int N, H, D, ans;
    static int[][] map, dist;
    static Point U, S, E;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정사각형 길이
        H = Integer.parseInt(st.nextToken()); // 체력
        D = Integer.parseInt(st.nextToken()); // 우산 내구도

        map = new int[N][N];
        dist = new int[N][N];
        ans = Integer.MAX_VALUE;

        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<N; j++){
                char str = line.charAt(j);

                if(str == 'U'){
                    U = new Point(i, j);
                    map[i][j] = D; // 우산
                }else if(str == 'S'){
                    S = new Point(i, j); // 현재
                }else if(str == 'E'){
                    E = new Point(i, j); // 안전지대
                }
            }
        }

        bfs();
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void bfs(){

        Queue<Move> queue = new LinkedList<>();
        queue.offer(new Move(S.x, S.y, 0, H, 0));
        dist[S.x][S.y] = H;

        while(!queue.isEmpty()){

            Move p = queue.poll();

//            System.out.println(p.toString());
//            System.out.println(dist[p.x][p.y]);

            if(p.x == E.x && p.y == E.y) {
                // 안전지대 발견
                ans = Math.min(ans, p.cnt);
                return;
            }

            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(dist[nx][ny] >= p.h + p.um) continue;

                int um = p.um;
                int h = p.h;

                if(map[nx][ny] > 0) { // 새 우산이 있으면
                    um = map[nx][ny]; // 새 우산 가진다.
                }

                // 죽음의 비
                if(um > 0){ // 우산 있으면 쓰고
                    um -= 1;
                }else { // 없으면 체력 마이너스
                    h -= 1;
                }

                if(p.h == 0){
                    continue;
                }

                dist[nx][ny] = p.h + p.um;
                queue.offer(new Move(nx, ny, um, h, p.cnt + 1));
            }
        }
    }

    static class Move{
        int x, y, um, h, cnt;

        Move(int x, int y, int um, int h, int cnt){
            this.x = x;
            this.y = y;
            this.um = um; // 우산 내구도
            this.h = h; // 체력
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Move{" +
                    "x=" + x +
                    ", y=" + y +
                    ", um=" + um +
                    ", h=" + h +
                    '}';
        }
    }
}
