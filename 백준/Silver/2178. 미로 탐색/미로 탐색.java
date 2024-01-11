import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] arr;
    static int[][] distance;
    static int n, m;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[n][m];
        distance = new int[n][m];

        for(int i=0; i<n; i++){
            String line = sc.next();
            for(int j=0; j<m; j++){
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        bfs();
    }

    public static void bfs(){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0));
        distance[0][0] = 0;

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for(int j = 0; j < 4; j++){ // 상하좌우를 살펴준다.
                int nx = cur.x + dx[j];
                int ny = cur.y + dy[j];

                if(nx >= 0 && nx < n && ny >=0 && ny < m){
                    if(distance[nx][ny] == 0 && arr[nx][ny] == 1){
                        distance[nx][ny] = distance[cur.x][cur.y] + 1;
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }
        System.out.println(distance[n-1][m-1] + 1);
    }
}