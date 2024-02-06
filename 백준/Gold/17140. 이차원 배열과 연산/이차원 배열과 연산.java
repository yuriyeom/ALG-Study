import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static int r, c, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[3][3];
        for(int i=0; i<3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while(true){

            // r,c가 범위 안이고 map[r-1][c-1]=k이면
            if(r-1 < map.length && c-1 < map[0].length && map[r-1][c-1] == k) break;

            time++;

            if(map.length >= map[0].length) { // 행의 개수<열의 개수일 때 행 연산
                map = row(map);
            }else if(map.length < map[0].length) { // 행의 개수<열의 개수일 때 열 연산
                map = col(map);
            }

            // 시간이 100 이상이면 -1 리턴
            if(time > 100) {
                time = -1;
                break;
            }
        }

        System.out.println(time);

    }

    public static int[][] row(int[][] map){
        // 모든 행을 정렬
        // 정렬된 결과 크기대로 새 배열 만들어서 저장. 가장 큰 행 기준. 크기 커진 곳은 0으로 채움

        // 원래 행, 열 길이
        int originRow = map.length;
        int originCol = map[0].length;

        // 새로운 행들을 저장할 리스트 배열
        ArrayList<Integer>[] rowMap = new ArrayList[originRow];

        for(int i=0; i<originRow; i++){
            // 숫자 개수 저장
            HashMap<Integer, Integer> numCnt = new HashMap<>();

            rowMap[i] = new ArrayList<>();

            for(int j=0; j<originCol; j++){
                if(map[i][j] == 0) continue;
                numCnt.put(map[i][j], numCnt.getOrDefault(map[i][j], 0)+1);
            }

            // 숫자 개수 map 정렬
            ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(numCnt.entrySet());
            list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b){
                    if(a.getValue() == b.getValue()){
                        return Integer.compare(a.getKey(), b.getKey());
                    }
                    return Integer.compare(a.getValue(), b.getValue());
                }
            });

            // 숫자, 횟수를 새로운 행에 저장
            for(Map.Entry<Integer, Integer> entry : list){
                rowMap[i].add(entry.getKey());
                rowMap[i].add(entry.getValue());
            }
        }

        // 새로운 행들의 최대 길이 구하기
        int maxLen = 0;
        for(ArrayList<Integer> l : rowMap){
            maxLen = Math.max(maxLen, l.size());
        }
        if(maxLen > 100) maxLen = 100;

        // 새로운 행들로 새로운 map 만들기. 길이가 최대보다 짧으면 0으로 채우기
        map = new int[originRow][maxLen];
        for(int i=0; i<originRow; i++){
            for(int j=0; j<maxLen; j++){
                if(rowMap[i].size() <= j){
                    map[i][j] = 0;
                }else{
                    map[i][j] = rowMap[i].get(j);
                }
            }
        }

        return map;
    }

    public static int[][] col(int[][] map){
        // 모든 열을 정렬
        // 정렬된 결과 크기대로 새 배열 만들어서 저장. 가장 큰 열 기준. 크기 커진 곳은 0으로 채움

        // 원래 행, 열 길이
        int originRow = map.length;
        int originCol = map[0].length;

        // 새로운 행들을 저장할 리스트 배열
        ArrayList<Integer>[] colMap = new ArrayList[originCol];

        for(int j=0; j<originCol; j++){
            // 숫자 개수 저장
            HashMap<Integer, Integer> numCnt = new HashMap<>();

            colMap[j] = new ArrayList<>();

            for(int i=0; i<originRow; i++){
                if(map[i][j] == 0) continue;
                numCnt.put(map[i][j], numCnt.getOrDefault(map[i][j], 0)+1);
            }

            // 숫자 개수 map 정렬
            ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(numCnt.entrySet());
            list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b){
                    if(a.getValue() == b.getValue()){
                        return Integer.compare(a.getKey(), b.getKey());
                    }
                    return Integer.compare(a.getValue(), b.getValue());
                }
            });

            // 숫자, 횟수를 새로운 행에 저장
            for(Map.Entry<Integer, Integer> entry : list){
                colMap[j].add(entry.getKey());
                colMap[j].add(entry.getValue());
            }
        }

        // 새로운 행들의 최대 길이 구하기
        int maxLen = 0;
        for(ArrayList<Integer> l : colMap){
            maxLen = Math.max(maxLen, l.size());
        }
        if(maxLen > 100) maxLen = 100;

        // 새로운 행들로 새로운 map 만들기. 길이가 최대보다 짧으면 0으로 채우기
        map = new int[maxLen][originCol];
        for(int j=0; j<originCol; j++){
            for(int i=0; i<maxLen; i++){
                if(colMap[j].size() <= i){
                    map[i][j] = 0;
                }else{
                    map[i][j] = colMap[j].get(i);
                }
            }
        }

        return map;
    }
}
