import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] route;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        route = new int[100001];
        Arrays.fill(route, -1);
        bfs(N, K);

        StringBuilder sb = new StringBuilder();
        int now = K;
        while(route[now] != -1){
            sb.insert(0, now + " ");
            now = route[now];
        }
        sb.insert(0, N + " ");

        System.out.println(sb.toString());
    }

    public static void bfs(int N, int K){
        Queue<Point> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];

        queue.offer(new Point(N, 0));
        visited[N] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            if(p.x == K){
                System.out.println(p.y);
                return;
            }

            int[] dir = {p.x-1, p.x+1, p.x*2};
            for(int next : dir){
                if(next < 0 || next > 100000) continue;
                if(visited[next]) continue;

                if(route[next] == -1) route[next] = p.x;
                visited[next] = true;
                queue.offer(new Point(next, p.y+1));
            }
        }
    }
}
