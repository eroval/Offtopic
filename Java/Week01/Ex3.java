package Week01;

import java.math.BigDecimal;
import java.util.Scanner;

public class Ex3 {
    public static void main(String args[]){
        Scanner myobj = new Scanner(System.in);
        System.out.println("Enter coefficient in front of x^2: ");
        double a = myobj.nextDouble();
        System.out.println("Enter coefficient in front of x: ");
        double b = myobj.nextDouble();
        System.out.println("Enter c: ");
        double c = myobj.nextDouble();
        double D = b*b-(4*a*c);

        double x1=0,x2=0;
        if(D==0){
            x1=x2=-b/(2*a);
            System.out.println("X1 = "+ x1 + " X2 = "+ x2);
        }
        else if(D<0){
            System.out.println("Does not have real roots");
        }
        else {
            x1=(-b+Math.sqrt(D))/(2*a);
            x2=(-b-Math.sqrt(D))/(2*a);
            System.out.println("X1 = "+ x1 + " X2 = "+ x2);
        }
    }
}
