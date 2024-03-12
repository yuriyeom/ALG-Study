import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] adjList;
    static int[] degree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] D = new int[N+1];
            for(int i=1; i<=N; i++){
                D[i] = Integer.parseInt(st.nextToken());
            }

            adjList = new ArrayList[N+1];
            for(int i=1; i<=N; i++){
                adjList[i] = new ArrayList<>();
            }

            degree = new int[N+1];
            for(int i=0; i<K; i++){
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                degree[y]++;
                adjList[x].add(y);
            }

            int W = Integer.parseInt(br.readLine());

            bfs(W, N, D);
        }
    }

    public static void bfs(int W, int N, int[] D){
        Queue<Integer> queue = new LinkedList<>();
        int[] maxTime = new int[N+1];
        for(int i=1; i<=N; i++){
            maxTime[i] = D[i];
            if(degree[i] == 0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int b = queue.poll();

            for(int next : adjList[b]){
                maxTime[next] = Math.max(maxTime[b]+D[next], maxTime[next]);
                degree[next]--;

                if(degree[next] == 0)
                    queue.offer(next);
            }
        }

        System.out.println(maxTime[W]);
    }
}
