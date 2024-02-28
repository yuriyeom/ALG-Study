import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static int[][] map;
    static int[][] dx = {{0}, {1}, {0,1,1}}; // 가로 세로 대각선
    static int[][] dy = {{1}, {0}, {1,0,1}};
    static int[][] pipes = {{0,2}, {1,2}, {0,1,2}}; // 가로2 세로2 대각선3
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        if(map[N-1][N-1] == 1){
            System.out.println(0);
            return;
        }

        ans = 0;
        bfs();
        System.out.println(ans);

    }

    public static void bfs(){
        Queue<Pipe> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.offer(new Pipe(0, 1, 0));
        visited[0][0] = true;
        visited[0][1] = true;

        while(!queue.isEmpty()){
            Pipe p = queue.poll();

            for(int t : pipes[p.type]){
                boolean possible = true;
                int nx=-1,ny=-1;
                for(int i=0; i<dx[t].length; i++){
                    nx = p.x + dx[t][i];
                    ny = p.y + dy[t][i];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1) {
                        possible = false;
                        break;
                    }
                }

                if(possible){
                    if(nx == N-1 && ny == N-1) ans++;
                    queue.offer(new Pipe(nx, ny, t));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static class Pipe{
        int x, y, type;

        public Pipe(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}
