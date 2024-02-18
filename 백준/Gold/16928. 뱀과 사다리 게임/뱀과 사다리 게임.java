import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[11][11];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x/10][x%10] = y;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            map[u/10][u%10] = v;
        }

        bfs();

    }

    public static void bfs(){
        Queue<Move> queue = new LinkedList<>();
        boolean[][] visited = new boolean[11][11];
        queue.offer(new Move(1, 0));
        visited[0][0] = true;

        while(!queue.isEmpty()){
            Move m = queue.poll();

            if(m.x == 100){
                System.out.println(m.dist);
                return;
            }

            for(int i=1; i<=6; i++){
                int nm = m.x + i;

                if(nm > 100) continue;
                if(visited[nm/10][nm%10]) continue;

                if(map[nm/10][nm%10] > 0){
                    queue.offer(new Move(map[nm/10][nm%10], m.dist+1));
                    visited[nm/10][nm%10] = true;
                }else{
                    queue.offer(new Move(nm, m.dist+1));
                    visited[nm/10][nm%10] = true;
                }
            }
        }
    }

    static class Move{
        int x, dist;

        public Move(int x, int dist){
            this.x = x;
            this.dist = dist;
        }
    }
}
