import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

enum EmployeeStatus{
    PERMANENT(BigDecimal.valueOf(0)), 
    PART_TIME(BigDecimal.valueOf(0)), 
    TRAINEE(BigDecimal.valueOf(0));

    private BigDecimal value;
    private EmployeeStatus(BigDecimal value){
        this.value=value;
    }
    
    public BigDecimal getValue(){
        return this.value;
    }

    public BigDecimal setValue(BigDecimal value){
        return this.value=value;
    }
}

class Employee{

    private String name;
    private int hours;
    private EmployeeStatus status;
    private BigDecimal additionalSalary;

    Employee(String name, int hours, EmployeeStatus status, BigDecimal additionalSalary){
        this.name=name;
        if(hours<=0){
            hours=0;
        }
        this.hours=hours;
        this.status=status;
        this.additionalSalary=additionalSalary;
    }

    //namesList, workedHoursList, contractTypeList, and additionalSalaryList
    public BigDecimal salary(){
        return this.additionalSalary.add(this.status.getValue()).multiply(BigDecimal.valueOf(hours)).setScale(2, RoundingMode.HALF_UP);
    }

    public void increaseSalary(double bonus){
        if(bonus<=0) return;
        bonus/=100;
        this.additionalSalary = this.additionalSalary.add(this.additionalSalary.multiply(BigDecimal.valueOf(bonus))).setScale(2, RoundingMode.HALF_UP);
    }
    
    public EmployeeStatus getStatus(){
        return this.status;
    }
}

class Company{
    Set<Employee> employees;
    private String name;
    private long maxEmployees;

    private static Company single_instance=null;
    private Company(){
        this.employees = new HashSet<Employee>();
    }

    public static Company getInstance(){
        if(single_instance==null){
            single_instance=new Company();
        }
        return single_instance;
    }

    void init(String name, long maxEmployees){
        this.name = name;
        if(maxEmployees<0){
            maxEmployees=0;
        }
        this.maxEmployees=maxEmployees;
    }

    void hireEmployee(Employee obj){
        if(this.employees.size()<this.maxEmployees){
        this.employees.add(obj);
        }
    }

    void fireEmployee(Employee obj){
        if(this.employees.isEmpty()){
            return;
        }
        this.employees.remove(obj);
    }

    public BigDecimal averageSalary(){
        BigDecimal Sum = new BigDecimal("0");
        if(employees.isEmpty()){return Sum;}
        int i = 0;  
        for (Employee temp : employees) {
            if(temp.salary().compareTo(BigDecimal.valueOf(0))==1){
                Sum =Sum.add(temp.salary());
                i++;
            }
        }
        return Sum.divide(BigDecimal.valueOf(i),2,RoundingMode.HALF_UP);
    }

    public BigDecimal averageSalaryByType(EmployeeStatus status){
        BigDecimal Sum = new BigDecimal("0");
        if(employees.isEmpty()){return Sum;}
        boolean flag=true;
        int i = 0;  
        for (Employee temp : employees) {
            if(temp.getStatus()==status){
                if(temp.salary().compareTo(BigDecimal.valueOf(0))==1){
                    Sum =Sum.add(temp.salary());
                    i++;
                    flag=false;
                }
            }
        }
        if(flag)return BigDecimal.valueOf(0);
        return Sum.divide(BigDecimal.valueOf(i),2,RoundingMode.HALF_UP);
    }

    public void increaseSalaries(double bonus){
        if(employees.isEmpty()){
            return;
        }
        for (Employee temp : employees) {
            temp.increaseSalary(bonus);
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String companyName = bufferedReader.readLine();

        int maxNumberOfEmployees = Integer.parseInt(bufferedReader.readLine().trim());

        List<Double> minSalaryList = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Double::parseDouble)
            .collect(toList());

        List<String> namesList = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .collect(toList());

        List<Integer> workedHoursList = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<String> contractTypeList = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .collect(toList());

        List<Double> additionalSalaryList = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Double::parseDouble)
            .collect(toList());

        String contractType = bufferedReader.readLine();

        double increasePercentage = Double.parseDouble(bufferedReader.readLine().trim());
        // Set the min salary per hour, that depends on the contract type. Use the values in the minSalaryList: the first value is for PERMANENT, the second is for PART_TIME, the third is for TRAINEE
        if(minSalaryList.get(0)<0){
            minSalaryList.set(0,0.0);
        }
        if(minSalaryList.get(1)<0){
            minSalaryList.set(1,0.0);
        }
        if(minSalaryList.get(2)<0){
            minSalaryList.set(2,0.0);
        }
        EmployeeStatus permanent=EmployeeStatus.PERMANENT;
        permanent.setValue(BigDecimal.valueOf(minSalaryList.get(0)));
        
        
        EmployeeStatus parttime=EmployeeStatus.PART_TIME;
        parttime.setValue(BigDecimal.valueOf(minSalaryList.get(1)));
        
        
        EmployeeStatus trainee=EmployeeStatus.TRAINEE;
        trainee.setValue(BigDecimal.valueOf(minSalaryList.get(2)));
        
        // Create company by using companyName and maxNumberOfEmployees
        Company employees = Company.getInstance();
        employees.init(companyName, maxNumberOfEmployees);
        // Create 6 employees by using the values in the namesList, workedHoursList, contractTypeList, and additionalSalaryList. Example for creating the first employee:  Employee employee1 = new Employee(namesList.get(0), workedHoursList.get(0), BigDecimal.valueOf(additionalSalaryList.get(0)), ContractType.valueOf(contractTypeList.get(0)));
        Employee number1= new Employee(namesList.get(0), workedHoursList.get(0), 
        EmployeeStatus.valueOf(contractTypeList.get(0)),BigDecimal.valueOf(additionalSalaryList.get(0)));
        Employee number2= new Employee(namesList.get(1), workedHoursList.get(1), EmployeeStatus.valueOf(contractTypeList.get(1)),BigDecimal.valueOf(additionalSalaryList.get(1)));
        Employee number3= new Employee(namesList.get(2), workedHoursList.get(2), EmployeeStatus.valueOf(contractTypeList.get(2)),BigDecimal.valueOf(additionalSalaryList.get(2)));
        Employee number4= new Employee(namesList.get(3), workedHoursList.get(3), EmployeeStatus.valueOf(contractTypeList.get(3)),BigDecimal.valueOf(additionalSalaryList.get(3)));
        Employee number5= new Employee(namesList.get(4), workedHoursList.get(4), EmployeeStatus.valueOf(contractTypeList.get(4)),BigDecimal.valueOf(additionalSalaryList.get(4)));
        Employee number6= new Employee(namesList.get(5), workedHoursList.get(5), EmployeeStatus.valueOf(contractTypeList.get(5)),BigDecimal.valueOf(additionalSalaryList.get(5)));
        // Hire all the 6 employees to work at the company
        employees.hireEmployee(number1);
        employees.hireEmployee(number2);
        employees.hireEmployee(number3);
        employees.hireEmployee(number4);
        employees.hireEmployee(number5);
        employees.hireEmployee(number6);
        // Print the result of calling the method averageSalary()
        System.out.println(employees.averageSalary());
        // Increase the additional salary per hour, by calling the method increaseSalaries(). Use increasePercentage as an argument: company.increaseSalaries(BigDecimal.valueOf(increasePercentage));
        employees.increaseSalaries(increasePercentage);
        // Print the result of calling the method averageSalary(), after increasing the additional salary per hour of all employees.
        System.out.println(employees.averageSalary());
        // Print the result of calling the method averageSalaryByType(). Use the string contractType with ContractType.valueOf() method to pass it as argument: System.out.println(company.averageSalaryByType(ContractType.valueOf(contractType)));
        System.out.println(employees.averageSalaryByType(EmployeeStatus.valueOf(contractType)));

        bufferedReader.close();
    }
}
