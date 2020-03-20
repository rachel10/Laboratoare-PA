import java.util.ArrayList;
import java.util.List;

public class Problem {
    private List<Resident> residents;
    private List<Hospital> hospitals;

    private Problem() {
        residents=new ArrayList<>();
        hospitals=new ArrayList<>();
    }

    public Problem(List<Resident> residents, List<Hospital> hospitals) {
        this();
        this.residents = residents;
        this.hospitals = hospitals;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }
}
