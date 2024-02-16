import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, S, E, ans;
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;
    static int[] route;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 사전순이므로 미리 정렬
        for(int i=0; i<=N; i++){
            Collections.sort(adjList[i]);
        }

        ans = 0;
        route = new int[N+1];

        // S -> E
        visited = new boolean[N+1];
        bfs(S, E);

        // E -> S
        visited = new boolean[N+1];

        // 안 겹치도록 경로 추적해서 저장
        int last = route[E];
        while(last > 0){
            visited[last] = true;
            last = route[last];
        }

        visited[S] = false;
        bfs(E, S);

        System.out.println(ans);
    }

    public static void bfs(int s, int e){
        Queue<Route> queue = new LinkedList<>();
        queue.offer(new Route(s, 0));
        visited[s] = true;

        while(!queue.isEmpty()){
            Route r = queue.poll();

            for(int next : adjList[r.x]){

                if(visited[next]) continue;

                route[next] = r.x; // next의 직전이 r.x임을 저장
                visited[next] = true;
                queue.offer(new Route(next, r.dist+1));

                if(next == e){
                    ans += r.dist + 1;
                    return;
                }
            }
        }
    }

    static class Route{
        int x, dist;

        public Route(int x, int dist){
            this.x = x;
            this.dist = dist;
        }
    }
}
