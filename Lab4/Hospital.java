import java.util.ArrayList;
import java.util.List;

public class Hospital implements Comparable<Hospital> {
    private String name;
    private int capacity;
    private List<Resident> residentList;

    public Hospital() {
        residentList=new ArrayList<>();
    }

    public Hospital(String name, int capacity) {
        this();
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Resident> getResidentList() {
        return residentList;
    }

    public void setResidentList(List<Resident> residentList) {
        this.residentList = residentList;
    }

    public void addResident(Resident resident){
        if(capacity<this.residentList.size()){
            residentList.add(resident);
        }
    }


    @Override
    public int compareTo(Hospital hospital) {
        return this.getName().compareTo(hospital.getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
