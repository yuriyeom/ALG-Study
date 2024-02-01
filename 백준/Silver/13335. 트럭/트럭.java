import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 트럭 수
        int w = Integer.parseInt(st.nextToken()); // 다리 길이
        int L = Integer.parseInt(st.nextToken()); // 최대 하중

        int[] trucks = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
            trucks[i] = Integer.parseInt(st.nextToken());

        Queue<Truck> bridge = new LinkedList<>();
        bridge.add(new Truck(trucks[0], w));
        int sum = trucks[0];
        int i = 1; // truck 인덱스
        int time = 1;
        while(true){
            int bridgeSize = bridge.size(); // 다리 위 트럭 수
            while(bridgeSize-- > 0){
                Truck t = bridge.poll();
                if(t.loc > 1){ // 건너고 있다
                    bridge.add(new Truck(t.weight, t.loc-1));
                }else{ // 건넌다
                    sum -= t.weight;
                }

            }
            if(i < n && sum + trucks[i] <= L){ // 다리 하중이 충분하면
                // 새 트럭 진입
                bridge.add(new Truck(trucks[i], w));
                sum += trucks[i];
                i++;
            }
            time++;
            if(bridge.isEmpty()) break; // 다 건넜으면 종료
        }
        System.out.println(time);
    }
}
class Truck{
    int weight, loc;

    Truck(int weight, int loc){
        this.weight = weight;
        this.loc = loc;
    }
}
