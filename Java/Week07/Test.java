package Week07;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Style;

import Week07.Dancer.DancerStyle;

public class Test {
    
    public static void main(String args[]){
        Dancer.setNeededYears(4);
        Dancer hank = new Dancer("Hank" , LocalDate.parse("2017-01-03"), DancerStyle.LATINO);
        hank.print();
        Dancer tom = new Dancer("Tom", LocalDate.parse("2017-01-03"),DancerStyle.BALLET);
        tom.print();
        System.out.print("Is Tom with more experience than Hank = " + tom.withGreaterExperience(hank) + "\n\n");


        DancingClub club = new DancingClub("Nirvana", 30, 3000, 40);
        club.print();
        club.addDancer(hank);
        club.print();
        System.out.println("BALLET INCOME : " + club.incomeBasedOnStyle(DancerStyle.BALLET));
        System.out.println("LATINO INCOME : " + club.incomeBasedOnStyle(DancerStyle.LATINO));
        System.out.println("TRADITIONAL INCOME : " + club.incomeBasedOnStyle(DancerStyle.TRADITIONAL));


        club.addDancer(tom);


        DancingClub club2 = new DancingClub("Looper", 40, 250, 500);
        club2.addDancer(tom);
        club2.addDancer(hank);
        club2.print();
        System.out.println("BALLET INCOME : " + club2.incomeBasedOnStyle(DancerStyle.BALLET));
        System.out.println("LATINO INCOME : " + club2.incomeBasedOnStyle(DancerStyle.LATINO));
        System.out.println("TRADITIONAL INCOME : " + club2.incomeBasedOnStyle(DancerStyle.TRADITIONAL));



        System.out.println("The club with more income is: " + club2.greaterIncome(club).getName());

        Dancer fernando = new Dancer("Fernando" , LocalDate.parse("2012-12-03"), DancerStyle.TRADITIONAL);
        System.out.print("Is Tom with more experience than Hank = " + fernando.withGreaterExperience(hank) + "\n");
        System.out.print("Is Tom with more experience than Tom = " + fernando.withGreaterExperience(tom) + "\n");


        club2.addDancer(fernando);

        club2.print();
        System.out.println("BALLET INCOME : " + club2.incomeBasedOnStyle(DancerStyle.BALLET));
        System.out.println("LATINO INCOME : " + club2.incomeBasedOnStyle(DancerStyle.LATINO));
        System.out.println("TRADITIONAL INCOME : " + club2.incomeBasedOnStyle(DancerStyle.TRADITIONAL));

        DancingClub club3 = club2;
        club3.print();

    }
}
