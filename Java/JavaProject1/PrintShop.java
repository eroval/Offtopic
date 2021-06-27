package JavaProject1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.naming.InsufficientResourcesException;

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

import JavaProject1.PrintedIssue.TypeOfPaper;
import jdk.javadoc.internal.doclets.toolkit.FieldWriter;
import jdk.jfr.events.FileWriteEvent;

public class PrintShop {


    private static final long serializableID = 4375749L;
    private String name;
    private BigDecimal minIncome;

    private BigInteger issueIdNumber;
    private int machineIdNumber;

    private LinkedHashSet<Issue> issues = new LinkedHashSet<Issue>();
    private List<Machine> machines = new ArrayList<>();
    private LinkedHashSet<Employee> employees = new LinkedHashSet<Employee>();


    private String shopDirectory;
    private String employeesFilesLocation;
    private String issuesFilesLocation;
    private String machinesFilesLocation;

    //-----------------------------------------CONSTRUCTORS-----------------------------------------//


    //DEFAULT CONSTRUCTOR
    PrintShop() throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter name of the printing shop: ");
        this.name = bf.readLine();                          //name

        do{
            System.out.print("Enter minimum income that should be reached: ");
        } while(!this.setMinIncome(bf.readLine()));
        
        do{
            System.out.print("Enter manager percentage bonus: ");
        } while(!Manager.setPercentage(bf.readLine()));

        do{
            System.out.print("Enter discount percentage for the printed issues: ");
        } while(!PrintedIssue.setPercentageBonus(bf.readLine()));

        do{
            System.out.print("Enter minimum number of printed issues for discount: ");
        } while(!PrintedIssue.setMinimumPrintedIssues(bf.readLine()));
        do{
            System.out.print("Enter base salary: ");
        } while(!Employee.setBaseSalary(bf.readLine()));


        this.issueIdNumber=BigInteger.valueOf(1);           //id issue
        this.machineIdNumber=1;                             //id machine
    }


    //CONSTRUCTOR WITH ALL VARIABLES
    PrintShop(String name, BigDecimal minIncome, int managerPercentage, int discountIssuePercentage, int minimumNumberOfPrintedIssues, double baseSalary){
        this.name=name;                                     //name
        this.setMinIncome(minIncome);                       //min income

        Manager.setPercentage(managerPercentage);
        PrintedIssue.setPercentageBonus(percentage);
        PrintedIssue.setMinimumNumberOfPrintedIssues(minimumNumberOfPrintedIssues);
        Employee.setBaseSalary(baseSalary);

        this.issueIdNumber=BigInteger.valueOf(1);           //id issue
        this.machineIdNumber=1;                             //id machine
    }



    //CONSTRUCTOR FROM FILE
    PrintShop(String shopFolderName){
        this.setDirectory(shopFolderName);
        System.out.println(this.shopDirectory);
        System.out.println(this.issuesFilesLocation);
        System.out.println(this.employeesFilesLocation);

        File newFile = new File(this.shopDirectory + shopFolderName + ".txt");
        System.out.println(newFile.getAbsolutePath());
        try (FileReader file = new FileReader(newFile)) {

            BufferedReader bf = new BufferedReader(file);
            String input = bf.lines().collect(Collectors.joining(System.lineSeparator()));
            this.name = input.split("Shop name: ")[1].split("\\r?\\n")[0];
            this.setMinIncome(new BigDecimal(input.split("Minimum income threshold: ")[1].split("\\r?\\n")[0]));
            Employee.setBaseSalary(Double.parseDouble(input.split("Base salary: ")[1].split("\\r?\\n")[0]));
            PrintedIssue.setMinimumNumberOfPrintedIssues(Integer.parseInt(input.split("Minimum number of printed issues for discount: ")[1].split("\\r?\\n")[0]));
            Manager.setPercentage(Integer.parseInt(input.split("Salary percentage bonus for managers: ")[1].split("\\r?\\n")[0]));
            PrintedIssue.setPercentageBonus(Integer.parseInt(input.split("Discount percentage: ")[1].split("\\r?\\n")[0]));
            this.issueIdNumber = new BigInteger(input.split("Global issue id: ")[1].split("\\r?\\n")[0]);
            this.machineIdNumber = Integer.parseInt(input.split("Global machine id: ")[1].split("\\r?\\n")[0]);

            File issueFolder = new File(this.issuesFilesLocation);
            File employeeFolder = new File(this.employeesFilesLocation);
            File machinesFolder= new File(this.machinesFilesLocation);

            if(issueFolder.exists()){
                for(final File issueFile : issueFolder.listFiles()){
                    if(issueFile.getAbsolutePath().contains(".txt")){
                        Issue issue = new Issue(issueFile.getAbsolutePath(), this.issuesFilesLocation);
                        this.addIssueFromFile(issue);
                    }
                }
            }
            

            if(employeeFolder.exists()){
                File operatorsFolder = new File(this.employeesFilesLocation+"\\Operators\\");
                File managersFolder = new File(this.employeesFilesLocation+"\\Managers\\");

                System.out.println(operatorsFolder.getAbsolutePath());
                System.out.println(managersFolder.getAbsolutePath());

                if(operatorsFolder.exists()){
                    for(final File operator : operatorsFolder.listFiles()){
                        Employee op = new Operator(operator);
                        this.addEmployee(op);
                    }
                }
                if(managersFolder.exists()){
                    for(final File manager : managersFolder.listFiles()){
                        System.out.println(manager);
                        Employee mg = new Manager(manager);
                        this.addEmployee(mg);
                    }
                }
            }

            if(machinesFolder.exists()){
                for(final File machine : machinesFolder.listFiles()){
                    Machine m = new Machine(this, machine);
                    this.addMachineFromFile(m);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No such shop exists or bad input.");
            throw new ClassCastException("Class wasn't created because such folder doesn't exist.");
        }
    }
    
    //--------------------------------------END OF CONSTRUCTORS-------------------------------------//



    
    //---------------------------------------ISSUES FUNCTIONS---------------------------------------//
    //                                                                                              //
    //                                                                                              //
    //                                                                                              //

    public void incrementIssueIdNumber(){
        this.issueIdNumber=this.issueIdNumber.add(BigInteger.ONE);
    }

    public BigInteger getIssueIdNumber(){
        return this.issueIdNumber;
    }

    //Function used not by the user(maybe should've done it private)
    public void addIssueFromFile(Issue obj){
        this.issues.add(obj);
    }

    //Adding issues functions
    public void addIssue() throws IOException{
        Issue object = new Issue(this.getIssueIdNumber());
        this.addIssue(object);
    }

    public void addIssue(Issue... objects){
        for(Issue a : objects){
            Issue newIssue = new Issue(this.issueIdNumber, a);
            if(this.issues.add(newIssue)){
                this.incrementIssueIdNumber();
            }
        }
    }

    //                                                                                              //
    //                                                                                              //
    //                                                                                              //
    //-----------------------------------END OF ISSUES FUNCTIONS------------------------------------//



    //--------------------------------------EMPLOYEE FUNCTIONS--------------------------------------//
    //                                                                                              //
    //                                                                                              //
    //                                                                                              //

    //Adding employees functions
    public void addEmployee() throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Employee object;
        System.out.println("What type of employee to add? (manager/operator)");
        String str = bf.readLine().toLowerCase();
        //System.out.println(str);
        while(!str.equals("manager")&&!str.equals("operator")){
            System.out.println("You've entered a wrong input. Please try again: ");
            str=bf.readLine().toLowerCase();
        }
        if(str.equals("operator")){
            object = new Operator();
        }
        else{
            object = new Manager();
        }
        this.employees.add(object);
    }

    public void addEmployee(Employee... objects){
        for(Employee a : objects){
            this.employees.add(a);
        }
    }

    //                                                                                              //
    //                                                                                              //
    //                                                                                              //
    //----------------------------------END OF EMPLOYEE FUNCTIONS-----------------------------------//



    //--------------------------------------MACHINES FUNCTIONS--------------------------------------//
    public void addMachine(Machine... machine){
        for(Machine a : machine){
            this.machines.add(a);
        }
    }

    public void addMachineFromFile(Machine a){
        this.machines.add(a);
    }
    
    public void addMachine() throws NumberFormatException, IOException{
        Machine newMachine = new Machine(this);
        this.machines.add(newMachine);
    }

    public void addMachine(int maxNumberOfPages, int pagesPerMinute){
        Machine newMachine = new Machine(this, maxNumberOfPages, pagesPerMinute);
        this.machines.add(newMachine);
    }

    public void incrementMachineIdNumber(){
        this.machineIdNumber++;
    }

    public int getMachineIdNumber(){
        return this.machineIdNumber;
    }


    public void startMachine(int number) throws InterruptedException{
        try{
            if(this.machines.isEmpty()){
                throw new InsufficientResourcesException("No machines in the shop.");
            }
            if(number-1>this.machines.size() || number-1<0){
                throw new Exception("Invalid machine id.");
            }
            this.machines.get(number-1).EnterData();
            Thread thread = new Thread(this.machines.get(number-1));
            thread.run();
            thread.join();
        }
        catch(InsufficientResourcesException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Invalid machine number.");
        }
    }

    public void startMachine(){
        try{
            if(this.machines.isEmpty()){
                throw new InsufficientResourcesException("No machines in the shop.");
            }
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter machine id: ");
            int number = Integer.parseInt(bf.readLine());
            if(number-1>this.machines.size() || number-1<0){
                throw new Exception("Invalid machine id.");
            }
            this.machines.get(number-1).EnterData();
            Thread thread = new Thread(this.machines.get(number-1));
            thread.run();
            thread.join();
        }
        catch(InsufficientResourcesException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Invalid input for machine number.");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


    public void startAllMachines() throws InterruptedException{
        for(Machine a : this.machines){
            a.EnterData();
        }

        Thread[] thread = new Thread[this.machines.size()];

        for(int i=0; i<this.machines.size(); i++){
            thread[i] = new Thread(this.machines.get(i));
            thread[i].start();
            Thread.sleep(50);
        }

        for(Thread t : thread){
            t.join();
        }

    }

    //-----------------------------------END OF MACHINES FUNCTIONS----------------------------------//

    //Setter for input
    public boolean setMinIncome(String input){
        try{
            BigDecimal minIncome = new BigDecimal(input);
            if(minIncome.compareTo(BigDecimal.ZERO)==-1){
                minIncome=BigDecimal.ZERO;
            }
            this.minIncome=minIncome.setScale(2,RoundingMode.HALF_UP);
            return true;
        }
        catch(Exception e){
            System.out.println("Invalid input for minimum income of the shop.");
            e.printStackTrace();
            return false;
        }
    }

    //Getters for issues
    public Set<Issue> getIssues(){
        return this.issues;
    }

    public Issue getIssueById(BigInteger number){
        try{
            for(Issue a : this.issues){
                if(a.getIdNumber()==number){
                    return a;
                }
            }
            throw new Exception("No element with such id found");
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void allPrintedIssues(){
        for(Issue a : this.issues){
            a.printAllPrintedIssues();
        }
    }

    //Setters/Getters
    public void setMinIncome(BigDecimal minIncome){
        if(minIncome.compareTo(BigDecimal.ZERO)==-1){
            minIncome=BigDecimal.ZERO;
        }
        this.minIncome=minIncome.setScale(2,RoundingMode.HALF_UP);;
    }

    public BigDecimal getMinIncome(){
        return this.minIncome;
    }
    
    public String getName(){
        return this.name;
    }



    //Files Locations Getters
    public String getShopDirectory(){
        return this.shopDirectory;
    }

    public String getIssuesFilesLocation(){
        return this.issuesFilesLocation;
    }

    public String getEmployeesFilesLocation(){
        return this.employeesFilesLocation;
    }

    public void printDirectories(){
        System.out.println(this.getShopDirectory());
        System.out.println(this.getIssuesFilesLocation());
        System.out.println(this.getEmployeesFilesLocation());
    }


    //Save shop to directory
    public void saveShopDirectory(){ 
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = null;                                                                   //variable used for user input
        File folder = null;                                                                    //folder
        String fileName = null;
        boolean writeToFile=false;                                                             //write or not

        try{
        //Folder for saving
        System.out.print("Choose name of the folder for saving the shop: ");
        input = bf.readLine();
        fileName=input;
        folder = new File (System.getProperty("user.dir") + "\\" + fileName);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        //System.out.println("SAVE FILE DIRECTORY: " + folder.getAbsolutePath());

        if(folder.exists()){
            try{
                do{
                    System.out.println("Would you like to overwrite the folder?");
                    input=bf.readLine().toLowerCase();
                } while(!input.equals("yes")&&!input.equals("no")) ;
                writeToFile=(input.equals("yes"));
            }
            catch(IOException e){
                e.printStackTrace();
                writeToFile=false;
            }
        }
        else{
        folder.mkdirs();
        writeToFile=true;
        }

        if(writeToFile){
            this.setDirectory(fileName);

            System.out.println(this.shopDirectory);
            System.out.println(this.issuesFilesLocation);
            System.out.println(this.employeesFilesLocation);

            System.out.println(this.shopDirectory + fileName + ".txt");
            try (FileWriter shopFile = new FileWriter(this.shopDirectory + fileName + ".txt")) {
                shopFile.write(this.toString());
                this.saveIssues();
                this.createEmployeeDirectory();
                this.saveEmployees();
                this.createMachinesFolder();
                this.saveMachines();
                this.saveCatalogue();
            }
            
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    //File Locations Setter
    private void setDirectory(String shopFolderName){
        this.shopDirectory = System.getProperty("user.dir") + "\\" + shopFolderName +"\\";      //shop directory
        this.issuesFilesLocation = this.shopDirectory+"Issues\\";                               //issues directory
        this.employeesFilesLocation = this.shopDirectory+"Employees\\";                         //employees directory
        this.machinesFilesLocation = this.shopDirectory + "Machines\\";                         //machines directory
    }


    //Save Issues 
    private void saveIssues(){
        for(Issue a : this.issues){
            a.saveToFile(this.issuesFilesLocation);
        }
    }

    //Save Catalogue
    private void saveCatalogue(){
        try(FileWriter catalogueFile = new FileWriter(this.shopDirectory + "Catalogue.txt")){
            catalogueFile.write(this.catalogueToString());
        }catch(Exception e){
            System.out.println("Problem saving the catalogue file.");
            e.printStackTrace();
        }
    }

    //Create folders for employees
    private void createEmployeeDirectory(){
        File employeeFolder = new File(this.employeesFilesLocation);
        if(!employeeFolder.exists()){
            employeeFolder.mkdirs();
        }
        File operatorFolder = new File(this.employeesFilesLocation + "Operators\\");
        File managerFolder = new File(this.employeesFilesLocation + "Managers\\");

        if(!operatorFolder.exists()){
            operatorFolder.mkdirs();
        }

        if(!managerFolder.exists()){
            managerFolder.mkdirs();
        }
    }



    //Save employees
    private void saveEmployees(){
        BigInteger opNum=BigInteger.ONE;
        BigInteger mgNum=BigInteger.ONE;
        for(Employee a : this.employees){
            switch(a.getClass().getSimpleName()){
                case "Operator":
                    a.saveToFile(this.getEmployeesFilesLocation() + "\\Operators\\" + opNum + ".txt");
                    opNum = opNum.add(BigInteger.ONE);
                    break;
                case "Manager":
                    a.saveToFile(this.getEmployeesFilesLocation() + "\\Managers\\" + mgNum + ".txt");
                    mgNum = mgNum.add(BigInteger.ONE);
                    break;
                default:
                    break;
            }
        }
    }


    private void createMachinesFolder(){
        File machinesFolder = new File(this.machinesFilesLocation);
        if(!machinesFolder.exists()){
            machinesFolder.mkdirs();
        }
    }

    private void saveMachines(){
        for(Machine a : this.machines){
            a.saveToFile(this.machinesFilesLocation);
        }
    }

    public void printIssuesWithName(String name){
        for(Issue a: this.issues){
            if(a.getName()==name){
                System.out.println(a);
            }
        }
    }

    //Employee expense
    public BigDecimal employeeCost(){
        int percentage = Manager.getPercentage();
        if(this.totalIssueProfit().compareTo(minIncome)==-1){
            Manager.setPercentage(0);
        }
        BigDecimal value = new BigDecimal(0);
        for(Employee a : employees){
            value = value.add(BigDecimal.valueOf(a.salary()));
        }
        Manager.setPercentage(percentage);
        return value;
    }

    //Issue expenses
    public BigDecimal totalIssueCost(){
        BigDecimal value = new BigDecimal(0);
        for(Issue issue : this.issues){
            for(PrintedIssue a : issue.getAllPrintedIssues()){
                value = value.add(a.TotalCost());
            }
        }
        return value;
    }

    //Issue income
    public BigDecimal totalIssueProfit(){
        BigDecimal value = new BigDecimal(0);
        for(Issue issue : this.issues){
            for(PrintedIssue a : issue.getAllPrintedIssues()){
                value = value.add(a.TotalSells());
            }
        }
        return value;
    }


    public void printIssues(){
        for(Issue a : this.issues){
            System.out.println();
            a.print();
        }
    }

    //toString used for saving the catalogue in a separate file
    public String catalogueToString(){
        String str = "Catalogue:";
        if(this.issues.isEmpty()){
            str+="\n-------------------------\n\tEMTPY\n";
            return str;
        }
        else {
            for(Issue a: this.issues){
                str+="\n-------------------------\n" + a;
                str+="\n\tAll printed issues:";
                if(a.getAllPrintedIssues().isEmpty()){
                    str+="\n\t-------------------------\n\tEMTPY";
                }
                else {
                    for(PrintedIssue b : a.getAllPrintedIssues()){
                        str+="\n\t-------------------------\n" + b.toCatalogue() + "\n";
                    }
                }
            }
        }
        return str;
    }
    //prints the whole catalogue with all printed editions
    public void printCatalogue(){
        System.out.print("\n\nCatalogue:");
        if(this.issues.isEmpty()){
            System.out.print("\n-------------------------\n\tEMTPY\n");
        }
        else {
            for (Issue a : this.issues){
                System.out.println("\n-------------------------");
                a.print();
                System.out.print("\n\tAll printed issues:");
                if(a.getAllPrintedIssues().isEmpty()){
                    System.out.print("\n\t-------------------------\n\tEMTPY");
                }
                else {
                    for(PrintedIssue b : a.getAllPrintedIssues()){
                        System.out.println("\n\t-------------------------");
                        b.print();
                    }
                }
                System.out.println();
            }
        }
    }

    //prints all employees
    public void printEmployees(){
        System.out.print("\nEmployees:\n-------------------------\n");
        if(this.employees.isEmpty()){
            System.out.print("\tEMTPY\n");
        }
        for(Employee a : this.employees){
            System.out.print(a);
        }
    }

    public void printAllMachines(){
        System.out.print("\n\nMachines:");
        if(this.machines.isEmpty()){
            System.out.println("\n\t-------------------------\n\tEMTPY\n");
        }
        for(Machine a : this.machines){
            System.out.println("\n-------------------------");
            a.print();
        }
    }
    
    public void print(){
        System.out.println("==================================================\nName of shop: " + this.name);
        System.out.println("Minimum income threshold: " + this.minIncome);
        System.out.println("Employee expenses: " + this.employeeCost());
        System.out.println("Expenses for all issues: " + this.totalIssueCost());
        System.out.println("Profit from all issues: " + this.totalIssueProfit());
        System.out.println("Total profit: " + this.totalIssueProfit().subtract(this.employeeCost()));
        this.printCatalogue();
        this.printEmployees();
        this.printAllMachines();
        System.out.println("==================================================\n\n");
    }

    
    public String toString(){
        String str = "=========================\nShop name: " + this.name;
        str+="\nMinimum income threshold: " + this.minIncome;
        str+="\nMinimum number of printed issues for discount: " + PrintedIssue.getMinimumNumberOfPrintedIssues();
        str+="\nDiscount percentage: " + PrintedIssue.getPercentage();
        str+="\nBase salary: " + Employee.getBaseSalary();
        str+="\nSalary percentage bonus for managers: " + Manager.getPercentage();
        str+="\nGlobal issue id: " + this.issueIdNumber;
        str+="\nGlobal machine id: " + this.machineIdNumber;
        str+="\n-------------------------\nEmployee expenses: " + this.employeeCost();
        str+="\nExpenses for all issues: " + this.totalIssueCost();
        str+="\nProfit from all issues: " + this.totalIssueProfit();
        str+="\nTotal profit: " + this.totalIssueProfit().subtract(this.employeeCost());
        return str+"\n=========================\n\n\n";
    }
}
