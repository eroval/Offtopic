package Week01;

import java.util.Scanner;

public class Ex1 { 
    public static void main(String args[]){
        Scanner obj = new Scanner(System.in);
        int leapYear=obj.nextInt();
        leapYear = leapYear%4==0?leapYear:-1;
        System.out.println(leapYear);
    }
}

