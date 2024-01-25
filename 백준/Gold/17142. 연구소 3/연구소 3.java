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
* 시작 10:37
* NxN 연구소
* M개를 활성화한다.
* 활성 -> 비활성 이동하면 비활성도 활성된다
* */
public class Main {
    static int[][] map;
    static int N,M,ans;
    static int[] dx= {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static ArrayList<Point> virus;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        virus = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int item = Integer.parseInt(st.nextToken());
                if(item == 2){ // 바이러스
                    virus.add(new Point(i, j));
                    map[i][j] = -5;
                }else if(item == 1){ // 벽
                    map[i][j] = -1;
                }else{ // 빈 칸
                    map[i][j] = 0;
                }
            }
        }

        ans = Integer.MAX_VALUE;
        pickVirus(0, 0, new int[M]);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void pickVirus(int idx, int depth, int[] output){
        if(depth == M){

            // 복제 배열
            int[][] temp = new int[N][N];
            for(int i=0; i<N; i++){
                temp[i] = Arrays.copyOf(map[i], N);
            }

            // BFS
            temp = bfs(output, temp);

            for(Point v : virus){
                temp[v.x][v.y] = -5;
            }

            // 최소 시간 구하기
            int min = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(temp[i][j] == 0) min = Integer.MAX_VALUE;
                    else if(temp[i][j] < 0) continue;
                    else min = Math.max(min, temp[i][j]);
                }
            }

            ans = Math.min(ans, min);

            return;
        }

        for(int i=idx; i<virus.size(); i++){
            output[depth] = i;
            pickVirus(i+1, depth+1, output);
        }
    }

    public static int[][] bfs(int[] output, int[][] temp){
        Queue<Virus> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for(int o : output){
            queue.offer(new Virus(virus.get(o).x, virus.get(o).y, true));
            visited[virus.get(o).x][virus.get(o).y] = true;
            temp[virus.get(o).x][virus.get(o).y] = 0;
        }

        while(!queue.isEmpty()){
            Virus v = queue.poll();

            for(int d=0; d<4; d++){
                int nx = v.x + dx[d];
                int ny = v.y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(temp[nx][ny] == -1 || visited[nx][ny]) continue;

                queue.offer(new Virus(nx, ny, v.isActive));
                temp[nx][ny] = temp[v.x][v.y] + 1;
                visited[nx][ny] = true;
            }
        }

        return temp;
    }
}

class Virus{
    int x, y;
    boolean isActive;

    public Virus(int x, int y, boolean isActive){
        this.x = x;
        this.y = y;
        this.isActive = isActive;
    }
}
