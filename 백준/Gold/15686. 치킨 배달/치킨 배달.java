import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
    static int n, m, ans;
    
	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        ArrayList<Point>  chi = new ArrayList<>();
        ArrayList<Point> homes = new ArrayList<>();
        
        for(int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                int item = Integer.parseInt(st.nextToken());
                if(item == 2) { // 치킨집이면
                    chi.add(new Point(i, j));
                }else if(item == 1) {
                    homes.add(new Point(i, j));
                }
            }
        }
        
        ans = Integer.MAX_VALUE;
        
        comb(chi.size(), new int[m], 0, 0, chi, homes);
        
        bw.write(String.valueOf(ans));
        
        br.close();
        bw.close();
	}

	
    public static void comb(int arrlen, int[] sel, int idx, int k, ArrayList<Point> chi, ArrayList<Point> homes) {
        if(k == m) {
        	int tempans = 0;
        	for(int i=0; i<homes.size(); i++) { // 집들
        		int dist = Integer.MAX_VALUE;
        		int hx = homes.get(i).x;
        		int hy = homes.get(i).y;        
        		
        		for(int s : sel) { // 고른 치킨집
	            	
	            	int cx = chi.get(s).x;
	            	int cy = chi.get(s).y;
            	
            		int temp = Math.abs(cx - hx) + Math.abs(cy - hy);
            		
            		dist = Math.min(dist, temp);
            	}
        		tempans += dist;
            }
        	ans = Math.min(ans, tempans);
            
            return;
        }
        
        for(int i=idx; i<chi.size(); i++) {
            sel[k] = i;
            comb(arrlen, sel, i+1, k+1, chi, homes);
        }
    }
}
