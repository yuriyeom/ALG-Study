import java.awt.*;
import java.util.*;

public class Main {
    static class Block implements Comparable<Block> {
        int x;
        int y;
        int color;

        Block(int x, int y, int color){
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public int compareTo(Block o){
            if(this.x == o.x){
                return o.y - this.y;
            }
            return o.x - this.x;
        }
    }

    static class BlockGroup implements Comparable<BlockGroup>{
        int area;
        int rainbow;
        int row;
        int col;
        ArrayList<Block> blocks;

        BlockGroup(int area, int rainbow, int row, int col, ArrayList<Block> blocks){
            this.area = area;
            this.rainbow = rainbow;
            this.row = row;
            this.col = col;
            this.blocks = blocks;
        }

        public int compareTo(BlockGroup o){
            if(this.area == o.area){
                if(this.rainbow == o.rainbow){
                    if(this.row == o.row){
                        return o.col - this.col;
                    }
                    return o.row - this.row;
                }
                return o.rainbow - this.rainbow;
            }
            return o.area - this.area;
        }
    }

    static int N, M, score;
    static int[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        score = 0;

        while(true) {
            BlockGroup bg = findMaxGroup();
            if(bg == null) break;

            remove(bg);
            gravity();
            rotation();
            gravity();
        }
        System.out.println(score);
    }

    // 그룹 기준
    // 일반블록 >= 1, 일반블록 색동일, 검은색x, 무지개노상관,
    // 개수 >= 2
    // 기준 블록 : 일반블록 중 행번호 가장작은거, 열 가장작은거
    public static BlockGroup findMaxGroup(){
        ArrayList<BlockGroup> grouplist = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == -1 || board[i][j] == -9) continue;

                Queue<Point> queue = new LinkedList<>();
                boolean[][] visited = new boolean[N][N];
                ArrayList<Block> blocklist = new ArrayList<>();

                queue.offer(new Point(i, j));
                visited[i][j] = true;

                int area = 1;

                boolean normal = false;

                int normalcolor = M+1;
                if(!normal && board[i][j] >= 1) {
                    normal = true;
                    normalcolor = board[i][j];
                }

                int rainbow = 0;

                while(!queue.isEmpty()){
                    Point p = queue.poll();
                    blocklist.add(new Block(p.x, p.y, board[p.x][p.y]));

                    if(board[p.x][p.y] == 0) rainbow += 1;

                    for (int k = 0; k < 4; k++) {
                        int nx = p.x + dx[k];
                        int ny = p.y + dy[k];

                        if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                        if(board[nx][ny] == -1 || board[nx][ny] == -9) continue;
                        if(visited[nx][ny]) continue;
                        // 일반블록은 색동일, 0은 가능
                        if(normal && board[nx][ny] != normalcolor && board[nx][ny] != 0) continue;

                        if(!normal && board[nx][ny] != 0) {
                            normal = true;
                            normalcolor = board[nx][ny];
                        }
                        queue.offer(new Point(nx, ny));
                        visited[nx][ny] = true;
                        area++;
                    }
                }

                // 기준 블록 찾기
                // 기준 블록 : 일반블록 중 행번호 가장작은거, 열 가장작은거
                Collections.sort(blocklist);
                Block leader = null;
                for(Block block : blocklist){
                    if(block.color != 0) leader = block;
                }
                
                if(area >= 2 && normal){
                    grouplist.add(new BlockGroup(area, rainbow, leader.x, leader.y, blocklist));
                }
            }
        }
        // 그룹 리스트 만들어서 가장 큰 거 찾기(무지개 많은, 기준의 행이큰, 열이 큰)
        Collections.sort(grouplist);        
        return grouplist.isEmpty()? null : grouplist.get(0);
    }

    // 2. 블록 제거
    public static void remove(BlockGroup bg){
        score += bg.area * bg.area;
        for(Block block : bg.blocks){
            board[block.x][block.y] = -9;
        }
    }

    // 3. 중력 작용
    public static void gravity(){
        for (int j = 0; j < N; j++) {
            for(int k=0; k<N; k++) {
                for (int i = 0; i < N - 1; i++) {

                    if (board[i + 1][j] == -1) continue;
                    if (board[i + 1][j] == -9 && board[i][j] != -1) {
                        int temp = board[i][j];
                        board[i][j] = board[i + 1][j];
                        board[i + 1][j] = temp;
                    }
                }
            }
        }
    }

    // 4. 90도 반시계 회전
    public static void rotation(){
        int[][] temps = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temps[i][j] += board[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = temps[j][N-1-i];
            }
        }
    }
}