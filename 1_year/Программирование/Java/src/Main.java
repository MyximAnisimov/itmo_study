import java.util.Scanner;

import static java.lang.Math.pow;

public class Main {
    public static void main(String[] args) {
int a[]={0,0,0,0,0,1,1,1};
int b=0;
int n=7;
for(int i=0;i<8;i++){
   b+=a[i]*pow(2,n);
   n=n-1;
}
System.out.println(b);
    }
}