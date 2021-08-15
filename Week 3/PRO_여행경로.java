import java.util.*;

class Solution {
    
    public static String[] answer;
    public static int length;
    public static HashMap<String,Integer> map=new HashMap<>();
    public static ArrayList<ArrayList<String>> list=new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        
        length=tickets.length;
        answer=new String[length+1];
        
        Arrays.sort(tickets,new Comparator<String[]>(){
            @Override
            public int compare(String[] o1,String[] o2){
                if(o1[0].equals(o2[0])){
                    return o1[1].compareTo(o2[1]);
                
                }
                return o1[0].compareTo(o2[0]);
            }
        });
        


        int idx=-1;
            
        for(int x=0;x<length;x++){
            if(!map.containsKey(tickets[x][0])){
                map.put(tickets[x][0],++idx);
                list.add(new ArrayList<>());
            }
            list.get(idx).add(tickets[x][1]);
        }
        
        answer[0]="ICN";
        
        dfs(map.get("ICN"),1);
        
        return answer;
    }
    
    public static boolean dfs(int start, int count){
        if(count==length){
            if(list.get(start).size()==1){
                answer[length]=list.get(start).get(0);
                return true;
            }
            return false;
        }
        
        int size=list.get(start).size();
        
        for(int x=0;x<size;x++){
            String next=list.get(start).get(x);
            
            if(!map.containsKey(next))
                continue;
            
            answer[count]=next;
            list.get(start).remove(x);
            if(dfs(map.get(next),count+1))
                return true;
            
            list.get(start).add(x,next);
        
        }
        
        return false;
    }
    
}
