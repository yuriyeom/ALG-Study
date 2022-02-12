import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N, K;
    static int[] distance;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        distance = new int[100001];
        Arrays.fill(distance, -1);
        
        bfs();
    }

    public static void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N); // 수빈이의 위치에서 시작
        distance[N] = 0;

        while(distance[K] == -1){
            int cur = queue.poll();

            // 이동방법은 3가지 : X-1, X+1, 2X
            for(int next: new int[]{cur + 1, cur - 1, 2 * cur}){
                if(next < 0 || next > 100000) continue;
                if(distance[next] == -1){
                    distance[next] = distance[cur] + 1;
                    queue.offer(next);
                }
            }
        }
        System.out.println(distance[K]);
    }
}
