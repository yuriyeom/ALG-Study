import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 시작 12:56
*
* 상하좌우 같은색 4개 이상 뿌요 제거
* 중력
* 또 뿌요 제거
*
* 제거할때마다 +1연쇄
* 한번에 여러 그룹있으면 동시에 제거, 그래도 +1연쇄
*
* 연쇄는 몇번??
* */
public class Main {
    static char[][] map;
    static int combo;
    static boolean end;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];
        for(int i=0; i<12; i++){
            String line = br.readLine();
            for(int j=0; j<6; j++){
                map[i][j] = line.charAt(j);
            }
        }

        combo = 0; // 연쇄 수
        while(true){
            end = true;
            boolean[][] visited = new boolean[12][6];

            // 뿌요 터뜨리기
            for(int i=0; i<12; i++){
                for(int j=0; j<6; j++){
                    if(visited[i][j] || map[i][j] == '.') continue;

                    bomb(i, j, visited);
                }
            }
            // 터질게 없으면 종료
            if(end) break;

            // +1연쇄
            combo++;

            // 중력으로 당기기
            gravity();

        }
        System.out.println(combo);
    }
    public static void bomb(int i, int j, boolean[][] visited){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(i, j));
        visited[i][j] = true;

        int area = 0; // 터뜨릴 뿌요 면적
        ArrayList<Point> bombTarget = new ArrayList<>(); // 터뜨릴 위치 저장

        while(!queue.isEmpty()){
            Point p = queue.poll();
            area++;
            bombTarget.add(p);

            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx < 0 || nx >= 12 || ny < 0 || ny >= 6) continue;
                if(visited[nx][ny]) continue;
                if(map[p.x][p.y] != map[nx][ny] || map[nx][ny] == '.') continue;

                queue.offer(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }

        // 면적이 4이상이면 터뜨리기
        if(area >= 4){
            end = false;
            for (Point target : bombTarget){
                map[target.x][target.y] = '.';
            }
        }
    }

    public static void gravity(){
        for(int j=0; j<6; j++){ // 한줄씩
            for(int i=0; i<12; i++){
                for(int k=11; k>=1; k--){
                    if(map[k][j] == '.') { // '.'이면 swap
                        char temp = map[k][j];
                        map[k][j] = map[k - 1][j];
                        map[k - 1][j] = temp;
                    }
                }
            }
        }
    }
}
