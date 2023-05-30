import org.jpl7.*;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that contains the methods that are used to get the list of diseases that match the given arguments
 */
public abstract class Diagnosis {

    /**
     * This method verifies if the knowledge base is found in the same directory as the .jar file
     * @return true if the knowledge base is found, false otherwise
     * @throws URISyntaxException if the path of the .jar file cannot be found
     */
    public static boolean verifyKB() throws URISyntaxException {
        //get the path of the file
        File file = new File(Diagnosis.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        String filePath = file.getParentFile().getAbsolutePath() + "/ExpertSystem.pl";
        try {
            Query ask = new Query("consult", new Term[] {new Atom(filePath)});
            return ask.hasSolution();
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * This method returns the expected path of the knowledge base
     * @return the expected path of the knowledge base, which is usually the same directory as the .jar file
     * @throws URISyntaxException if the path of the .jar file cannot be found
     */
    public static String expectedPath() throws URISyntaxException {
        //get the path of the file
        File file = new File(Diagnosis.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        return file.getParentFile().getAbsolutePath() + "/ExpertSystem.pl";
    }

    /**
     * This method returns the list of diseases that match:
     * symptoms, age, chief complaint, time length, vices, past
     * medical conditions, and environment
     * @param symptoms the list of symptoms, it can be none, one, or more
     * @param age the age of the patient
     * @param chiefComplaint the chief complaint of the patient
     * @param timeLen the time length of the chief complaint
     * @param vices the vices of the patient
     * @param pastMedicalConditions the list of past medical conditions, it can be none, one, or more
     * @param environment the environment of the patient
     * @return the list of diseases that likely matches the given arguments
     * @throws URISyntaxException if the path of the .jar file cannot be found
     */
    public static List<DiseaseQ> getDiseaseQList(List<String> symptoms,
                                                 String age,
                                                 String chiefComplaint,
                                                 String timeLen,
                                                 List<String> vices,
                                                 List<String> pastMedicalConditions,
                                                 String environment) throws URISyntaxException {

        //get the path of the file
        File file = new File(Diagnosis.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        String filePath = file.getParentFile().getAbsolutePath() + "/ExpertSystem.pl";
        Query ask = new Query("consult", new Term[] {new Atom(filePath)});
        if (ask.hasSolution()) {

            String result = "X";
            Term symptomList = argsToList(symptoms);
            Term pastMedicalConditionsList = argsToList(pastMedicalConditions);
            Term vicesList = argsToList(vices);

            Query query = new Query("diagnosis", new Term[] {
                    symptomList,
                    new Atom(age),
                    new Atom(chiefComplaint),
                    new Atom(timeLen),
                    vicesList,
                    pastMedicalConditionsList,
                    new Atom(environment),
                    new Variable(result)
            });

            if (query.hasSolution()) {
                // Extract the disease and weight from the solution term
                return DiseaseQ.extractDiseaseQList(query.oneSolution().get(result));
            } else {
                return new ArrayList<>();
            }
        } else {
            System.out.println("Could not load file.");
        }
        return null;
    }

    /**
     * Converts a list of strings to a list of atoms
     * @param args the list of strings
     * @return the list of atoms
     */
    private static Term argsToList(List<String> args) {
        Term [] argsArray = new Term[args.size()];
        for (int i = 0; i < args.size(); i++) {
            argsArray[i] = new Atom(args.get(i));
        }
        return Term.termArrayToList(argsArray);
    }
}
