import com.github.javafaker.Faker;

import java.util.*;
import java.util.List;
import java.util.Random;

public class ProblemGenerator {

    public ProblemGenerator() {
    }

    private int randomInIntreval(int min, int max) {
        Random rand = new Random();
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        return rand.nextInt((max - min) + 1) + min;
    }

    public Problem Generator() {
        List<Resident> residentList = new ArrayList<>();
        List<Hospital> hospitalList = new ArrayList<>();
        int nrResidents = randomInIntreval(1, 50);
        int nrHospitals = randomInIntreval(1, 50);
        Faker faker = new Faker();
        for (int i = 0; i < nrResidents; i++) {
            Resident resident = new Resident(faker.name().fullName());
            residentList.add(resident);
        }

        for (int i = 0; i < nrHospitals; i++) {
            Hospital hospital = new Hospital(faker.name().fullName(), randomInIntreval(1, 15));
            hospitalList.add(hospital);
        }

        for (Resident resident : residentList) {
            int nrPreferences = randomInIntreval(1, nrHospitals - 1);
            for (int i = 0; i < nrPreferences; i++) {
                int indexHospital = randomInIntreval(0, nrHospitals - 1);
                while (resident.getHospitalList().contains(hospitalList.get(indexHospital))) {
                    indexHospital = randomInIntreval(0, nrHospitals - 1);
                }
                resident.addHospital(hospitalList.get(indexHospital));
            }
        }

        return new Problem(residentList, hospitalList);
    }

}
