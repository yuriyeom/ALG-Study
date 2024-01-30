import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
시작 22:40
NxN, 물고기 M마리, 아기상어 1마리가 1초에 상하좌우 이동
처음 아기상어 크기는 2
자신보다 크기 큰 칸은 지날 수 X
자신보다 크기 작은 물고기 먹을 수 O, 크기 큰건 지날 수는 있음

먹을게 없으면 종료
먹을게 1마리면 먹고, 더 많으면 거리가 가까운거
거리 가까운 -> 가장 위 -> 가장 왼쪽 순으로 먹는다.

아기상어는 크기와 같은 수 물고기 먹을때 크기+1

몇 초 동안 먹는가!

 */
public class Main {
    static int[][] map;
    static int N, M, sx, sy, size;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        sx = -1; sy = -1;
        size = 2;
        map = new int[N][N];

        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){ // 아기 상어
                    sx = i; sy = j;
                    map[i][j] = 0;
                }
            }
        }

        int eatCnt = 0; // 먹은 수
        int time = 0; // 걸린 시간
        while(true){
            // 먹을 물고기 찾기
            Move fish = bfs(sx, sy);

            // 더 이상 먹을게 없으면 break
            if(fish == null) break;

            // 거리만큼 시간 더하기
            time += fish.dist;

            // 물고기먹고 상어 이동
            map[fish.x][fish.y] = 0;
            sx = fish.x;
            sy = fish.y;
            eatCnt++;

            // 아기상어 크기와 같은 수를 먹었으면 아기상어 크기 +1
            if(size == eatCnt) {
                size++;
                eatCnt = 0;
            }

        }

        System.out.println(time);
    }

    public static Move bfs(int sx, int sy){
        Queue<Move> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.offer(new Move(sx, sy, 0));
        visited[sx][sy] = true;

        PriorityQueue<Move> pq = new PriorityQueue<>();
        while(!queue.isEmpty()){
            Move m = queue.poll();
            for(int d=0; d<4; d++){
                int nx = m.x + dx[d];
                int ny = m.y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] > size) continue; // 큰건 못지나감

                if(map[nx][ny] < size && map[nx][ny] != 0){ // 크기가 작은거 먹을수있음
                    pq.add(new Move(nx, ny, m.dist+1));
                    visited[nx][ny] = true;
                }else{ // 크기가 같은건 지나가기만
                    queue.offer(new Move(nx, ny, m.dist+1));
                    visited[nx][ny] = true;
                }
            }
        }

        if(pq.isEmpty()){ // 먹을수있는게 없다.
            return null;
        }

        // 먹을 물고기 리턴
        return pq.poll();
    }
}

class Move implements Comparable<Move>{
    int x, y, dist;

    public Move(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    // 거리 가까운 -> 가장 위 -> 가장 왼쪽 순으로 먹는다.
    public int compareTo(Move m){
        if(this.dist == m.dist){
            if(this.x == m.x){
                return Integer.compare(this.y, m.y);
            }
            return Integer.compare(this.x, m.x);
        }
        return Integer.compare(this.dist, m.dist);
    }
}
