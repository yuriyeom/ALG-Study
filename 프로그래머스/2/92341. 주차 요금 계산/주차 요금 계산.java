import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        // fee : 기본시간(분), 기본요금(원), 단위시간(분), 단위요금(원)
        // records : [시각 차량번호 내역, ..]
        
        String[][] recs = new String[records.length][3];
        for(int i=0; i<records.length; i++){
            recs[i] = records[i].split(" ");
        }
        boolean[] checked = new boolean[records.length];
        
        TreeMap<String, Integer> map = new TreeMap<>();
        
        // 차량 입차,출차 짝지어서 (출차없으면 23:59로)
        for(int i=0; i<recs.length; i++){ // "IN"
            if(checked[i] || recs[i][2].equals("OUT")) continue;
            
            for(int j=i+1; j<recs.length; j++){ // "OUT"
                if(checked[j]) continue;
                
                // 누적 시간 계산
                if(recs[i][1].equals(recs[j][1]) && recs[j][2].equals("OUT")){
                    checked[i] = checked[j] = true;
                    
                    map.put(recs[i][1], map.getOrDefault(recs[i][1], 0) + calTime(recs[i][0], recs[j][0]));
                    break;
                }
            }
            
            // 출차가 없는 경우
            if(!checked[i]){
                map.put(recs[i][1], map.getOrDefault(recs[i][1], 0) + calTime(recs[i][0], "23:59"));
                checked[i] = true;
            }
        }
        
        int[] answer = new int[map.size()];
        int idx=0;
        for(String key : map.keySet()){
            map.put(key, calFee(fees, map.get(key)));
            answer[idx++] = map.get(key);
        }
        
        return answer;
    }
    
    public int calTime(String start, String end){
        // 시, 분 차이 계산
        int diffHour = Integer.parseInt(end.split(":")[0]) - Integer.parseInt(start.split(":")[0]);
        int diffMin = Integer.parseInt(end.split(":")[1]) - Integer.parseInt(start.split(":")[1]);
        
        // 분이 음수이면 시에서 60분 빼온다
        if(diffMin < 0){
            diffHour--;
            diffMin += 60;
        }
        
        return diffHour * 60 + diffMin;
    }
    
    public int calFee(int[] fees, int time){
        // 주차 요금 계산
        // fee : 기본시간(분), 기본요금(원), 단위시간(분), 단위요금(원)
        //  시간 <= 기본시간 : 기본요금
        //  시간 > 기본시간 : 기본요금 + 초과시간요금 (소수점 올림)
        if(time <= fees[0]) return fees[1];
        
        int fee = 0;
        
        // 기본요금 계산
        time -= fees[0];
        fee += fees[1];
        
        // 추가요금 계산
        fee += Math.ceil((double)time / (double)fees[2]) * fees[3];
        
        return fee;
    }
}