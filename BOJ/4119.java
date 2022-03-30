import java.awt.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class Main {

    public static int[][] fire, jihoon;
    public static Queue<Point> qFire, qJH;
    public static int r, c;
    public static int[] dx = { 1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        r = sc.nextInt(); // 행
        c = sc.nextInt(); // 열

        fire = new int[r + 1][c + 1];
        jihoon = new int[r + 1][c + 1];

        qFire = new LinkedList<>();
        qJH = new LinkedList<>();

        String[] input = new String[r+1];
        sc.nextLine();
        for (int i = 0; i < r; i++) {
            input[i] = sc.nextLine();
            for (int j = 0; j < c; j++) {
                // #, ., F, J
                // 벽을 -9 갈수있는곳 0 J,F도 0
                char ch = input[i].charAt(j);
                if(ch =='#'){ // 벽
                    fire[i][j] = -9;
                    jihoon[i][j] = -9;
                }
                if(ch == 'F'){ // 불 위치
                    qFire.offer(new Point(i, j));
                }
                if(ch == 'J'){ // 지훈 위치
                    qJH.offer(new Point(i, j));
                }
            }
        }

        // 불
        while(!qFire.isEmpty()){
            Point cur = qFire.poll();

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                // 벽일 때, 이미 방문했을때 불가능
                if(fire[nx][ny] == -9 || fire[nx][ny] > 0) continue;

                qFire.offer(new Point(nx, ny));
                fire[nx][ny] = fire[cur.x][cur.y] + 1;
            }
        }

        // 지훈이
        while(!qJH.isEmpty()){
            Point cur = qJH.poll();

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    System.out.println(jihoon[cur.x][cur.y] + 1);
                    return;
                }
                if(jihoon[nx][ny] == -9 || jihoon[nx][ny] > 0) continue;
                // 갈수있는 곳인지, 지훈이가 먼저 도착했는지
                if(fire[nx][ny] != 0 &&jihoon[cur.x][cur.y] + 1 >= fire[nx][ny]) continue;

                qJH.offer(new Point(nx, ny));
                jihoon[nx][ny] = jihoon[cur.x][cur.y] + 1;
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}
