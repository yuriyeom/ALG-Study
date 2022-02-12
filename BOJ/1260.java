import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] arr;
    static boolean[] visited;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int m = sc.nextInt();
        int v = sc.nextInt();

        arr = new int[n+1][n+1];

        for(int i=0; i<m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();

            arr[x][y] = arr[y][x] = 1;
        }

        visited = new boolean[n+1];
        dfs(v);

        System.out.println();

        visited = new boolean[n+1];
        bfs(v);

    }
  
    public static void dfs(int start){
        visited[start] = true;
        System.out.print(start + " ");

        for(int j=1; j<=n; j++){
            if(arr[start][j] == 1 && visited[j] == false){
                dfs(j);
            }
        }
    }

    public static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        System.out.print(start + " ");

        while(!queue.isEmpty()){
            int temp = queue.poll();
            for(int j=1; j<=n; j++){
                if(arr[temp][j] == 1 && visited[j] == false){
                    queue.offer(j);
                    visited[j] = true;
                    System.out.print(j + " ");
                }
            }
        }
    }

}
