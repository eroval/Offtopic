package WeekLecEx1;

public abstract class RegisteredProvider implements Provider{
    private String regNumber;

    RegisteredProvider(String regNumber){
        this.regNumber=regNumber;
    }
}