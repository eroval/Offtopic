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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class Operator extends Employee{

    Operator() throws IOException{
        super();
    }

    Operator(String name){
        super(name);
    }

    Operator(File sourceFile){
        super(sourceFile);
    }

    public double salary(){
        return getBaseSalary();
    }

    public String toString(){
        return this.getClass().getSimpleName() + ": " + this.getName() + " has an income of " + salary() + ".\n";
    }
}
