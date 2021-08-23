class Solution {
    
    public static int[] stack;
    public static int top=-1;
    
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        
        stack=new int[n];
        
        int cmdLength=cmd.length;
        
        int length=n;
        
        for(int x=0;x<cmdLength;x++){
            if(cmd[x].charAt(0)=='C'){
                length--;
                stack[++top]=k;
                
                if(k==length)
                    k--;
            }else if(cmd[x].charAt(0)=='Z'){
                length++;
                if(stack[top]<=k)
                    k++;
                top--;
            }else {
                int plus=Integer.parseInt(cmd[x].split(" ")[1]);
                plus%=length;
                
                if(cmd[x].charAt(0)=='U'){
                    k=(k+length-plus)%length;
                }else{
                    k=(k+plus)%length;
                }
                
            }
        }
        
        StringBuilder sb=new StringBuilder();
        
        for(int x=0;x<length;x++)
            sb.append("O");
        
        for(int x=top;x>=0;x--){
            sb.insert(stack[x],"X");
        }
        
        answer=sb.toString();
        
        return answer;
    }
}
