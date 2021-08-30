import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine();
        String P = br.readLine();

        boolean part=false;

        char[] sArray = S.toCharArray();
        char[] pArray = P.toCharArray();

        int[] pi = new int[pArray.length];

        int sLength = sArray.length;
        int pLength = pArray.length;

        int j = 0;

        for(int i=1;i<pLength;i++){
            while(j>0 && pArray[i]!=pArray[j])
                j = pi[j - 1];

            if(pArray[i]==pArray[j])
                pi[i] = ++j;
        }

        j = 0;
        for(int i=0;i<sLength;i++){
            while(j>0 && pArray[j] != sArray[i])
                j = pi[j - 1];

            if(pArray[j]==sArray[i])
                j++;
            if(j==pLength){
                part = true;
                break;
            }
        }

        if(part)
            bw.write("1");
        else
            bw.write("0");

        bw.flush();

    }
}
