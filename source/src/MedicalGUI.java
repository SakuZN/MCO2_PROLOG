import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import java.awt.*;

/**
 * The interface for the Medical Expert System Diagnosis
 */
public class MedicalGUI extends JFrame implements ActionListener {
    private final JPanel mainPanel;
    private JPanel scene0_Name;
    private JPanel scene1_Age;
    private JPanel scene2_Environment;
    private JPanel scene3_Vices;
    private JPanel scene4_ChiefComplaint;
    private JPanel scene5_Symptoms;
    private JPanel scene6_Timeline;
    private JPanel scene7_PastHistory;
    private JPanel scene8_Diagnosis;
    private final CardLayout sceneManager;
    private String patientName;
    HashMap<String, List<String>> patientData = new HashMap<>();
    List <DiseaseQ> diseaseQList = new ArrayList<>();

    /**
     * MedicalGUI constructor, setups the GUI
     * @throws URISyntaxException if the path to the Prolog file is incorrect
     */
    public MedicalGUI() throws URISyntaxException {

        //CHECK IF THE PROLOG FILE IS IN THE CORRECT LOCATION, IF NOT EXIT THE PROGRAM
        if (!Diagnosis.verifyKB()) {
            showMessage(String.format("""
                    Prolog File not found.
                    Please place the file in the same directory as the .jar file.
                    Please also make sure the file is named "ExpertSystem.pl".
                    Expected path: %s
                    The program will now exit.""", Diagnosis.expectedPath())
            );
            System.exit(0);
        }

        setTitle("Medical Expert System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        /*
        Setup the scene manager and the main panel
         */
        mainPanel = new JPanel();
        sceneManager = new CardLayout();
        mainPanel.setLayout(sceneManager);
        setUpScene0_Name();
        mainPanel.add(scene0_Name, "Patient");
        setContentPane(mainPanel);

        //pack();
        setVisible(true);
    }

    /**
     * Scene to get the patient's name
     */
    public void setUpScene0_Name(){
        //Show message welcoming user to the program
        showMessage("Welcome to the Medical Expert System!\n Please enter your name to begin.");
        scene0_Name = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.PINK);
        centerPanel.setPreferredSize(new Dimension(400, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(50, 50));
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setFont(new Font(nameField.getFont().getName(), Font.PLAIN, 18));

        centerPanel.add(nameField, gbc);
        scene0_Name.add(centerPanel, BorderLayout.CENTER);
        scene0_Name.add(new JLabel("Enter Patient Name:"), BorderLayout.NORTH);

        JButton nextButton = new JButton("Next");
        JButton showInfoButton = new JButton("Show Info");
        southPanel.add(nextButton);
        southPanel.add(showInfoButton);
        scene0_Name.add(southPanel, BorderLayout.SOUTH);

        nextButton.addActionListener(e -> {
            patientName = nameField.getText();
            if(patientName.equals("")){
                showMessage("Please enter a name.");
            }
            else{
                showMessage("Happy to serve you, " + patientName + "!\n");
                setTitle("Medical Expert System - Patient: " + patientName);
                setupScene01();
                mainPanel.add(scene1_Age, "Age");
                sceneManager.next(mainPanel);
                showMessage("Please select your age group.");
            }
        });
        showInfoButton.addActionListener(e -> showInfo());

    }

    /**
     * Scene to get the patient's age
     */
    public void setupScene01() {
        scene1_Age = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("0-18");
        listModel.addElement("19-64");
        listModel.addElement("65+");

        //Create the check list box
        JList<String> list = new JList<>(listModel);
        list.setCellRenderer(new CheckboxListCellRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scene1_Age.add(new JLabel("Select Age Group:"), BorderLayout.NORTH);
        scene1_Age.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.PINK);
        JButton nextButton = new JButton("Next");
        JButton showInfoButton = new JButton("Show Info");

        southPanel.add(nextButton);
        southPanel.add(showInfoButton);
        scene1_Age.add(southPanel, BorderLayout.SOUTH);

        showInfoButton.addActionListener(e -> showInfo());

        nextButton.addActionListener(e -> {
            if  (list.getSelectedValue() == null) {
                showMessage("Please select an age group.");
                return;
            }
            patientData.put("Age", getAnswer(list));
            setupScene02();
            mainPanel.add(scene2_Environment, "Environment");
            sceneManager.next(mainPanel);
            showMessage("What kind of environment do you live in? (Select one only)");
        });

    }

    /**
     * Scene to get the patient's environment
     */
    public void setupScene02() {
        scene2_Environment = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Cold and Damp Weather");
        listModel.addElement("Crowded");
        listModel.addElement("Exposed to Wild Animals");
        listModel.addElement("Impoverished");
        listModel.addElement("Living in Rural Area");
        listModel.addElement("Polluted Air");
        listModel.addElement("Tropical");
        listModel.addElement("Well");
        listModel.addElement("None of the above");

        //Create the check list box
        JList<String> list = new JList<>(listModel);
        list.setCellRenderer(new CheckboxListCellRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scene2_Environment.add(new JLabel("Select Environment:"), BorderLayout.NORTH);
        scene2_Environment.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.PINK);
        JButton nextButton = new JButton("Next");
        JButton showInfoButton = new JButton("Show Info");
        southPanel.add(nextButton);
        southPanel.add(showInfoButton);
        scene2_Environment.add(southPanel, BorderLayout.SOUTH);

        showInfoButton.addActionListener(e -> showInfo());

        nextButton.addActionListener(e -> {
            if  (list.getSelectedValue() == null) {
                showMessage("Please select an environment.");
                return;
            }
            patientData.put("Environment", getAnswer(list));
            setupScene03();
            mainPanel.add(scene3_Vices, "Vices");
            sceneManager.next(mainPanel);
            showMessage("If applicable, what vices do you take? (Select all that apply by holding down the control key)");
        });
    }

    /**
     * Scene to get the patient's vices
     */
    public void setupScene03() {

        scene3_Vices = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Smoking");
        listModel.addElement("Drinking");
        listModel.addElement("Drugs");
        listModel.addElement("None of the above");

        //Create the check list box
        JList<String> list = new JList<>(listModel);
        list.setCellRenderer(new CheckboxListCellRenderer());
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scene3_Vices.add(new JLabel("Select Vice:"), BorderLayout.NORTH);
        scene3_Vices.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.PINK);
        JButton nextButton = new JButton("Next");
        JButton showInfoButton = new JButton("Show Info");
        southPanel.add(nextButton);
        southPanel.add(showInfoButton);
        scene3_Vices.add(southPanel, BorderLayout.SOUTH);

        showInfoButton.addActionListener(e -> showInfo());

        nextButton.addActionListener(e -> {
            if  (list.getSelectedValue() == null) {
                showMessage("Please select a vice.");
                return;
            }
            patientData.put("Vices", getAnswer(list));
            setupScene04_ChiefComplaint();
            mainPanel.add(scene4_ChiefComplaint, "Chief Complaint");
            sceneManager.next(mainPanel);
            showMessage("What is your chief complaint? (Select one that applies to you)");
        });

    }

    /**
     * Scene to get the patient's chief complaint
     */
    public void setupScene04_ChiefComplaint() {


        scene4_ChiefComplaint = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Respiratory Problems");
        listModel.addElement("Exposure to disease-ridden animals or insects");
        listModel.addElement("Skin Problems");
        listModel.addElement("Pain in the joints");
        listModel.addElement("Upset Stomach");
        listModel.addElement("None of the above");

        //Create the check list box
        JList<String> list = new JList<>(listModel);
        list.setCellRenderer(new CheckboxListCellRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scene4_ChiefComplaint.add(new JLabel("Select Chief Complaint:"), BorderLayout.NORTH);
        scene4_ChiefComplaint.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.PINK);
        JButton nextButton = new JButton("Next");
        JButton showInfoButton = new JButton("Show Info");
        southPanel.add(nextButton);
        southPanel.add(showInfoButton);
        scene4_ChiefComplaint.add(southPanel, BorderLayout.SOUTH);

        showInfoButton.addActionListener(e -> showInfo());

        nextButton.addActionListener(e -> {
            if  (list.getSelectedValue() == null) {
                showMessage("Please select a chief complaint.");
                return;
            }
            patientData.put("Chief Complaint", getAnswer(list));
            setupScene05_Symptoms();
            mainPanel.add(scene5_Symptoms, "Symptoms");
            sceneManager.next(mainPanel);
            showMessage("What are your symptoms? (Select all that apply by holding down the control key)");
        });
    }

    /**
     * Scene to get the patient's symptoms
     */
    public void setupScene05_Symptoms(){

        scene5_Symptoms = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = categoricalSymptoms(patientData.get("Chief Complaint").get(0));

        //Create the check list box
        JList<String> list = new JList<>(listModel);
        list.setCellRenderer(new CheckboxListCellRenderer());
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scene5_Symptoms.add(new JLabel("Select Symptoms:"), BorderLayout.NORTH);
        scene5_Symptoms.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.PINK);
        JButton nextButton = new JButton("Next");
        JButton showInfoButton = new JButton("Show Info");
        southPanel.add(nextButton);
        southPanel.add(showInfoButton);
        scene5_Symptoms.add(southPanel, BorderLayout.SOUTH);

        showInfoButton.addActionListener(e -> showInfo());

        nextButton.addActionListener(e -> {
            if  (list.getSelectedValue() == null) {
                showMessage("Please select at least one symptom.");
                return;
            }
            patientData.put("Symptoms", getAnswer(list));
            setupScene06_Timeline();
            mainPanel.add(scene6_Timeline, "Timeline");
            sceneManager.next(mainPanel);
            showMessage("How long have you had these symptoms?");
        });
    }

    /**
     * Scene to get the patient's timeline of symptoms
     */
    public void setupScene06_Timeline() {

        scene6_Timeline = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("1 or 2 days");
        listModel.addElement("3 or 6 days");
        listModel.addElement("More than a week");

        //Create the check list box
        JList<String> list = new JList<>(listModel);
        list.setCellRenderer(new CheckboxListCellRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scene6_Timeline.add(new JLabel("Select Timeline:"), BorderLayout.NORTH);
        scene6_Timeline.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.PINK);
        JButton nextButton = new JButton("Next");
        JButton showInfoButton = new JButton("Show Info");
        southPanel.add(nextButton);
        southPanel.add(showInfoButton);
        scene6_Timeline.add(southPanel, BorderLayout.SOUTH);

        showInfoButton.addActionListener(e -> showInfo());

        nextButton.addActionListener(e -> {
            if  (list.getSelectedValue() == null) {
                showMessage("Please select a timeline.");
                return;
            }
            patientData.put("Timeline", getAnswer(list));
            setupScene07_History();
            mainPanel.add(scene7_PastHistory, "History");
            sceneManager.next(mainPanel);
            showMessage("Do you have any past medical history? (Select all that apply by holding down the control key)");
        });
    }

    /**
     * Scene to get the patient's past medical history
     */
    public void setupScene07_History() {

        scene7_PastHistory = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("AIDS");
        listModel.addElement("Asthma");
        listModel.addElement("Bit by an Animal in the Past");
        listModel.addElement("Brain Complications");
        listModel.addElement("Comorbidities");
        listModel.addElement("Dengue");
        listModel.addElement("Diabetes");
        listModel.addElement("Heart Complications");
        listModel.addElement("Heart Disease");
        listModel.addElement("HIV");
        listModel.addElement("Immunocompromised");
        listModel.addElement("Immunodeficiency");
        listModel.addElement("Injury");
        listModel.addElement("Kidney Disease");
        listModel.addElement("Lung Complications");
        listModel.addElement("Nervous System Complications");
        listModel.addElement("Obesity");
        listModel.addElement("Organ Transplant");
        listModel.addElement("Respiratory Complications");
        listModel.addElement("Substance Abuse");
        listModel.addElement("Vitamin A Deficiency");
        listModel.addElement("None of the above");

        //Create the check list box
        JList<String> list = new JList<>(listModel);
        list.setCellRenderer(new CheckboxListCellRenderer());
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scene7_PastHistory.add(new JLabel("Select History (None or more):"), BorderLayout.NORTH);
        scene7_PastHistory.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.PINK);
        JButton DiagnoseButton = new JButton("Diagnose");
        JButton showInfoButton = new JButton("Show Info");
        southPanel.add(DiagnoseButton);
        southPanel.add(showInfoButton);
        scene7_PastHistory.add(southPanel, BorderLayout.SOUTH);

        showInfoButton.addActionListener(e -> showInfo());

        DiagnoseButton.addActionListener(e -> {
            if  (list.getSelectedValue() == null) {
                showMessage("Please select a history.");
                return;
            }
            patientData.put("History", getAnswer(list));
            setupScene08_Diagnosis();
            mainPanel.add(scene8_Diagnosis, "Diagnosis");
            sceneManager.next(mainPanel);
        });
    }

    /**
     * Scene to show the diagnosis
     */
    public void  setupScene08_Diagnosis() {
        showMessage("Determining Diagnosis...");
        scene8_Diagnosis = new JPanel(new BorderLayout());
        scene8_Diagnosis.add(new JLabel("Diagnosis:"), BorderLayout.NORTH);

        //Show the diagnosis in the middle, make the text bigger
        JLabel diagnosis = new JLabel();
        diagnosis.setFont(new Font("Serif", Font.BOLD, 20));
        try {
            diagnosis.setText(getDiagnosis());
            diagnosis.setHorizontalAlignment(JLabel.CENTER);
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        scene8_Diagnosis.add(diagnosis, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.PINK);
        JButton restartButton = new JButton("Restart");
        JButton showInfoButton = new JButton("Show Info");
        JButton showMatchButton = new JButton("Check Other Disease Match");
        southPanel.add(restartButton);
        southPanel.add(showInfoButton);
        southPanel.add(showMatchButton);
        scene8_Diagnosis.add(southPanel, BorderLayout.SOUTH);

        showInfoButton.addActionListener(e -> showInfo());

        showMatchButton.addActionListener(e -> showMatchingSymptoms());

        restartButton.addActionListener(e -> {
            try {
                restartDiagnosis();
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Method to get disease that likely matches the symptoms
     * @return String of the likely disease
     * @throws URISyntaxException if the URI is not valid
     * @throws IOException if there is an error reading the file
     */

    public String getDiagnosis() throws URISyntaxException, IOException {
        String age = patientData.get("Age").get(0);
        switch (age) {
            case "0-18" -> age = "A";
            case "19-64" -> age = "B";
            default -> age = "C";
        }
        String environment = patientData.get("Environment").get(0);
        List<String> vices = patientData.get("Vices");
        String chiefComplaint = processChiefComplaint(patientData.get("Chief Complaint").get(0));
        List<String> symptoms = patientData.get("Symptoms");
        String timeline = patientData.get("Timeline").get(0);
        switch (timeline) {
            case "1 or 2 days" -> timeline = "A";
            case "3 or 6 days" -> timeline = "B";
            default -> timeline = "C";
        }
        List<String> pastHistory = patientData.get("History");

        diseaseQList = Diagnosis.getDiseaseQList(symptoms, age, chiefComplaint, timeline, vices,
                pastHistory, environment);
        //Check if the diseaseQList is empty

        assert diseaseQList != null;
        if (diseaseQList.isEmpty()  || diseaseQList.get(0).getProbability() < 1) {
            return "<html>Sorry! Cannot determine sickness,<br>please refer to a doctor.</html>";
        }
        //Check if there are two or more diseases that have the same probability
        if (diseaseQList.size() > 1) {
            if (diseaseQList.get(0).getProbability() == diseaseQList.get(1).getProbability()) {
                return "<html>Sorry! Cannot determine sickness,<br>please refer to a doctor.</html>";
            }
        }
        String [] format = diseaseFormat(diseaseQList.get(0).getDisease(), diseaseQList.get(0).getProbability());
        String disease = format[0];
        String probability = format[1];
        return "<html>You may have " + disease + "<br>" +
                   " with a degree of seriousness: " + probability + "</html>";
    }

    /**
     * Reusable method to extract the string of the checkbox that is selected
     * @param list the list of checkbox
     * @return the list of string of the checkbox that is selected
     */
    private List<String> getAnswer(JList<String> list){
        List<String> getSelection = new ArrayList<>();
        for (int i = 0; i < list.getModel().getSize(); i++) {
            JCheckBox checkbox = (JCheckBox) list.getCellRenderer().getListCellRendererComponent(list,
                    list.getModel().getElementAt(i), i, list.isSelectedIndex(i), false);

            if (checkbox.isSelected()) {
                getSelection.add(checkbox.getText());
            }
        }
        return getSelection;
    }

    /**
     * Show selected information of the patient
     */
    private void showInfo() {
        // lambda to initialize name if there is name and when yet to input name
        String name = (patientName == null) ? "???" : patientName;
        String ageCategory = (patientData.get("Age") == null) ? "???" : patientData.get("Age").get(0);
        String age = switch (ageCategory) {
            case "0-18" -> age = "A";
            case "19-64" -> age = "B";
            case "65+" -> age = "C";
            default -> age = "???";
        };
        String environment = (patientData.get("Environment") == null) ? "???" : patientData.get("Environment").get(0);
        String vices = (patientData.get("Vices") == null) ? "???" : patientData.get("Vices").get(0);
        String chiefComplaint = (patientData.get("Chief Complaint") == null) ? "???" : patientData.get("Chief Complaint").get(0);
        String symptoms = (patientData.get("Symptoms") == null) ? "???" : patientData.get("Symptoms").toString();
        String timeline = (patientData.get("Timeline") == null) ? "???" : patientData.get("Timeline").get(0);
        String pastHistory = (patientData.get("History") == null) ? "???" : patientData.get("History").toString();
        showMessage(String.format("""
                Medical Record:
                Patient : %s
                Age Category: %s (%s)
                Environment: %s
                Vices: %s
                Chief Complaint: %s
                Symptoms: %s
                Timeline: %s
                Past History: %s
                """, name, ageCategory, age,
                environment, vices, chiefComplaint,
                symptoms, timeline, pastHistory));
    }

    /**
     * Method to restart the diagnosis by restarting the GUI
     * @throws URISyntaxException if the URI is not valid
     */
    private void restartDiagnosis() throws URISyntaxException {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to restart?", "Restart", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            this.dispose();
            Main.launchGUI();
        }
    }

    /**
     * Show the other possible diseases and their probabilities
     */
    private void showMatchingSymptoms() {
        StringBuilder matchingSymptoms = new StringBuilder();
        for (DiseaseQ diseaseQ : diseaseQList) {
            String [] disease = diseaseFormat(diseaseQ.getDisease(), diseaseQ.getProbability());
            matchingSymptoms.append(disease[0]).append(": ").append(disease[1]).append("\n");
        }
        showMessage("Other possible diseases and their probabilities:\n" +
                matchingSymptoms);
    }

    /**
     * Format the disease and probability to be displayed
     * @param disease the disease
     * @param probability the probability
     * @return the formatted disease and probability
     */
    private String[] diseaseFormat(String disease, double probability) {
        String Disease = disease.substring(0, 1).toUpperCase()
                + disease.substring(1);
        String Probability = String.format("%.2f", probability);
        return new String[]{Disease, Probability};
    }

    /**
     * Reusable method to show a message dialog
     * @param message the message to be shown
     */
    private static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Here so that the class does not complain about not having an actionPerformed method :p
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private String processChiefComplaint(String chiefComplaint) {
        switch (chiefComplaint) {
            case "Respiratory Problems" -> {
                return "respiratory";
            }
            case "Exposure to disease-ridden animals or insects" -> {
                return "vector_borne";
            }
            case "Skin Problems" -> {
                return "viral_infections_with_rash";
            }
            case "Pain in the joints" -> {
                return "degenerative_joint";
            }
            case "Upset Stomach" -> {
                return "gastrointestinal";
            }
            default -> {
                return "None of the above";
            }
        }
    }

    private DefaultListModel<String> categoricalSymptoms(String ChiefComplaint) {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        switch(ChiefComplaint) {
            case "Respiratory Problems" -> {
                listModel.addElement("Chest Pain");
                listModel.addElement("Chills");
                listModel.addElement("Confusion");
                listModel.addElement("Cough");
                listModel.addElement("Coughing Up Blood");
                listModel.addElement("Difficulty Breathing");
                listModel.addElement("Fatigue");
                listModel.addElement("Feeling Confused");
                listModel.addElement("Feeling Generally Unwell");
                listModel.addElement("Fever");
                listModel.addElement("Headache");
                listModel.addElement("Loss of Appetite");
                listModel.addElement("Loss of Mobility");
                listModel.addElement("Loss of Smell");
                listModel.addElement("Loss of Speech");
                listModel.addElement("Loss of Taste");
                listModel.addElement("Nasal Congestion");
                listModel.addElement("Nausea and Vomiting");
                listModel.addElement("Nausea");
                listModel.addElement("Night Sweats");
                listModel.addElement("Rapid Heartbeat");
                listModel.addElement("Runny Nose");
                listModel.addElement("Shivering");
                listModel.addElement("Sneezing");
                listModel.addElement("Sore Throat");
                listModel.addElement("Sweating");
                listModel.addElement("Tiredness");
                listModel.addElement("Vomiting");
                listModel.addElement("None of the above");

            }
            case "Exposure to disease-ridden animals or insects" -> {
                listModel.addElement("Agitation");
                listModel.addElement("Anxiety");
                listModel.addElement("Bit by an Animal");
                listModel.addElement("Confusion");
                listModel.addElement("Excess Salivation");
                listModel.addElement("Fatigue");
                listModel.addElement("Fear of Water");
                listModel.addElement("Fever");
                listModel.addElement("Foaming at the Mouth");
                listModel.addElement("Hallucination");
                listModel.addElement("Headache");
                listModel.addElement("High Fever");
                listModel.addElement("Insomnia");
                listModel.addElement("Malaise");
                listModel.addElement("Nausea and Vomiting");
                listModel.addElement("Problems Swallowing");
                listModel.addElement("Severe Headache");
                listModel.addElement("Severe Muscle Pains");
                listModel.addElement("Skin Rash");
                listModel.addElement("Swollen Lymph Glands");
                listModel.addElement("None of the above");
            }
            case "Skin Problems" -> {
                listModel.addElement("Cough");
                listModel.addElement("Fatigue");
                listModel.addElement("Fever");
                listModel.addElement("Fluid-Filled Blisters");
                listModel.addElement("Headache");
                listModel.addElement("Itching");
                listModel.addElement("Koplik Spots");
                listModel.addElement("Loss of Appetite");
                listModel.addElement("Red, Blotchy Rash");
                listModel.addElement("Runny Nose");
                listModel.addElement("Skin Rash");
                listModel.addElement("Tiredness");
                listModel.addElement("Watery Eyes");
                listModel.addElement("None of the above");
            }
            case "Pain in the joints" -> {
                listModel.addElement("Extra Lumps of Bone");
                listModel.addElement("Joint Pain");
                listModel.addElement("Joints Make Popping Sounds When Moved");
                listModel.addElement("Loss of Flexibility");
                listModel.addElement("Reduced Range of Motion");
                listModel.addElement("Stiffness in the Joint");
                listModel.addElement("Swelling in the Joint");
                listModel.addElement("Tenderness on the Area");
                listModel.addElement("None of the above");

            }
            case "Upset Stomach" -> {
                listModel.addElement("A Frequent Urge to Evacuate Your Bowels");
                listModel.addElement("Abdominal Pain");
                listModel.addElement("Bloating");
                listModel.addElement("Body Aches");
                listModel.addElement("Cramping");
                listModel.addElement("Dehydration");
                listModel.addElement("Fever");
                listModel.addElement("Large Volume of Stools");
                listModel.addElement("None of the above");
            }
            default -> {
                listModel.addElement("None of the above");
            }
        }

        return listModel;
    }

    /**
     * A custom renderer for the checkbox list that makes them able to be selected
     */
    private static class CheckboxListCellRenderer extends JCheckBox implements ListCellRenderer<String> {
        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setText(value);
            setSelected(isSelected);
            setEnabled(list.isEnabled());
            return this;
        }
    }

}

