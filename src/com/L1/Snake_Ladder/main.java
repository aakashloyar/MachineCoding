package com.L1.Snake_Ladder;
import java.util.*;
import java.io.*;
public class main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        StringTokenizer s1=new StringTokenizer(br.readLine());
        int s_cnt=Integer.parseInt(s1.nextToken());
        int[][] snake=new int[s_cnt][2];
        for(int i=0;i<s_cnt;i++) {
            StringTokenizer s2=new StringTokenizer(br.readLine());
            snake[i][0]=Integer.parseInt(s2.nextToken());
            snake[i][1]=Integer.parseInt(s2.nextToken());
        }
        StringTokenizer s2=new StringTokenizer(br.readLine());
        int l_cnt=Integer.parseInt(s2.nextToken());
        int[][] ladder=new int[l_cnt][2];
        for(int i=0;i<l_cnt;i++) {
            StringTokenizer s3=new StringTokenizer(br.readLine());
            ladder[i][0]=Integer.parseInt(s3.nextToken());
            ladder[i][1]=Integer.parseInt(s3.nextToken());
        }
        StringTokenizer s3=new StringTokenizer(br.readLine());
        int p_cnt=Integer.parseInt(s3.nextToken());
        StringBuilder[] sb=new StringBuilder[p_cnt];
        for(int i=0;i<p_cnt;i++) {
            sb[i]=new StringBuilder(br.readLine());
        }
        solve(sb,snake,ladder,s_cnt,l_cnt,p_cnt);
    }
    static void solve(StringBuilder[] sb,int[][] snake,int[][] ladder,int s_cnt,int l_cnt,int p_cnt) {
        SL sl=new SL(100);
        sl.build(snake,ladder);
        sl.init(p_cnt);
        int t=0;
        while(true) {
            int inc=6;
            while(inc==6) {
                inc = sl.dice();
                int s=sl.p[t];
                sl.play(t,inc);
                int e=sl.p[t];
                System.out.println(sb[t]+" rolled a "+inc +" moved from "+s+" to "+e);
                if(sl.p[t]==100) break;
            }
            if(sl.p[t]==100) break;
            t++;
            t%=p_cnt;
        }
        System.out.println(sb[t]+" won");
    }
}
class SL {
    int n;
    int[] snake;
    int[] ladder;
    int p_cnt;
    int[] p;
    SL(int n) {
        this.n=n;
        snake=new int[n+1];
        ladder=new int[n+1];
        for(int i=1;i<=n;i++) {
            snake[i]=i;
            ladder[i]=i;
        }
        //System.out.println(Arrays.toString(snake));
        //System.out.println(Arrays.toString(ladder));
    }
    void build(int[][] sna,int[][] lad) {
        for(int i=0;i<sna.length;i++) snake[sna[i][0]]=sna[i][1];
        for(int i=0;i<lad.length;i++) ladder[lad[i][0]]=lad[i][1];
    }
    void init(int p) {
        this.p_cnt=p;
        this.p=new int[p_cnt];
    }
    int dice() {
        int x=(int)(Math.random()*6)+1;
        //System.out.println(x);
        return x;
    }
    void play(int t,int inc) {
        int curr=p[t];
        if(curr==0) {
            if(inc!=6) return;
            else p[t]=1;
            return;
        }
        curr+=inc;
        if(curr>n) return;
        while(snake[curr]!=curr ||ladder[curr]!=curr) {
            curr=snake[curr];
            curr=ladder[curr];
        }
        p[t]=curr;
    }
}
