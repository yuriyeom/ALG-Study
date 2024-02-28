import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int F, S, G, U, D, ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken()); // 총 층수
        S = Integer.parseInt(st.nextToken()); // 출발 층수
        G = Integer.parseInt(st.nextToken()); // 도착 층수
        U = Integer.parseInt(st.nextToken()); // 위로 몇층
        D = Integer.parseInt(st.nextToken()); // 아래로 몇층

        ans = Integer.MAX_VALUE;

        bfs();

        System.out.println(ans == Integer.MAX_VALUE ? "use the stairs" : ans);
    }

    public static void bfs(){
        Queue<Move> queue = new LinkedList<>();
        boolean[] visited = new boolean[F+1];
        queue.offer(new Move(S, 0));
        visited[S] = true;

        while(!queue.isEmpty()){
            Move m = queue.poll();

            if(m.x == G){
                ans = Math.min(ans, m.dist);
                return;
            }

            if(m.dist > ans) return;

            for(int btn : new int[]{U, -D}){
                int next = m.x + btn;

                if(next <= 0 || next > F) continue;
                if(visited[next]) continue;

                queue.offer(new Move(next, m.dist+1));
                visited[next] = true;

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
