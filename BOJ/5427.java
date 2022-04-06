import java.awt.*;
import java.util.*;

public class Main {

    static int[][] sg, fire;
    static boolean[][] firestart;
    static int w, h;
    static Queue<Point> qsg, qfire;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0){
            w = sc.nextInt();
            h = sc.nextInt();

            sg = new int[h+1][w+1];
            fire = new int[h+1][w+1];
            firestart = new boolean[h+1][w+1];

            qfire = new LinkedList<>();
            qsg = new LinkedList<>();

            sc.nextLine();
            for (int i = 0; i < h; i++) {
                String line = sc.nextLine();

                for (int j = 0; j < w; j++) {
                    char ch = line.charAt(j);
                    if(ch == '#'){
                        sg[i][j] = -1;
                        fire[i][j] = -1;
                    }
                    if(ch == '@'){
                        qsg.offer(new Point(i, j));
                        sg[i][j] = 1;
                    }
                    if(ch == '*'){
                        qfire.offer(new Point(i, j));
                        firestart[i][j] = true;
                        fire[i][j] = 1;
                    }
                }
            }

            while(!qfire.isEmpty()){
                Point cur = qfire.poll();

                for (int k = 0; k < 4; k++) {
                    int nx = cur.x + dx[k];
                    int ny = cur.y + dy[k];

                    if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                    if(fire[nx][ny] == -1 || fire[nx][ny] > 0) continue;
                    if(firestart[nx][ny]) continue;

                    qfire.offer(new Point(nx, ny));
                    fire[nx][ny] = fire[cur.x][cur.y] + 1;
                }
            }

            boolean escape = false;
            while(!qsg.isEmpty() && !escape){
                Point cur = qsg.poll();

                for (int k = 0; k < 4; k++) {
                    int nx = cur.x + dx[k];
                    int ny = cur.y + dy[k];

                    if(nx < 0 || nx >= h || ny < 0 || ny >= w) {
                        System.out.println(sg[cur.x][cur.y]);
                        escape = true;
                        break;
                    }
                    if(sg[nx][ny] == -1 || sg[nx][ny] > 0) continue;
                    if(fire[nx][ny] != 0 && fire[nx][ny] <= sg[cur.x][cur.y] + 1) continue;

                    qsg.offer(new Point(nx, ny));
                    sg[nx][ny] = sg[cur.x][cur.y] + 1;
                }
            }
            if(!escape) System.out.println("IMPOSSIBLE");
        }
    }
}
