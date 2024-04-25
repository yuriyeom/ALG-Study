class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        
        int attackIdx = 0;
        int attackCnt = 0;
        int nowHealth = health;
        int time = 0;
        
        while(attackIdx < attacks.length){
            time++;
            if(nowHealth <= 0) break;
            
            // 몬스터 공격
            if(time == attacks[attackIdx][0]){
                nowHealth -= attacks[attackIdx][1];
                attackIdx++;
                attackCnt = 0;
            }
            else{
                attackCnt++;
                nowHealth += bandage[1];
                
                if(attackCnt == bandage[0]){
                    nowHealth += bandage[2];
                    attackCnt = 0;
                }
                
                if(nowHealth > health) nowHealth = health;
            }
        }
        
        return nowHealth <= 0 ? -1 : nowHealth;
    }
}