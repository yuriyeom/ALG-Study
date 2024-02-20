import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = line.charAt(j) == 'L' ? 0 : 1;
            }
        }

        ans = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 1) continue;

                visited = new boolean[N][M];

                bfs(i, j);
            }
        }

        System.out.println(ans);
    }

    public static void bfs(int x, int y){
        Queue<Move> queue = new LinkedList<>();
        queue.offer(new Move(x, y, 0));
        visited[x][y] = true;

        while(!queue.isEmpty()){
            Move m = queue.poll();

            ans = Math.max(ans, m.dist);

            for(int d=0; d<4; d++){
                int nx = m.x + dx[d];
                int ny = m.y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(visited[nx][ny] || map[nx][ny] == 1) continue;

                queue.offer(new Move(nx, ny, m.dist+1));
                visited[nx][ny] = true;
            }
        }
    }

    static class Move{
        int x, y, dist;

        public Move(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
