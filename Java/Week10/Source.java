package Week10;

public class Source {
    public static void main(String[] args) throws InterruptedException{
        /*
        FashionShop Gucci = new FashionShop("Gucci", 124);
        Tailor Larry = new Tailor("Larry");
        Tailor Garry = new Tailor("Garry");
        Tailor Harry = new Tailor("Harry");
        Tailor Manny = new Tailor("Manny");
        Gucci.addTailor(Larry, Garry, Harry, Manny);
        Gucci.startSewing();
        Gucci.addTailor("Thor The God of Thunder");
        Gucci.addTailor("Kratos The God of War");
        Gucci.increaseNumberOfClothes(200);
        Gucci.startSewing();
        Gucci.printAllTailors();
        */
        String str1 = "deniszangarov";
        String str2 = "zangarovsined";
        int Sum=0; 
        int maxSum=0;
        
        
        for(int i=0; i<str1.length(); i++){
            Sum=0;
            int l=0;
            for(int j=0; j<str2.length(); j++){
                if(i+l<str1.length()){
                    if(str1.charAt(i+l)==str2.charAt(j)){ 
                        System.out.println("str1 = " + str1.charAt(i+l) + "str2 = " + str2.charAt(j));
                        ++Sum;
                        if(Sum>maxSum){
                            maxSum=Sum;
                        }
                        //System.out.print(str1.charAt(i+l));
                        l++;
                    }
                    else {
                        Sum=0;
                        l=0;
                    }
                }
                else {
                    System.out.println();
                    break;
                }
            }
            
        }
        System.out.println(maxSum);
    }
}
