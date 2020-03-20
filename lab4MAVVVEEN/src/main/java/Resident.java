import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Resident {
    private String name;
    private List<Hospital> hospitalList;

    public Resident() {
        hospitalList = new ArrayList<>();
    }

    public Resident(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return hospitalList.size();
    }

    public List<Hospital> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(Hospital[] hospitalList) {
        this.hospitalList.addAll(Arrays.asList(hospitalList));
        Arrays.stream(hospitalList).forEach(hospital -> hospital.addResident(this));
    }

    public void addHospital(Hospital hospital) {
        this.hospitalList.add(hospital);
        hospital.addResident(this);
    }

    @Override
    public String toString() {
        return name;
    }


}
