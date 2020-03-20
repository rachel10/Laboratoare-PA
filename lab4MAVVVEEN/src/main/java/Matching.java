import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Matching {
    private Problem problem;
    private Map<Resident,Hospital> matchPairs;

    public Matching(Problem problem) {
        this.matchPairs=new HashMap<>();
        this.problem = problem;
    }
    public Map<Resident,Hospital> algorithm(){
        int capacity=0;
        int indexHospital=0;
        for(Resident resident: problem.getResidents() ){
            matchPairs.put(resident,problem.getHospitals().get(indexHospital));
            capacity++;
            if(capacity==problem.getHospitals().get(indexHospital).getCapacity()){
                capacity=0;
                indexHospital++;
                if(indexHospital==problem.getHospitals().size()){
                    break;
                }
            }
        }
        return matchPairs;
    }

    public boolean isStable (){
        // verific daca toti rezidentii au fost repartizati
        if(problem.getResidents().size()>matchPairs.keySet().size())
        {
            return false;
        }

        //verific daca fiecare rezident a fost repartizat in primul spital din lista sa
        for(Resident resident:matchPairs.keySet()){
            if(!resident.getHospitalList().get(0).getName().equals(matchPairs.get(resident).getName())){
                return false;
            }
        }

        //verific daca fiecare spital a primit primii rezidenti doriti;
        Map<Hospital, List<Resident>> hospitalResidents = new HashMap<>();
        //pt fiecare spital construiesc lista cu rezidentii primiti
        for(Resident resident:matchPairs.keySet()){
            if(!hospitalResidents.containsKey(matchPairs.get(resident)))
                hospitalResidents.put(matchPairs.get(resident),new ArrayList<>());
            hospitalResidents.get(matchPairs.get(resident)).add(resident);
        }

        for(Hospital hospital: problem.getHospitals()){
            int minim;
            //caz1: spitalul a primit mai putini rezidenti decat erau in lista de preferinte.
            //      toti rezidentii repartizati trebuie sa se regaseasca pe primele pozitii din preferinte
            if(hospital.getResidentList().size()>=hospitalResidents.get(hospital).size()){
                minim=hospitalResidents.get(hospital).size();
            }
            else//Caz2: spitalul a primit mai multi decat rezidenti decat erau in lista de preferinte.
            //      toti rezidentii din lista de preferinite trebuie sa se regaseasca printre cei repartizati
            {
                minim=hospital.getResidentList().size();
            }
            //caut primele minim preferinte in rezidentii repartizati.
            for(int i=0;i<minim;i++){
                if(!hospitalResidents.get(hospital).contains(hospital.getResidentList().get(i))){
                    return false;
                }
            }
        }
        return true;
    }

}
