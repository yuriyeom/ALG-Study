import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {

        ArrayList<Integer> list = new ArrayList<>();
        
        HashMap<String, Integer> genreplayMap = new HashMap<>();

        // 많이 재생된 장르 저장
        for(int i=0; i<genres.length; i++){
            genreplayMap.put(genres[i], genreplayMap.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        // 장르 개수만큼 진행
        int genreNum = genreplayMap.size();
        while(genreNum-- > 0){
            
            // 가장 많이 재생된 장르 진행
            String maxGenre = MaxInMap(genreplayMap);
            genreplayMap.remove(maxGenre); // 다음 순서가 진행될 수 있게 제거

            // maxGenre의 play수를 playmap에 저장
            HashMap<String, Integer> playMap = new HashMap<>();            
            for(int i=0; i<plays.length; i++){
                if(genres[i].equals(maxGenre)){
                    playMap.put(String.valueOf(i), plays[i]);
                }
            }

            // playmap에서 많이 재생된 2개 저장
            int two = 2;
            while(playMap.size() > 0 && two > 0){
                list.add(Integer.parseInt(MaxInMap(playMap)));
                playMap.remove(MaxInMap(playMap));    
                two--;
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    String MaxInMap(HashMap<String, Integer> map){
        Map.Entry<String, Integer> maxEntry = null;
        int max = 0;
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(max < entry.getValue()){
                max = entry.getValue();
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }
}
