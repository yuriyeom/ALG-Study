import java.awt.*;
import java.util.*;

public class Main {

    static int m, n, k;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int area;
    static ArrayList<Integer> answers;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        answers = new ArrayList<>();

        m = sc.nextInt();
        n = sc.nextInt();
        k = sc.nextInt();

        board = new int[m+1][n+1];
        visited = new boolean[m+1][n+1];

        int num = 0;

        while(k-- > 0){
            int startx = sc.nextInt();
            int starty = sc.nextInt();
            int endx = sc.nextInt();
            int endy = sc.nextInt();

            for (int i = starty; i < endy; i++) {
                for (int j = startx; j < endx; j++) {
                    board[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 1 || visited[i][j]) continue;
                bfs(i, j);
                num++;
            }
        }
        
        System.out.println(num);
        Collections.sort(answers);
        for(int a: answers){
            System.out.print(a + " ");
        }
    }

    static void bfs(int x, int y){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        visited[x][y] = true;
        area = 1;

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for (int p = 0; p < 4; p++) {
                int nx = cur.x + dx[p];
                int ny = cur.y + dy[p];

                if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if(visited[nx][ny] || board[nx][ny] == 1) continue;

                queue.offer(new Point(nx, ny));
                visited[nx][ny] = true;
                area++;
            }
        }
        answers.add(area);
    }
}
