import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int ans;
	static char[][] map;
	static boolean[][] visited;
	static ArrayList<Point> soms, yeons;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 7명. 인접해야함
		// 서로 섞여도 됨
		// but 이다솜파가 4명 이상이여야 함
		map = new char[5][5];
		soms = new ArrayList<>();
		yeons = new ArrayList<>();
		
		for(int i=0; i<5; i++) {
			String line = sc.next();
			for(int j=0; j<5; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'S') {
					soms.add(new Point(i, j));
				}else {
					yeons.add(new Point(i, j));
				}
			}
		}
	
		ans = 0;
		visited = new boolean[5][5];
		
		// 7명을 먼저 뽑아?
		// 다솜이 중 4,5,6,7명 뽑아서 
		// 3,2,1명을 또 도연파에서.
		for(int r=4; r<=7; r++) {
			combSom(soms.size(), new int[r], 0, 0);
		}
		// 7명이 인접한지 체크?
		
		System.out.println(ans);
	}

	private static void combSom(int size, int[] output, int idx, int k) {
	
		if(k == output.length) {
			combYeon(yeons.size(), new int[7-k], 0, 0, output);
			
			return;
		}
		
		for(int i=idx; i<size; i++	) {
			output[k] = i;
			combSom(size, output, i+1, k+1);
		}
		
	}

	private static void combYeon(int size, int[] output, int idx, int k, int[] somoutput) {
		if(k == output.length) {
			
			// somoutput, output
			
			int[][] check = new int[5][5];
			
			for(int o : somoutput) {
				check[soms.get(o).x][soms.get(o).y]	= 1;
			}
			for(int o : output) {
				check[yeons.get(o).x][yeons.get(o).y]	= 1;
			}
			
			int area = bfs(soms.get(somoutput[0]).x, soms.get(somoutput[0]).y, check);
			
			if(area==7) ans++;
			
			return;
		}
		
		for(int i=idx; i<size; i++	) {
			output[k] = i;
			combYeon(size, output, i+1, k+1, somoutput);
		}
	}

	private static int bfs(int x, int y, int[][] check) {
		boolean[][] visited = new boolean[5][5];
		
		Queue<Point>  queue = new LinkedList<>();
		queue.offer(new Point(x, y));
		visited[x][y] = true;
		int area = 1;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				
				if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5 ) continue;
				if(visited[nx][ny]) continue;
				if(check[nx][ny] == 0) continue;
				
				queue.offer(new Point(nx, ny));
				visited[nx][ny] = true;
				area++;
			}
			
		}
		return area;
	}
	
	

}
