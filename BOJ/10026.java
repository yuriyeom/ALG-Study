import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static char[][] board;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        board = new char[n+1][n+1];

        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        visited = new boolean[n+1][n+1];
        int num1 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j]) continue;
                bfs(i, j);
                num1++;
            }
        }
        int num2 = 0;
        visited = new boolean[n+1][n+1];
        // G를 R로 바꿔준다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 'G') board[i][j] = 'R';
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j]) continue;
                bfs(i, j);
                num2++;
            }
        }

        System.out.println(num1 + " " + num2);
    }

    static void bfs(int x, int y){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        visited[x][y] = true;

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(board[cur.x][cur.y] != board[nx][ny] || visited[nx][ny]) continue;

                queue.offer(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }
}
