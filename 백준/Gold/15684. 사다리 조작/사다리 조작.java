import java.util.Scanner;

public class Main {

	static int N, M, H;
	static int[][] map;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		
		map = new int[H+1][N+1]; // 6, 5 => 7, 6
		
		for(int i=0; i<M; i++) {
			// a 위치에서
			// b 세로선과 b+1 세로선을 연결했다
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = 1;
			map[a][b+1] = -1;
		}	
		
		// 세로는 1부터 4까지
		// 가로는 0부터 5까지
		
		// 3보다 크면 -1 출력
		for(int k=0; k<=3; k++) {
			back(0, k);
		}
		
		System.out.println(-1);
	}
	
	public static void back(int idx, int cnt) { // cnt : 몇개 놓을건지
		if(idx == cnt) { // 다 놨으면 
			// 확인하고 종료
			boolean result = check();
			
			if(result) { // cnt개일때 가능하다 => 출력하고 종료
				System.out.println(cnt);
				System.exit(0);
			}
			return;
		}
		
		for(int i=1; i<=H; i++) { 
			for(int j=1; j<N; j++) {// 세로선 j번
				if(map[i][j] != 0 || map[i][j+1] != 0) continue;
//				System.out.println(i + " "+ j);
				
				// 다리를 놓고
				map[i][j] = 1;
				map[i][j+1] = -1;
				
				back(idx+1, cnt);
				
				// 돌려놓기
				map[i][j] = 0;
				map[i][j+1] = 0;
			}
		}
	}

	private static boolean check() {
		
		for(int i=1; i<=N; i++) { // 세로선 타고 내려간다
			int x = 1; // 시작 x
			int y = i; // 시작 y
			
			while(x <= H) { // x가 경계 내에서
				if(map[x][y] == 1) { // 오른쪽 다리 있으면 y++ 이동
					y++;
				}
				else if(map[x][y] == -1 ) { // 왼쪽 다리 있으면 y-- 이동
					y--;
				}
				x++; // 한 줄 이동
			}
			
			if(y != i) { // 끝에 도착했는데 i랑 다르면 return false
				return false;
			}
		}
		return true;
	}
}