import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, K, ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = Integer.MAX_VALUE;

        bfs();

        System.out.println(ans);
    }

    public static void bfs(){
        PriorityQueue<Subin> pq = new PriorityQueue<>(((s1, s2) -> s1.time - s2.time));
        boolean[] visited = new boolean[100001];
        pq.offer(new Subin(N, 0));

        while(!pq.isEmpty()){
            Subin subin = pq.poll();
            visited[subin.x] = true;

            if(subin.x == K){
                ans = Math.min(ans, subin.time);
                return;
            }

            if(isInside(subin.x*2) && !visited[subin.x*2]){
                pq.offer(new Subin(subin.x*2, subin.time));
            }
            if(isInside(subin.x+1) && !visited[subin.x+1]){
                pq.offer(new Subin(subin.x+1, subin.time+1));
            }
            if(isInside(subin.x-1) && !visited[subin.x-1]){
                pq.offer(new Subin(subin.x-1, subin.time+1));
            }
        }
    }

    public static boolean isInside(int x){
        if(x < 0 || x > 100000) return false;
        return true;
    }
}

class Subin{
    int x, time;

    public Subin(int x, int time){
        this.x = x;
        this.time = time;
    }
}
