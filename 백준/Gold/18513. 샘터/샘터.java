import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static long sum;
    static int[] places;
    static Set<Integer> set;
    static int[] d = {-1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 샘터 개수
        K = Integer.parseInt(st.nextToken()); // 집 개수

        places = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            places[i] = Integer.parseInt(st.nextToken());
        }

        set = new HashSet<>();

        bfs();

        System.out.println(sum);
    }

    public static void bfs(){
        Queue<House> queue = new LinkedList<>();
        int k = 0;
        sum = 0;
        for(int place : places){
            queue.offer(new House(place, 0));
            set.add(place);
        }

        while(!queue.isEmpty()){
            House p = queue.poll();

            for(int i=0; i<2; i++){
                int np = p.x + d[i];

                if(set.contains(np)) continue;

                queue.offer(new House(np, p.dist+1));
                k++;
                set.add(np);
                sum += p.dist + 1;
                if(k == K){
                    return;
                }
            }
        }
    }
    static class House{
        int x, dist;

        public House(int x, int dist){
            this.x = x;
            this.dist = dist;
        }
    }

}
