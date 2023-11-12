package swea;

import java.io.*;
import java.util.*;

class S17937
{
  public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;
    int T = Integer.parseInt(br.readLine());
    for(int t = 1; t <= T; t++)
    {
      st = new StringTokenizer(br.readLine());
      String s1 = st.nextToken();
      String s2 = st.nextToken();
      sb.append("#").append(t).append(" ");
      if(s1.equals(s2)) {
        sb.append(s1).append("\n");
      } else {
        sb.append("1").append("\n");
      }
    }
    bw.write(sb.toString());
    bw.flush();
    bw.close();
    br.close();
  }
}