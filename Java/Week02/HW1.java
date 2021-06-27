package Week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

class Manager{
    private String name;
    private LocalDate hiredate;

    Manager(String name){
        this.name=name;
    }

    String getName(){
        return this.name;
    }

    LocalDate getDate(){
        return this.hiredate;
    }

    void setHireDate(int year, int month, int day){
        LocalDate tod=LocalDate.of(year,month,day);
        if(tod.getYear()>LocalDate.now().getYear()){
            year=2020;
            day=1;
            month=1;
        }
        else if(tod.getYear()==LocalDate.now().getYear()){
            if(tod.getDayOfYear()>LocalDate.now().getDayOfYear()){
                year=2020;
                day=1;
                month=1;
            }
        }
        this.hiredate=LocalDate.of(year,month,day);
    }

    int numberOfYearsInCompanyTill(LocalDate tod){
        int diff=Math.abs(tod.getYear()-this.hiredate.getYear());
        if(tod.getDayOfYear()<this.hiredate.getDayOfYear()){
            diff--;
        }
        
        return diff;
    }
}



public class HW1 {
    public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    String name = bufferedReader.readLine();

    int hiringYear = Integer.parseInt(bufferedReader.readLine().trim());

    int hiringMonth = Integer.parseInt(bufferedReader.readLine().trim());

    int hiringDay = Integer.parseInt(bufferedReader.readLine().trim());

    int currentYear = Integer.parseInt(bufferedReader.readLine().trim());

    int currentMonth = Integer.parseInt(bufferedReader.readLine().trim());

    int currentDay = Integer.parseInt(bufferedReader.readLine().trim());

    // Create an object of type Manager. Use one parameter constructor with argument name: Manager(name)
    Manager man = new Manager (name);
    // Call the set method to set the hiring date of Ivan. Pass year, month, and day as parameters: setHiringDate(LocalDate.of(hiringYear, hiringMonth, hiringDay))
    man.setHireDate(hiringYear,hiringMonth,hiringDay);
    // Print on the console the result of getting the hiring date
    System.out.println(man.getDate());
    // Print on the console the result of calling numberOfYearsInCompanyTill() method. The method has to be called with a parameter of type LocalDate using currentYear, currentMonth, currentDay: numberOfYearsInCompanyTill(LocalDate.of(currentYear, currentMonth, currentDay))
    System.out.println(man.numberOfYearsInCompanyTill(LocalDate.of(currentYear, currentMonth, currentDay)));
    bufferedReader.close();
    }
}
