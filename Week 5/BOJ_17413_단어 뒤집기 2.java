import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] S = br.readLine().toCharArray();
        int sLength = S.length;
        StringBuilder sb = new StringBuilder();

        int x=0;

        while(x<sLength){
            //태그 안이면? -> 뒤집지 않음
            if (S[x] == '<') {
                while (x<sLength && S[x] != '>') {
                    sb.append(S[x++]);
                }
                sb.append(S[x++]);
            } else {
                // 단어면? -> 뒤집음
                StringBuilder temp = new StringBuilder();

                while(x<sLength && (S[x]!=' ' && S[x]!='<')){
                    temp.append(S[x++]);
                }

                sb.append(temp.reverse());

                if(x<sLength && S[x]==' ')
                    sb.append(S[x++]);
            }
        }


        System.out.println(sb);


    }
}
