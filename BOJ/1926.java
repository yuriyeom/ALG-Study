import java.awt.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static int[][] arr;
    public static boolean[][] visited;
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    public static int cnt = 0, max = 0;
    public static int n, m;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 세로, 가로
        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[n+1][m+1];
        visited = new boolean[n+1][m+1];

       
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(visited[i][j] || arr[i][j] == 0) continue;
                cnt++;
                bfs(i, j);
            }
        }
        System.out.println(cnt);
        System.out.println(max);

    }
  
    // 돌면서 그림 개수 체크, 그림 넓이 체크
    static void bfs(int x, int y){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x,y));
        visited[x][y] = true;
        int area = 1;

        while(!queue.isEmpty()){
            Point cur = queue.poll();
            for(int j=0; j<4; j++){
                int nextx = cur.x + dx[j];
                int nexty = cur.y + dy[j];

                if(nextx >= 0 && nextx < n && nexty >= 0 && nexty < m){
                    if(arr[nextx][nexty] == 1 && !visited[nextx][nexty]){
                        visited[nextx][nexty] = true;
                        queue.offer(new Point(nextx, nexty));
                        area++;
                    }
                }
            }
        }
        max = Math.max(max, area);
    }
}
