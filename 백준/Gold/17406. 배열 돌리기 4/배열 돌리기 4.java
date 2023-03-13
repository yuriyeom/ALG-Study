import java.util.Scanner;

public class Main {

	static int n,m,min;
	static int[][] map;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		int k = sc.nextInt();
		
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int[][] orders = new int[k][3];
		
		for(int i=0; i<k; i++) {
			orders[i][0] = sc.nextInt();
			orders[i][1] = sc.nextInt();
			orders[i][2] = sc.nextInt();
		}
		
		min = Integer.MAX_VALUE;
		
		makeOrder(map, k, orders, new boolean[k], new int[k], 0);
		
		System.out.println(min);

	}
	
	public static int calScore(int[][] vmap) {
		int min = Integer.MAX_VALUE;
		for(int i=0; i<n; i++) {
			int sum = 0;
			for(int j=0; j<m; j++) {
				sum += vmap[i][j];
			}
			min = Math.min(min, sum);
		}
		
		return min;
	}
	
	public static void makeOrder(int[][] vmap, int k, int[][] orders, boolean[] visited, int[] output, int idx) {
		if(idx == k) {
			
			int[][] temp = new int[n][m];
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					temp[i][j] = vmap[i][j];
				}
			}		
			
			for(int o : output) {
				temp = rotate(temp, orders[o][0]-1, orders[o][1]-1, orders[o][2]);
			}
			
			min = Math.min(min, calScore(temp));
			return;
		}
		
		for(int i=0; i<k; i++) {
			if(visited[i]) continue;
			
			output[idx] = i;
			visited[i] = true; 
			makeOrder(vmap, k, orders, visited, output, idx+1);
			visited[i] = false;
		}
	}
	
	public static int[][] rotate(int[][] vmap, int r, int c, int s) {
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		int d = 0;
		
		// 몇번 돌리냐 -> s만큼
		for(int i=0; i<s; i++) {
			int x = r - s + i;
			int y = c - s + i;
			int temp2 =  vmap[x][y];
			
			while(true) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(nx < r-s+i || nx > r+s-i || ny < c-s+i || ny > c+s-i) {
					
					nx -= dx[d];
					ny -= dy[d];
					
					d = (d+1) % 4;
					
					nx += dx[d];
					ny += dy[d];
				}	
				
				int temp1 = temp2;
				temp2 = vmap[nx][ny];
				vmap[nx][ny] = temp1;
				
				x = nx;
				y = ny;
				
				if(x == r - s + i && y == c - s + i) break;
			}
		}
	
		return vmap;
	}

}
