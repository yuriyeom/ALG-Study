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
* 시작 21:42
* 바이러스 M개를 놓을 것
* NxN 연구소 0,1,2
* map에 있는 바이러스 중 M개를 골라 동시에 복제
* 모든 칸에 바이러스를 퍼뜨리는 >최소< 시간
* */
public class Main {
    static int[][] map;
    static int N, M, ans;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static ArrayList<Point> viruses = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int item = Integer.parseInt(st.nextToken());
                if (item == 2) {
                    viruses.add(new Point(i, j));
                    map[i][j] = 0;
                } else if(item == 1){
                    map[i][j] = -1;
                }
            }
        }

        ans = Integer.MAX_VALUE;
        pickVirus(0, 0, new int[M], new boolean[viruses.size()]);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void pickVirus(int idx, int depth, int[] output, boolean[] visited) {
        if (depth == M) {

            // 복제 배열
            int[][] temp = new int[N][N];
            for (int i = 0; i < N; i++) {
                temp[i] = Arrays.copyOf(map[i], N);
            }

            // 퍼뜨리기
            // 바이러스 퍼지는 시간 담아서 리턴
            temp = bfs(temp, output);

            // 바이러스 자리 채우기
            for (int i = 0; i < M; i++) {
                temp[viruses.get(output[i]).x][viruses.get(output[i]).y] = -1;
            }

            int time = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (temp[i][j] == 0) { // 바이러스 안 퍼진 0있으면 무효
                        time = Integer.MAX_VALUE;
                    }
                    time = Math.max(time, temp[i][j]);
                }
            }

            ans = Math.min(time, ans);
            return;
        }

        for (int i = idx; i < viruses.size(); i++) {
            if (visited[i]) continue;
            output[depth] = i;
            visited[i] = true;
            pickVirus(i + 1, depth + 1, output, visited);
            visited[i] = false;
        }

    }

    public static int[][] bfs(int[][] temp, int[] output) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for (int o : output) {
            queue.offer(new Point(viruses.get(o).x, viruses.get(o).y));
            visited[viruses.get(o).x][viruses.get(o).y] = true;
            temp[viruses.get(o).x][viruses.get(o).y] = 0;
        }

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny] || temp[nx][ny] == -1) continue;

                queue.offer(new Point(nx, ny));
                visited[nx][ny] = true;
                temp[nx][ny] = temp[p.x][p.y] + 1;
            }
        }
        return temp;
    }
}