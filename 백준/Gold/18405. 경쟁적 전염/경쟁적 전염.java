import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, K, S, X, Y;
    static int[][] map;
    static PriorityQueue<Virus> pq;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        pq = new PriorityQueue<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[N][N];
        while(S-- > 0){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][j] > 0 && !visited[i][j]){
                        visited[i][j] = true;
                        pq.offer(new Virus(i, j, map[i][j]));
                    }
                }
            }

            bfs();
        }

        System.out.println(map[X-1][Y-1]);
    }

    public static void bfs(){
        while(!pq.isEmpty()){
            Virus v = pq.poll();

            for(int d=0; d<4; d++){
                int nx = v.x + dx[d];
                int ny = v.y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(map[nx][ny] > 0) continue;

                map[nx][ny] = v.num;
            }
        }
    }

    static class Virus implements Comparable<Virus>{
        int x, y, num;

        public Virus(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
        }

        public int compareTo(Virus virus){
            return Integer.compare(this.num, virus.num);
        }
    }
}
