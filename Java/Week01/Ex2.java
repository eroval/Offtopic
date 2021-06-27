package Week01;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Ex2 {
    
    public static void main(String args[]){
        Scanner myobj = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Enter date in format: dd/mm/yyyy");
        String userInput=myobj.nextLine();
        LocalDate date = LocalDate.parse(userInput,formatter);
        System.out.println("Number of days in month: " + date.lengthOfMonth());
    }  
}
