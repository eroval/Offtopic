package JavaProject1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.ProcessBuilder.Redirect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Issue { 

    public static enum TypeOfIssue{
        NEWSPAPER(0),
        BOOK(1),
        DISSERTATION(2),
        POSTER(3),
        BROCHURE(4),
        FLYER(5),
        CALENDAR(6),
        VOUCHER(7),
        CARD(8);

        private int number;

        TypeOfIssue(int number){
            this.number=number;
        }

        public int getValue(){
            return this.number;
        }
    }

    public static enum SizeOfPaper{
        A1(0.25, 10),
        A2(0.15, 20),
        A3(0.06, 30),
        A4(0.03, 40),
        A5(0.02, 50); 
        
        private double price;
        private int type;

        SizeOfPaper(double price, int type){
            this.setPrice(price);
            this.type=type;
        }

        public void setPrice(double price){
            if(price<0){
                price=0;
            }
            this.price = price;
        }
        
        public double getPrice(){
            return this.price;
        }

        public int getType(){
            return this.type;
        }

    }


    private BigInteger idNumber;
    private static final long serializableID = 234168L;

    private String name;
    private int numberOfPages;
    private TypeOfIssue issueType;
    private SizeOfPaper paperSize;

    private List<PrintedIssue> printedIssues;
    private String printedIssuesFilesLocation;




    //-----------------------------------------CONSTRUCTORS-----------------------------------------//
    Issue() throws IOException{
        //Assign id
        this.idNumber=BigInteger.ONE;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter headline for the issue: ");
        this.name = bf.readLine();

        do{
            System.out.print("Enter type of issue (newspaper, book, dissertation, poster, brochure, flyer, calendar, voucher, card): ");
        }while(!this.setIssueType(bf.readLine()));

        do{
            System.out.print("Enter size of paper (A1,A2,A3,A4,A5): ");
        } while(!this.setSizeOfPaper(bf.readLine()));

        do{
            System.out.print("Enter number of pages for the issue: ");
        } while(!this.setNumberOfPages(bf.readLine()));

        this.printedIssues = new ArrayList<>();
    }

    Issue(BigInteger issueIdNumber) throws IOException{
        //Assign id
        this.idNumber=issueIdNumber;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter headline for the issue: ");
        this.name = bf.readLine();

        do{
            System.out.print("Enter type of issue (newspaper, book, dissertation, poster, brochure, flyer, calendar, voucher, card): ");
        }while(!this.setIssueType(bf.readLine()));

        do{
            System.out.print("Enter size of paper (A1,A2,A3,A4,A5): ");
        } while(!this.setSizeOfPaper(bf.readLine()));

        do{
            System.out.print("Enter number of pages for the issue: ");
        } while(!this.setNumberOfPages(bf.readLine()));

        this.printedIssues = new ArrayList<>();
    }

    Issue(String name, TypeOfIssue issueType, SizeOfPaper paperSize, int numberOfPages){
        //Assign id
        this.idNumber=BigInteger.valueOf(1);
        this.name=name;
        this.issueType=issueType;
        this.paperSize=paperSize;
        this.setNumberOfPages(numberOfPages);
        this.printedIssues = new ArrayList<>();
    }

    Issue(BigInteger issueIdNumber,Issue object){
        this.idNumber=issueIdNumber;
        this.name=object.name;
        this.issueType=object.issueType;
        this.paperSize=object.paperSize;
        this.setNumberOfPages(object.getNumberOfPages());
        this.printedIssues = new ArrayList<>();
    }

    Issue(BigInteger issueIdNumber, String name, TypeOfIssue issueType, SizeOfPaper paperSize, int numberOfPages){
        //Assign id
        this.idNumber=issueIdNumber;
        this.name=name;
        this.issueType=issueType;
        this.paperSize=paperSize;
        this.setNumberOfPages(numberOfPages);
        this.printedIssues = new ArrayList<>();
    }

    Issue(Issue obj){
        //Assign id
        this.idNumber=obj.idNumber;
        this.name=obj.name;
        this.issueType=obj.issueType;
        this.paperSize=obj.paperSize;
        this.setNumberOfPages(obj.getNumberOfPages());
        this.printedIssues = new ArrayList<>();
    }


    //Constructor for reading from a file
    Issue(String fileLocation, String folderLocation){
        try (FileReader file = new FileReader(fileLocation)) {
            BufferedReader bf = new BufferedReader(file);
            String input = bf.lines().collect(Collectors.joining(System.lineSeparator()));

            this.idNumber=new BigInteger(input.split("id: ")[1].split("\\r?\\n")[0]);
            this.name = input.split("Headline: ")[1].split("\\r?\\n")[0];
            this.issueType = TypeOfIssue.valueOf(input.split("Type of issue: ")[1].split("\\r?\\n")[0]);
            this.paperSize = SizeOfPaper.valueOf(input.split("Size of paper: ")[1].split("\\r?\\n")[0]);
            this.numberOfPages = Integer.parseInt(input.split("Number of pages: ")[1].split("\\r?\\n")[0]);
            this.printedIssues = new ArrayList<>();

            this.printedIssuesFilesLocation = folderLocation+"PrintedIssues\\"+this.name+"-"+this.nameIndex()+"\\";
            System.out.println(this.printedIssuesFilesLocation);
            File folder = new File(this.printedIssuesFilesLocation);

            if(folder.exists()){
                System.out.println(folder.getAbsolutePath());
                for(final File printFile : folder.listFiles()){
                    System.out.println(printFile.getAbsolutePath());
                    PrintedIssue pIssue = new PrintedIssue(this, printFile);
                    this.printedIssues.add(pIssue);
                }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    //--------------------------------------END OF CONSTRUCTORS-------------------------------------//


    //Setters for manual input
    public boolean setIssueType(String input){
        try{
            TypeOfIssue typeOfIssue = TypeOfIssue.valueOf(input.toUpperCase());
            this.issueType=typeOfIssue;
            return true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Invalid input for type of issue.");
            return false;
        }
    }

    public boolean setSizeOfPaper(String input){
        try{
            SizeOfPaper paperSize = SizeOfPaper.valueOf(input.toUpperCase());
            this.paperSize=paperSize;
            return true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Invalid input for size of paper.");
            return false;
        }
    }

    public boolean setNumberOfPages(String input){
        try{
            int numberOfPages = Integer.parseInt(input.toUpperCase());
            if(numberOfPages<0){
                numberOfPages=0;
            }
            this.numberOfPages=numberOfPages;
            return true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Invalid input for number of pages.");
            return false;
        }
    }

    //Normal setters
    public void setNumberOfPages(int numberOfPages){
        if(numberOfPages<0){
            numberOfPages=0;
        }
        this.numberOfPages=numberOfPages;
    }

    public void setPaperSize(SizeOfPaper paper){
        this.paperSize = paper;
    }

    public void setTypeOfIssue(TypeOfIssue typeOfIssue){
        this.issueType=typeOfIssue;
    }

    public BigInteger getIdNumber(){
        return this.idNumber;
    }

    public String getName(){
        return this.name;
    }

    public int getNumberOfPages(){
        return this.numberOfPages;
    }


    public TypeOfIssue getIssueType(){
        return this.issueType;
    }

    public SizeOfPaper getPaperSize(){
        return this.paperSize;
    }

    public void add(PrintedIssue printedIssue){
        this.printedIssues.add(printedIssue);
    }

    public List<PrintedIssue> getAllPrintedIssues(){
        return this.printedIssues;
    }

    //Save To File
    public void saveToFile(String directory){

        File folder = new File(directory);
        if(!folder.exists()){
            folder.mkdirs();
        }

        String fileName=directory+this.name+"-"+this.nameIndex()+".txt";
 
        try (FileWriter issueFile = new FileWriter(fileName)) {
            System.out.println("Currently saving: " + this.toString());
            issueFile.write(this.toString());

            this.printedIssuesFilesLocation = directory+"PrintedIssues\\"+this.name+"-"+this.nameIndex()+"\\";
            File folder_printed = new File(this.printedIssuesFilesLocation);
            if(!folder_printed.exists()){
                folder_printed.mkdirs();
            }
            
            BigInteger i=BigInteger.ONE;
            for(PrintedIssue pIssue : this.printedIssues){
                pIssue.saveToFile(this.printedIssuesFilesLocation+ i.toString() + ".txt");
                i=i.add(BigInteger.ONE);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public int nameIndex(){
        return paperSize.getType()+numberOfPages;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((issueType == null) ? 0 : issueType.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + numberOfPages;
        result = prime * result + ((paperSize == null) ? 0 : paperSize.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Issue other = (Issue) obj;
        if (issueType != other.issueType)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (numberOfPages != other.numberOfPages)
            return false;
        if (paperSize != other.paperSize)
            return false;
        return true;
    }

    //to string and prints
    public void printAllPrintedIssues(){
        for(PrintedIssue a : this.printedIssues){
            a.print();
        }
    }


    public void print(){  
        System.out.println("#"+ this.idNumber + ":\nHeadline: " + this.name + "\nType of issue: " + this.issueType +
        "\nSize of paper: " + this.paperSize + "\nNumber of pages: " + this.numberOfPages);
    }


    public String toString(){
        return "id: " + this.idNumber + "\nHeadline: " + this.name + "\nType of issue: " + this.issueType + 
                "\nSize of paper: " + this.paperSize + "\nNumber of pages: " + this.numberOfPages + "\n";
    }

}
