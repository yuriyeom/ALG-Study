import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int[] count;
    static int N, M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        list = new ArrayList[N+1];
        count = new int[N+1];

        StringBuilder sb = new StringBuilder();

        for(int i=1; i<N+1; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            int A = sc.nextInt();
            int B = sc.nextInt();

            list[A].add(B);
        }

        for(int i=1; i<N+1; i++){
            visited = new boolean[N+1];
            bfs(i);
        }

        int max = count[0];
        for(int i=1; i<N+1; i++){
            max = Math.max(max, count[i]);
        }

        for(int i=1; i<N+1; i++){
            if(max == count[i]){
                sb.append(i + " ");
            }
        }
        System.out.println(sb);
    }

    public static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int temp = queue.poll();
            for(int next : list[temp]){
                if(!visited[next]){
                    visited[next] = true;
                    queue.offer(next);
                    count[next]++;
                }
            }
        }
    }
}
