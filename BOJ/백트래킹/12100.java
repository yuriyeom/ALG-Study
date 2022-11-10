import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, ans;
	static int[][] map;
	
	// 상하좌우인데 미는거랑 값 바꾸는 방향은 반대로
	// 위로 밀려면 아래 방향으로 탐색
	static int[] dx = {1, -1, 0, 0}; 
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		ans = 0;
		// 5번 이동해서 최댓값
		back(map, 0);
		
		System.out.println(ans);
	}
	
	private static void back(int[][] map, int idx) {
		
		if(idx == 5) {
			
			// 최대 블록 찾고
			int max = findMaxBlock(map);
			ans = Math.max(ans, max);
			return;
		}
		
		// 4방향이 있으니까
		for(int d=0; d<4; d++) {
			
			// map 그대로 하지말고 복사해서 써라
			int[][] tmap = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					tmap[i][j] = map[i][j];
				}
			}
			
			// 밀고
			push(tmap, d);
			
			// 넘겨
			back(tmap, idx+1);
		}
		
		
	}
	
	static int[] ddx = {0, 0, 1, 1};
	static int[] ddy = {1, 1, 0, 0};
	private static void push(int[][] map, int d) {
		// tmap을 d방향으로 민다!!!
		
		// 밀어보자...
		
		// 각 미는 방향마다 시작점 찾고. 상하좌우 
		Point[] startP = {
				new Point(0,0), new Point(N-1, 0), new Point(0,0), new Point(0,N-1)
		};
//		System.out.println("밀 방향" + d);
		// 고른 방향대로 밀어야할 줄이 N개
		// 상하는 열이동, 좌우는 행이동
		for(int k=0; k<N; k++) {
			// 줄별 시작점 찾아서
			int sx = startP[d].x + ddx[d] * k;
			int sy = startP[d].y + ddy[d] * k;
//			System.out.println(k + "줄에 시작점 " + sx + " " + sy + " " + map[sx][sy]);
			pushLine(map, sx, sy, d);
		}
		
		
	}

	private static void pushLine(int[][] map, int sx, int sy, int d) {
		
		Queue<Integer> queue = new LinkedList<>();
		
		// 시작점 sx, sy를 시작으로 탐색
		// d 방향임
		
		for(int k=0; k<N; k++) { 
			int nx = sx + dx[d]*k;
			int ny = sy + dy[d]*k;
//			System.out.println(nx + " " + ny + " " + map[nx][ny]);
			if(map[nx][ny] > 0) { // 칸에 값이 있으면
				queue.offer(map[nx][ny]);
				map[nx][ny] = 0;
			}
		}
		// 숫자없으면 할거없음
		if(queue.isEmpty()) return;
		
		for(int k=0; k<N; k++) {
			if(queue.isEmpty()) break;
			int nx = sx + dx[d]*k;
			int ny = sy + dy[d]*k;
//			System.out.println(nx + " " + ny + " " + map[nx][ny]);
			// 하나를 뽑고
			int first = queue.poll();
			int temp = first;
			// 뒤에 하나가 또 있으면
			if(!queue.isEmpty()) {
				int second = queue.peek();
				if(first == second) {
					// 두 개가 같으면 합쳐준다
					queue.poll();
					temp = first + second;
//					System.out.println("합쳐짐 " + temp);
				}
			}
			map[nx][ny] = temp;
		}
		
	}

	// 최댓값 찾는다
	private static int findMaxBlock(int[][] map) {
		int max = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(max < map[i][j]) max = map[i][j];
			}
		}
		
		return max;
	}

}
