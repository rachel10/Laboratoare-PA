import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.*;
import java.util.stream.IntStream;


public class Main {
    public static void main(String[] args) {
        Resident[] residents = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Resident("R" + i))
                .toArray(Resident[]::new);
        Hospital[] hospitals = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Hospital("Hospital" + i, i == 2 ? 2 : i % 2 + 1))
                .toArray(Hospital[]::new);

        residents[0].setHospitalList(hospitals);
        residents[1].setHospitalList(hospitals);
        residents[2].addHospital(hospitals[0]);
        residents[2].addHospital(hospitals[1]);
        residents[3].addHospital(hospitals[0]);
        residents[3].addHospital(hospitals[2]);

        List<Resident> residentList = new ArrayList<>();
        residentList.addAll(Arrays.asList(residents));

        //sort using lambdas
        //Collections.sort(residentList,((r1,r2)->r1.getName().compareTo(r2.getName())));

        //sort using functions
        Collections.sort(residentList, Comparator.comparing(Resident::getLength));
        System.out.print("ResidentList: ");
        for (Resident resident : residentList) {
            System.out.print(resident.getName() + " ");
        }
        System.out.println();

        Set<Hospital> hospitalSet = new TreeSet<>();
        Collections.addAll(hospitalSet, hospitals);
        System.out.print("HospitalSet: ");
        for (Hospital hospital : hospitalSet) {
            System.out.print(hospital.getName() + " ");
        }

        Map<Resident, List<Hospital>> residentListMap = new HashMap<>();

        residentListMap.put(residents[0], Arrays.asList(hospitals[0], hospitals[1], hospitals[2]));
        residentListMap.put(residents[1], Arrays.asList(hospitals[0], hospitals[1], hospitals[2]));
        residentListMap.put(residents[2], Arrays.asList(hospitals[0], hospitals[1]));
        residentListMap.put(residents[3], Arrays.asList(hospitals[0], hospitals[2]));

        Map<Hospital, List<Resident>> hospitalListMap = new TreeMap<>();

        hospitalListMap.put(hospitals[0], Arrays.asList(residents[3], residents[0], residents[1], residents[2]));
        hospitalListMap.put(hospitals[1], Arrays.asList(residents[0], residents[2], residents[1]));
        hospitalListMap.put(hospitals[2], Arrays.asList(residents[0], residents[1], residents[3]));

        System.out.println();
        residentListMap.keySet().forEach(resident -> System.out.println(resident.getName() + " " + residentListMap.get(resident)));


        System.out.println();
        hospitalListMap.keySet().forEach(hospital -> System.out.println(hospital.getName() + " " + hospitalListMap.get(hospital)));

        System.out.println();
        System.out.println("Residents who find acceptable H0 and H2: ");
        residentList.stream().filter(resident -> residentListMap.get(resident).contains(hospitals[0]))
                .filter(resident -> residentListMap.get(resident).contains(hospitals[2]))
                .forEach(resident -> System.out.print(resident.getName() + " "));
        System.out.println();
        System.out.println("Hospitals that have R0 as their top preference: ");
        hospitalSet.stream().filter(hospital -> hospitalListMap.get(hospital).get(0).getName().compareTo(residents[0].getName()) == 0)
                .forEach(hospital -> System.out.print(hospital.getName() + " "));

    }
}
