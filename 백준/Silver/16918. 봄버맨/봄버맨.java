import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 봄버맨
public class Main {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static char[][] board;
    static int r, c, n;
    static Queue<Point> queue;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        r = sc.nextInt();
        c = sc.nextInt();
        n = sc.nextInt();

        board = new char[r][c];
        queue = new LinkedList<>();

        sc.nextLine();
        for (int i = 0; i < r; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        
        for (int i = 1; i <= n; i++) {
            // 1초 설치 후 아무것도x
            if(i == 1) continue;
            // 2초 폭탄 모두 설치, 원래 폭탄 위치 기억
            if(i % 2 == 0){
                installation();
            }
            // 3초 폭발
            if(i % 2 == 1){
                explosion();
            }
        }
        
        print_board();
    }

    // 모든 칸에 폭탄 설치
    static void installation(){
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(board[i][j] == 'O'){
                    queue.offer(new Point(i, j));
                }
                if(board[i][j] == '.'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    // 상하좌우 폭탄 폭발
    static void explosion() {
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            board[p.x][p.y] = '.';

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                board[nx][ny] = '.';
            }
        }
    }

    static void print_board(){
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}