class Solution {
    public int solution(int n) {
        int answer = 0;
        
    
        int prevOne, prevTwo,temp;
        
        prevOne=1;
        prevTwo=2;
        
        for(int x=3;x<=n;x++){
            temp=(prevOne+prevTwo)%1000000007;
            prevOne=prevTwo;
            prevTwo=temp;
        }
        
        answer=prevTwo;
        
        return answer;
    }
}
