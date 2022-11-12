import java.util.Arrays;
import java.util.Scanner;

/*
 
 K 이상 있어야 검사 통과
 약품투입은 한 행을 A 또는 B로 바꿀 수 있다.
 가장 적은 약품투입 횟수
 
 */
public class Solution {
	
	static int D, W, K, ans;
	static int[][] map;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		for(int t=1; t<=tc; t++) {
			
			D = sc.nextInt(); // 두께
			W = sc.nextInt(); // 가로
			K = sc.nextInt(); // 합격기준
			
			map = new int[D][W];
			
			for(int i=0; i<D; i++) {
				for(int j=0; j<W; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			// 
			ans = Integer.MAX_VALUE;
			
//			boolean result = test(map);
//			if(result) {
//				System.out.println("#" + t + " " + ans);
//				continue;
//			}
			
			back(0, map, 0);
			System.out.println("#" + t + " " + ans);
		}
	}
	private static void back(int idx, int[][] map, int cnt) {
		if(ans < cnt) return;
		if(idx == D) {
			// 성능 검사
			boolean result = test(map);
			if(result) {
//				if(cnt == 0) {
//					for(int i=0; i<D; i++) {
//						for(int j=0; j<W; j++) {
//							System.out.print(map[i][j] + " ");
//						}
//						System.out.println();
//					}
//				}
//				System.out.println(cnt);
				ans = Math.min(ans, cnt);
			}
			return;
		}
		
		int[][] tmap = new int[D][W];
		for(int i=0; i<D; i++) {
			for(int j=0; j<W; j++) {
				tmap[i][j] = map[i][j];
			}
		}
		
		// 원본 idx 행
		int[] origin = Arrays.copyOf(tmap[idx], W);
		
		// 약품투입 x
		back(idx+1, tmap, cnt);
		
		// A 약품투입
		for(int k=0; k<W; k++) {
			tmap[idx][k] = 0; // A
		}
		
		back(idx+1, tmap, cnt+1);

		// B 약품투입
		for(int k=0; k<W; k++) {
			tmap[idx][k] = 1; // A
		}
		
		back(idx+1, tmap, cnt+1);
		
		// idx 행 원상복귀
		for(int i=0; i<W; i++) {
			tmap[idx][i] = origin[i];
		}
	}
	
	
	private static boolean test(int[][] map) {
		// 모든 줄이 합격기준 K을 넘어야 true
		for(int j=0; j<W; j++) {
			// i열 확인
			boolean col = false;
		
			for(int i=0; i<=D-K; i++) { // j열에서 i행을 시작으로 K개 연속으로 있는지
				boolean possible = true;
				
				int item = map[i][j];
//				System.out.println("기준 " + item);
				for(int k=i+1; k<i+K; k++) {
					if(item != map[k][j]) {
//						System.out.println(k + " " + j + " 때문에 안됨");
						possible = false;
					}
				}
				if(possible) {
//					System.out.println(j + " 열 가능========");
					col = true;
					break;
				}
			}
			
			// 다 해보고 정말 없으면 return false;
			if(!col) return false;
		}
		return true;
	}

}
