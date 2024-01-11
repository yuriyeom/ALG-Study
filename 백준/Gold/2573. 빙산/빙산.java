import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		visited = new boolean[N][M];
		
		// 두 덩어리 이상이면 break;
		int time = 0;
		while(true) {
			int areacnt = 0;
			visited = new boolean[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(visited[i][j] || map[i][j] <= 0) continue;
					areacnt++;
					area(i, j);
				}
			}
			
			
			if(areacnt == 0) {
				time = 0;
				break;
			}
			if(areacnt >= 2) break;
			
			bfs();
			
			time++;
		}
		
		System.out.println(time);
	}

	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		
		int[][] temp = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				temp[i][j] = map[i][j];
				if(map[i][j] > 0) queue.offer(new Point(i, j));
			}
		}
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			int zerocnt = 0;
			for(int d=0; d<4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				if(map[nx][ny] <= 0) zerocnt++;
				
			}
			
			temp[p.x][p.y] -= zerocnt;
		}
		map = temp;
	}
	
	public static void area(int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.offer(new Point(x, y));
		visited[x][y] = true;
		
		while(!queue.isEmpty()) {
			
			Point p = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(visited[nx][ny] || map[nx][ny] <= 0) continue;
				
				queue.offer(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}
}
