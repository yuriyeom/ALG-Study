import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 시작 21:10
* NxM 연구소 0:빈칸, 1:벽, 2:바이러스
* 바이러스(2)는 상하좌우로
* 세울 벽(1)은 꼭 3개
* 안전 영역(다 퍼진 후 0의 개수)의 최댓값
* */
public class Main {
    static int[][] map;
    static int N,M, cnt=0, ans=0;
    static ArrayList<Point> walls;
    static ArrayList<Point> viruses;
    static int[] dx= {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        walls = new ArrayList<>();
        viruses = new ArrayList<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                int item = Integer.parseInt(st.nextToken());
                if(item == 0){
                    walls.add(new Point(i, j));
                }else if(item == 2){
                    viruses.add(new Point(i, j));
                }
                map[i][j] = item;
            }
        }

        // 벽 3개 고르기
        boolean[] visited = new boolean[walls.size()];
        pickWall(0, 0, new int[3], visited);

        // 바이러스 퍼지기
        // 안전영역 구하기
        System.out.println(ans);
    }

    public static void pickWall(int idx, int depth, int[] output, boolean[] visited){
        if(depth == 3){
            cnt = 0;

            int[][] temp = new int[N][M];

            for(int i=0; i<N; i++) {
                for (int j = 0; j < M; j++) {
                    temp[i][j] = map[i][j];
                }
            }

            // 벽 세우기
            for(int i=0; i<output.length; i++)
                temp[walls.get(output[i]).x][walls.get(output[i]).y] = 1;

            // 바이러스 퍼지기
            for(Point p : viruses)
                bfs(p.x, p.y, temp);

            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(temp[i][j] == 0) cnt++;
                }
            }
//            System.out.println(cnt);
            ans = Math.max(ans, cnt);

            return;
        }

        for(int i=idx; i<walls.size(); i++){
            if(visited[i]) continue;
            visited[i] = true;
            output[depth] = i;
            pickWall(i, depth+1, output, visited);
            visited[i] = false;
        }
    }

    public static void bfs(int x, int y, int[][] temp){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        queue.offer(new Point(x, y));
        visited[x][y] = true;

        while(!queue.isEmpty()){
            Point p = queue.poll();

            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(visited[nx][ny] || temp[nx][ny] == 1) continue;

                queue.offer(new Point(nx, ny));
                visited[nx][ny] = true;
                temp[nx][ny] = 2;
            }
        }
    }
}
