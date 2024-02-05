class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        int max = arr[arr.length-1];
        while(true){
            boolean find = true;
            arr[arr.length-1] += max;
            for(int i=0; i<arr.length-1; i++){
                if(arr[arr.length-1] % arr[i] != 0){
                    find = false;
                    break;
                }
            }
            if(find) break;
        }
        
        return arr[arr.length-1];
    }
}