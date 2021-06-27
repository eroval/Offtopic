package Week08;

public class Hotel {
    private String name;
    private int numberOfRooms;
    private int rentedRooms;

    Hotel(String name, int numberOfRooms, int rentedRooms){
        this.name=name;
        this.numberOfRooms=numberOfRooms;
        this.rentedRooms=rentedRooms;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setNumberOfRooms(int numberOfRooms){
        this.numberOfRooms=numberOfRooms;
    }

    public void setRentedRooms(int rentedRooms){
        this.rentedRooms=rentedRooms;
    }

    public String getName(){
        return this.name;
    }

    public int getNumberOfRooms(){
        return this.numberOfRooms;
    }

    public int getRentedRooms(){
        return this.rentedRooms;
    }
}
