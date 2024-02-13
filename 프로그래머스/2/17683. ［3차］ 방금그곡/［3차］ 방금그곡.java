import java.util.*;
class Solution {
    public String solution(String m, String[] musicinfos) {
        ArrayList<Song> answer = new ArrayList<>();
        
        m = m.replace("C#", "c").replace("D#", "f").replace("F#", "f").replace("G#", "g").replace("A#", "a");
        
        for(int i=0; i<musicinfos.length; i++){
            String[] info = musicinfos[i].split(",");
            
            int time = diffTime(info[0], info[1]);
            String title = info[2];
            String code = info[3].replace("C#", "c").replace("D#", "f").replace("F#", "f").replace("G#", "g").replace("A#", "a");
            
            int idx = 0;
            String song = "";
            for(int j=1; j<=time; j++){
                char ch = code.charAt(idx);
                song += ch + "";
                idx = (idx + 1) % code.length();
            }
            
            if(song.contains(m)){
                answer.add(new Song(time, i, title));
            }
        }
        
        if(answer.size() > 1){
            // 여러 개면 재생 시간이 긴, 순서가 빠른 순서
            Collections.sort(answer, new Comparator<>(){
               public int compare(Song a, Song b){
                   if(a.time == b.time){
                       return Integer.compare(a.i, b.i);
                   }
                   return -Integer.compare(a.time, b.time);
               } 
            });
        }
        
        return answer.size() == 0 ? "(None)" : answer.get(0).title;
    }
    
    public int diffTime(String start, String end){
        
        String[] startSplit = start.split(":");
        String[] endSplit = end.split(":");
        
        int diffHour = Integer.parseInt(endSplit[0]) - Integer.parseInt(startSplit[0]);
        int diffMin = Integer.parseInt(endSplit[1]) - Integer.parseInt(startSplit[1]);
        
        if(diffMin < 0){
            diffHour--;
            diffMin += 60;
        }
        
        return diffHour * 60 + diffMin;
    }
}

class Song{
    int time, i;
    String title;
    
    public Song(int time, int i, String title){
        this.time = time;
        this.i = i;
        this.title = title;
    }
}