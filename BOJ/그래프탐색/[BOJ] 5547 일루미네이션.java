package boj.n5547;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int w, h, ans = 0;
	static int[] dxeven = {-1, 0, 1, 1, 0, -1};
	static int[] dyeven = {1, 1, 1, 0, -1, 0};
	static int[] dxodd = {-1, 0, 1, 1, 0, -1};
	static int[] dyodd = {0, 1, 0, -1, -1, -1};
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		
		map = new int[w][h];
		
		for(int i=0; i<w; i++) {
			String line = br.readLine();
			for(int j=0; j<h; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		visited = new boolean[w][h];
		
		for(int i=0; i<w; i++) {
			for(int j=0; j<h; j++) {
				if(visited[i][j] || map[i][j] == 0) continue;
				bfs(i, j);
			}
		}

//		for(int i=0; i<w; i++) {
//			for(int j=0; j<h; j++) {
//				System.out.print(visited[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		System.out.println(ans);
	}
	
	private static void bfs(int x, int y) {
		
		Queue<Point> queue = new LinkedList<>();
		
		queue.offer(new Point(x, y));
		visited[x][y] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			if((p.y+1) % 2 == 0) {// 짝수
				for(int d=0; d<4; d++) {
					int nx = p.x + dxeven[d];
					int ny = p.y + dyeven[d];
					
					if(nx < 0|| nx >= w || ny < 0 || ny >= h) continue;
					if(visited[nx][ny] || map[nx][ny] == 0) continue;
					
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}else { // 홀수
				for(int d=0; d<4; d++) {
					int nx = p.x + dxodd[d];
					int ny = p.y + dyodd[d];
					
					if(nx < 0|| nx >= w || ny < 0 || ny >= h) continue;
					if(visited[nx][ny] || map[nx][ny] == 0) continue;
					
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}
}
