import org.jpl7.Term;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the disease and its probability
 * This class is used to store the disease and its probability
 */

public class DiseaseQ {
    String disease;
    double probability;

    public DiseaseQ(String disease, double probability) {
        this.disease = disease;
        this.probability = probability;
    }
    public String getDisease() {
        return disease;
    }
    public double getProbability() {
        return probability;
    }

    /**
     * This method extracts the disease and its probability from the solution given by the Prolog engine
     * @param solution the solution given by the Prolog engine
     * @return the list of diseases and their probabilities
     */
    public static List<DiseaseQ> extractDiseaseQList(Term solution) {
        List<DiseaseQ> diseaseQList = new ArrayList<>();
        while (solution.arity() > 0) {
            Term currentArg = solution.arg(1);
            String currentDisease = currentArg.arg(2).name();
            double currentWeight = Double.parseDouble(currentArg.arg(1).toString());
            diseaseQList.add(new DiseaseQ(currentDisease, currentWeight));
            solution = solution.arg(2);
        }
        return diseaseQList;
    }
}
