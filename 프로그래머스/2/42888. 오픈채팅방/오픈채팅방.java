import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        /*
        ["Enter uid1234 Muzi", 
        "Enter uid4567 Prodo",
        "Leave uid1234",
        "Enter uid1234 Prodo",
        "Change uid4567 Ryan"]
        */
        
        HashMap<String,String> map = new HashMap<>();
        
        for(String rec : record){
            String[] splits = rec.split(" ");
            if(rec.startsWith("Enter")){
                map.put(splits[1], splits[2]);
            }else if(rec.startsWith("Change")){
                map.put(splits[1], splits[2]);
            }
        }
        
        ArrayList<String> list = new ArrayList<>();
        for(String rec : record){
            String[] splits = rec.split(" ");
            if(rec.startsWith("Enter")){
                list.add(map.get(splits[1]) + "님이 들어왔습니다.");
            }else if(rec.startsWith("Leave")){
                list.add(map.get(splits[1]) + "님이 나갔습니다.");
            }
        }
        
        return list.toArray(new String[list.size()]);
    }
}