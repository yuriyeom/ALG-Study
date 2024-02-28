import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int L, R, C;
    static char[][][] map;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dc = {0, 0, 0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L == 0 && R == 0 && C == 0) break;

            map = new char[L][R][C];

            int sx=-1, sy=-1, sc=-1;
            for(int i=0; i<L; i++){
                for(int j=0; j<R; j++){
                    String line = br.readLine();
                    for(int k=0; k<C; k++){
                        map[i][j][k] = line.charAt(k);
                        if(map[i][j][k] == 'S'){
                            sx = j; sy = k; sc = i;
                        }
                    }
                }
                br.readLine();
            }

            int ans = bfs(sx, sy, sc);

            if(ans == -1){
                System.out.println("Trapped!");
            }else{
                System.out.println("Escaped in " + ans + " minute(s).");
            }
        }
    }

    public static int bfs(int sx, int sy, int sc){
        Queue<Move> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[L][R][C];
        queue.offer(new Move(sx, sy, sc, 0));
        visited[sc][sx][sy] = true;

        while(!queue.isEmpty()){
            Move m = queue.poll();

            for(int d=0; d<6; d++){
                int nx = m.x + dx[d];
                int ny = m.y + dy[d];
                int nc = m.c + dc[d];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C || nc < 0 || nc >= L)  continue;
                if(map[nc][nx][ny] == '#' || visited[nc][nx][ny]) continue;
                if(map[nc][nx][ny] == 'E'){
                    return m.dist+1;
                }

                queue.offer(new Move(nx, ny, nc, m.dist+1));
                visited[nc][nx][ny] = true;
            }
        }

        return -1;
    }

    static class Move{
        int x,y,c,dist;

        public Move(int x, int y, int c, int dist){
            this.x = x;
            this.y = y;
            this.c = c;
            this.dist = dist;
        }
    }
}
