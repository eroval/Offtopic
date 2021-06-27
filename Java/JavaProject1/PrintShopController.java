package JavaProject1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class PrintShopController {

    private boolean shopFromFile() throws IOException{
        String input;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter shop folder: ");
        String shopFolder = bf.readLine();
        try{
            PrintShop printShop = new PrintShop(shopFolder);
            boolean continueExecution=true;
            while(continueExecution){
                System.out.println("1. Create a new issue.");
                System.out.println("2. Add an employee.");
                System.out.println("3. Add a machine.");
                System.out.println("4. Start a machine.");
                System.out.println("5. Start all machines.");
                System.out.println("6. Print catalogue.");
                System.out.println("7. Print whole shop.");
                System.out.println("8. Save shop to folder.");
                System.out.println("0. Stop loading the shop.");
                System.out.print("\nEnter command: ");
                input=bf.readLine().toLowerCase();
                try{
                    switch(input){
                        case "1": case "create a new issue": case "create new issue": case "create issue":
                            printShop.addIssue();
                            break;
                        case "2": case "add an employee": case "create new employee": case "employee": case "add employee": case "create a new employee":
                            printShop.addEmployee();
                            break;
                        case "3": case "add machine": case "add a machine": case "add new machine": case "new machine":
                            printShop.addMachine();
                            break;
                        case "4": case "start machine": case "start a machine": case "machine start":
                            printShop.startMachine();
                            break;
                        case "5": case "start all machines": case "start machines": case "all machines start": case "all machines":
                            printShop.startAllMachines();
                            break;
                        case "6": case "print catalogue":
                            printShop.printCatalogue();
                            break;
                        case "7": case "print shop": case "print whole shop":
                            printShop.print();
                            break;
                        case "8": case "save shop": case "save folder": case "save": case "save shop to folder":
                            printShop.saveShopDirectory();
                            break;
                        case "0": case "stop": case "stop loading": case "stop shop": case "stop loading the shop": case "stop loading shop":
                            continueExecution=false;
                            break;
                        default: 
                            throw new Exception("No such command.");
                    } 
                } catch(Exception e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }
        catch(ClassCastException e){
                e.printStackTrace();
                System.out.println(e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        do{
            System.out.println("Would you like to load a new shop?");
            input=bf.readLine().toLowerCase();
        } 
        while(!input.equals("yes")&&!input.equals("no"));
        return input.equals("yes");
    }

    private boolean shopFromInput() throws IOException{ 
        String input;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try{
            PrintShop printShop = new PrintShop();
            boolean continueExecution=true;
            while(continueExecution){
                System.out.println("1. Create a new issue.");
                System.out.println("2. Add an employee.");
                System.out.println("3. Add a machine.");
                System.out.println("4. Start a machine.");
                System.out.println("5. Start all machines.");
                System.out.println("6. Print catalogue.");
                System.out.println("7. Print whole shop.");
                System.out.println("8. Save shop to folder.");
                System.out.println("0. Stop loading the shop.");
                System.out.print("\nEnter command: ");
                input=bf.readLine().toLowerCase();
                try{
                    switch(input){
                        case "1": case "create a new issue": case "create new issue": case "create issue":
                            printShop.addIssue();
                            break;
                        case "2": case "add an employee": case "create new employee": case "employee": case "add employee": case "create a new employee":
                            printShop.addEmployee();
                            break;
                        case "3": case "add machine": case "add a machine": case "add new machine": case "new machine":
                            printShop.addMachine();
                            break;
                        case "4": case "start machine": case "start a machine": case "machine start":
                            printShop.startMachine();
                            break;
                        case "5": case "start all machines": case "start machines": case "all machines start": case "all machines":
                            printShop.startAllMachines();
                            break;
                        case "6": case "print catalogue":
                            printShop.printCatalogue();
                            break;
                        case "7": case "print shop": case "print whole shop":
                            printShop.print();
                            break;
                        case "8": case "save shop": case "save folder": case "save": case "save shop to folder":
                            printShop.saveShopDirectory();
                            break;
                        case "0": case "stop": case "stop loading": case "stop shop": case "stop loading the shop": case "stop loading shop":
                            continueExecution=false;
                            break;
                        default: 
                            throw new Exception("No such command.");
                    } 
                } catch(Exception e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }
        catch(ClassCastException e){
                e.printStackTrace();
                System.out.println(e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


        do{
            System.out.println("Would you like to load a new shop?");
            input=bf.readLine().toLowerCase();
        } 
        while(!input.equals("yes")&&!input.equals("no"));
        return input.equals("yes");
    }



    private int LoadShop(){
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1. Create a new shop.");
        System.out.println("2. Load shop from folder.");
        System.out.println("0. Exit");
        System.out.print("Enter command: ");
        String input;
        try{
            input = bf.readLine().toLowerCase();
            switch(input){
                case "1": case "new": case "new shop": case "create": case "create a new shop from folder": case "create a new shop": case "create new shop":
                    return 1;
                case "2": case "load": case "load shop": case "from folder": case "load shop from folder": case "load a new shop from folder":
                    return 2;
                case "0": case "exit": case "quit": case "exit program": case "stop":
                    return 0;
                default:
                    throw new Exception("No such command.");
            }
        }

        catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return -1;
        }
    }

    PrintShopController() throws IOException{
        boolean continueExecution=true;

        while(continueExecution){
            try {
                switch(LoadShop()){
                    case 1:
                        continueExecution = shopFromInput();
                        break;
                    case 2:
                        continueExecution = shopFromFile();
                        break;
                    case 0:
                        continueExecution=false;
                        break;
                    case -1:
                        break;
                    default:
                    throw new Exception("Something went wrong. Unexpected error.");
                }
            }
            catch(Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                continueExecution=false;
            }
        }
    }
}
