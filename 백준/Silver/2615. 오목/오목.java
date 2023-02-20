import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 오른, 아래, 오른아래대각선
    static int[] dx = {1, 0, 1, -1};
    static int[] dy = {0, 1, 1, 1};
    static int[][] board;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[20][20];

        for(int i=1; i<20; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for(int j=1; j<20; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<20; i++){
            for(int j=1; j<20; j++){
                if(board[i][j] != 0){
                    if(check(i, j)){
                        System.out.println(board[i][j]);
                        System.out.println(i + " " + j);
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }

    private static boolean check(int i, int j) {
        int len = 1;

        for(int d=0; d<4; d++){
            len = 1;
            while(true){
                int nx = i + dx[d]*len;
                int ny = j + dy[d]*len;

                if(nx <= 0 || nx >= 20 || ny <= 0 || ny >= 20) {
                    if(len == 5 && board[i-dx[d]][j-dy[d]] != board[i][j]) return true;
                    break;
                }
                if(board[i][j] != board[nx][ny]){
                    if(len == 5 && board[i-dx[d]][j-dy[d]] != board[i][j]){
                        return true;
                    }
                    break;
                }
                len++;
            }

        }

        return false;
    }
}
