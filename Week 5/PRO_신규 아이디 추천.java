class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        // 1단계 ~ 4단계
        new_id=new_id.toLowerCase().replaceAll("[^a-z0-9-_.]","").replaceAll("\\.+",".").replaceAll("^[.]|[.]$","");
        
        // 6단계
        if(new_id.length()>=16){
            new_id=new_id.substring(0,15).replaceAll("[.]$","");
        }
        
        // 5단계 + 7단계
        if(new_id.length()<=2){
            if(new_id.length()==0)
                new_id="a";
            
            while(new_id.length()!=3)
                new_id+=new_id.charAt(new_id.length()-1);
        }
        
        answer=new_id;
        
        return answer;
    }
}
