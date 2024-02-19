import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans;
    static int[] cnt;
    static ArrayList<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        cnt = new int[N+1];
        for(int i=1; i<=N; i++){
            cnt[i] = Integer.parseInt(st.nextToken());
        }

        adjList = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            adjList[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());

            for(int j=0; j<m; j++){
                adjList[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        ans = Integer.MAX_VALUE;

        devide(1, new boolean[N+1]);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void devide(int depth, boolean[] pick){
        if(depth == N+1){

            // 적어도 하나로 안 나눠졌으면
            int num = 0;
            for(boolean p : pick)
                if(p) num++;
            if(num == N || num == 0) return;

            // 인구차이 계산 (이어져있지 않으면 -1 리턴)
            int tsum = connect(pick, true);
            int fsum = connect(pick, false);
            if(tsum == -1 || fsum == -1) return;

            int diff = Math.abs(tsum - fsum);
            ans = Math.min(ans, diff);

            return;
        }

        devide(depth+1, pick);

        pick[depth] = true;
        devide(depth+1, pick);
        pick[depth] = false;
    }

    public static int connect(boolean[] pick, boolean status){

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        Arrays.fill(visited, !status);
        int sum = 0;

        for(int i=1; i<=N; i++){
            if(pick[i] == status){
                queue.offer(i);
                visited[i] = status;
                break;
            }
        }

        while(!queue.isEmpty()){
            Integer p = queue.poll();
            visited[p] = status;

            for(int next : adjList[p]){

                if(visited[next] == status || pick[next] != status) continue;

                queue.offer(next);
            }
        }

        for(int i=1; i<=N; i++){
            if(pick[i] != visited[i]){
                return -1;
            }
            if(pick[i] == status)
                sum += cnt[i];
        }

        return sum;
    }
}
