import java.util.*;
class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        // int로 변환
        int int_video_len = strToInt(video_len);
        int int_pos = strToInt(pos);
        int int_op_start = strToInt(op_start);
        int int_op_end = strToInt(op_end);
        
        for(String com : commands){
            // 오프닝인지 확인
            if(int_pos >= int_op_start && int_pos <= int_op_end){
                int_pos = int_op_end;
            }
            
            if(com.equals("prev")){
                if(int_pos >= 10){
                    int_pos -= 10;
                }else{
                    int_pos = 0;
                }
            }else if(com.equals("next")){
                if(int_pos + 10 > int_video_len){
                    int_pos = int_video_len;
                }else{
                    int_pos += 10;
                }
            }
        }
        // 오프닝인지 확인
        if(int_pos >= int_op_start && int_pos <= int_op_end){
            int_pos = int_op_end;
        }
        
        int ansMin = int_pos / 60;
        int ansSec = int_pos % 60;
        
        answer += ansMin < 10 ? "0" + ansMin : ansMin;
        answer += ":";
        answer += ansSec < 10 ? "0" + ansSec : ansSec;
        
        return answer;
    }
    
    public int strToInt(String time){
        int result = 0;
        
        String min = time.split(":")[0];
        String sec = time.split(":")[1];
        
        result = Integer.parseInt(min)*60 + Integer.parseInt(sec);
        return result;
        
    }
}