import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        
        ArrayList<FileName> list = new ArrayList<>();
        
        for(int f=0; f<files.length; f++){
            String file = files[f];
            int idx = 0;
            String head = "", number = "", tail = "";
            // HEAD 찾기 (문자("",".","-" 포함), 1글자~)
            for(int i=0; i<file.length(); i++){
                if(file.charAt(i) - '0' >= 0 && file.charAt(i) - '0' <= 9){ // 숫자
                    head = file.substring(0, i).toLowerCase();
                    idx = i;
                    break;
                }
            }
          
            // NUMBER 찾기 (숫자, 1~5글자)
            for(int i=idx; i<file.length(); i++){
                if((file.charAt(i) - '0' >= 0 && file.charAt(i) - '0' <= 9)){ // 숫자
                    number += file.charAt(i);
                }else{
                    idx = i+1;
                    break;
                }
            }
            
            // TAIL 찾기 (나머지부분, 숫자 or 아무글자x)
            if(number == null) tail = String.valueOf(idx);
            else 
                tail = file.substring(idx, file.length()).trim();
            
            list.add(new FileName(f, head, Integer.parseInt(number), tail));
       
        }
        
        Collections.sort(list);
        
        for(int i=0; i<files.length; i++){
            answer[i] = files[list.get(i).idx];
        }
      
        return answer;
    }
}

class FileName implements Comparable<FileName>{
    
    int idx;
    String head;
    Integer number;
    String tail;
    
    FileName(int idx, String head, Integer number, String tail){
        this.idx = idx;
        this.head = head.toLowerCase();
        this.number = number;
        this.tail = tail.toLowerCase();
    }
    
    public int compareTo(FileName f){
        if(this.head.equals(f.head)){
            if(this.number == f.number){
                return 0;
            }
            return Integer.compare(this.number, f.number);
            
        }
        return this.head.compareTo(f.head);
    }
    
    public String toString(){
        return "{ " + idx + " " + head + " " + number + " " + tail + " }";
    }
}