import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] arr;
    static boolean[] visited;
    static int n;
    static int virusN = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int pairNum = sc.nextInt();

        arr = new int[n+1][n+1];
        visited = new boolean[n+1];

        for(int i=0; i<pairNum; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();

            arr[x][y] = arr[y][x] = 1;
        }

        bfs(1);
        System.out.println(virusN);
    }

    public static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int temp = queue.poll();
            for(int j=1; j<=n; j++){
                if(arr[temp][j] == 1 && !visited[j]){
                    virusN++;
                    queue.offer(j);
                    visited[j] = true;
                }
            }
        }
    }
}
