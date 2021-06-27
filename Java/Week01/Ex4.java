package Week01;

import java.util.Scanner;

public class Ex4 {
    public static void main(String args[]){
        Scanner myobj = new Scanner(System.in);
        int bottom, top;
        do{
            System.out.print("Bottom = ");
            bottom=myobj.nextInt();
            System.out.print("Top = ");
            top=myobj.nextInt();
        }
        while(bottom>top||bottom<0||top<0);

        if(bottom%2==0){
            for(int i=bottom+1; i<=top; i+=2){
                System.out.println(i);
            }
        }
        else {
            for(int i=bottom; i<=top; i+=2){
                System.out.println(i);
            }
        }
    }
}
