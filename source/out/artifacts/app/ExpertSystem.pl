tubercolosis(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Weight) :-
    findall(SymptomWeight, (member(Symptom, Symptoms),
    (member(Symptom, ['Fever', 'Chills', 'Night Sweats', 'Loss of Appetite']) -> SymptomWeight is 0.2;
     member(Symptom, ['Cough']) -> SymptomWeight is 0.5; SymptomWeight is 0)), SymptomWeights),
    sum_list(SymptomWeights, SymptomWeightTotal),

    (member(Age, ['A']) -> AgeWeight is 0.2;
     member(Age, ['B']) -> AgeWeight is 0.1;
     member(Age, ['C']) -> AgeWeight is 0.15; AgeWeight is 0),
    (member(TimeLen, ['A']) -> TimeLenWeight is 0;
     member(TimeLen, ['B']) -> TimeLenWeight is 0;
     member(TimeLen, ['C']) -> TimeLenWeight is 0.2; TimeLenWeight is 0),
    findall(VicesWeight, (member(Vice, Vices),
    (member(Vice, ['Smoking']) -> VicesWeight is 0.2; VicesWeight is 0)), VicesWeights),
    sum_list(VicesWeights, VicesWeightTotal),

    findall(PastMedicalConditionsWeight, (member(Past_Medical_Condition, Past_Medical_Conditions),
    (member(Past_Medical_Condition, ['HIV', 'Substance Abuse', 'Immunocompromised', 'Organ Transplant',
                                     'Kidney Disease', 'Diabetes']) -> PastMedicalConditionsWeight is 0.2; PastMedicalConditionsWeight is 0)),
                                     PastMedicalConditionsWeights),
    sum_list(PastMedicalConditionsWeights, PastMedicalConditionsWeightTotal),

    (member(Environment, ['Impoverished']) -> EnvironmentWeight is 0.1; EnvironmentWeight is 0),

    Weight is SymptomWeightTotal + AgeWeight + TimeLenWeight + VicesWeightTotal + PastMedicalConditionsWeightTotal + EnvironmentWeight.

diarrhea(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Weight) :-
    findall(SymptomWeight, (member(Symptom, Symptoms),
    (member(Symptom, ['Nausea', 'Abdominal Pain', 'Cramping', 'Bloating', 'Dehydration',
                      'A Frequent Urge to Evacuate Your Bowels', 'Large Volume of Stools']) -> SymptomWeight is 0.5;
    member(Symptom, ['Fever']) -> SymptomWeight is 0.2; SymptomWeight is 0)), SymptomWeights),
    sum_list(SymptomWeights, SymptomWeightTotal),

    (member(Age, ['A', 'B', 'C']) -> AgeWeight is 0.15; AgeWeight is 0),

    (member(TimeLen, ['A', 'B', 'C']) -> TimeLenWeight is 0.1; TimeLenWeight is 0),

    findall(VicesWeight, (member(Vice, Vices),
    (member(Vice, ['None']) -> VicesWeight is 0; VicesWeight is 0)), VicesWeights),
    sum_list(VicesWeights, VicesWeightTotal),

    findall(PastMedicalConditionsWeight, (member(Past_Medical_Condition, Past_Medical_Conditions),
    (member(Past_Medical_Condition, ['Immunodeficiency']) -> PastMedicalConditionsWeight is 0.2; PastMedicalConditionsWeight is 0)),
            PastMedicalConditionsWeights),

    sum_list(PastMedicalConditionsWeights, PastMedicalConditionsWeightTotal),

    (member(Environment, ['Impoverished']) -> EnvironmentWeight is 0.2; EnvironmentWeight is 0),

    Weight is SymptomWeightTotal + AgeWeight + TimeLenWeight + VicesWeightTotal + PastMedicalConditionsWeightTotal + EnvironmentWeight.

pneumonia(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Weight) :-
    findall(SymptomWeight, (member(Symptom, Symptoms),
    (member(Symptom, ['Fever', 'Rapid Heartbeat', 'Feeling Generally Unwell', 'Sweating',
        'Shivering', 'Loss of Appetite', 'Chest Pain', 'Coughing Up Blood',
        'Nausea', 'Vomiting', 'Feeling Confused']) -> SymptomWeight is 0.2;
    member(Symptom, ['Difficulty Breathing', 'Cough']) -> SymptomWeight is 0.5; SymptomWeight is 0)), SymptomWeights),
    sum_list(SymptomWeights, SymptomWeightTotal),

    (member(Age, ['A', 'C']) -> AgeWeight is 0.2;
     member(Age, ['B']) -> AgeWeight is 0.1; AgeWeight is 0),

    (member(TimeLen, ['A']) -> TimeLenWeight is 0.1;
     member(TimeLen, ['B', 'C']) -> TimeLenWeight is 0.15; TimeLenWeight is 0),

    findall(VicesWeight, (member(Vice, Vices),
    (member(Vice, ['Smoking']) -> VicesWeight is 0.2; VicesWeight is 0)), VicesWeights),
    sum_list(VicesWeights, VicesWeightTotal),

    findall(PastMedicalConditionsWeight, (member(Past_Medical_Condition, Past_Medical_Conditions),
    (member(Past_Medical_Condition, ['Immunocompromised', 'Comorbidities']) -> PastMedicalConditionsWeight is 0.2; PastMedicalConditionsWeight is 0)),
            PastMedicalConditionsWeights),
    sum_list(PastMedicalConditionsWeights, PastMedicalConditionsWeightTotal),

    (member(Environment, ['Well']) -> EnvironmentWeight is 0; EnvironmentWeight is 0),

    Weight is SymptomWeightTotal + AgeWeight + TimeLenWeight + VicesWeightTotal + PastMedicalConditionsWeightTotal + EnvironmentWeight.

%influenza_a
influenza_a(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Weight) :-
    findall(SymptomWeight, (member(Symptom, Symptoms),
    (member(Symptom, ['Sneezing', 'Runny Nose', 'Cough', 'Sore Throat', 'Fever', 'Body Aches',
        'Fatigue', 'Headache', 'Chills', 'Nasal Congestion', 'Nausea and Vomiting']) -> SymptomWeight is 0.4; SymptomWeight is 0)), SymptomWeights),
    sum_list(SymptomWeights, SymptomWeightTotal),

    (member(Age, ['A']) -> AgeWeight is 0.2;
     member(Age, ['B']) -> AgeWeight is 0.15;
     member(Age, ['C']) -> AgeWeight is 0.1; AgeWeight is 0),

    (member(TimeLen, ['A']) -> TimeLenWeight is 0.15;
     member(TimeLen, ['B']) -> TimeLenWeight is 0.2;
     member(TimeLen, ['C']) -> TimeLenWeight is 0.1; TimeLenWeight is 0),

    findall(VicesWeight, (member(Vice, Vices),
    (member(Vice, ['None']) -> VicesWeight is 0; VicesWeight is 0)), VicesWeights),
    sum_list(VicesWeights, VicesWeightTotal),

    findall(PastMedicalConditionsWeight, (member(Past_Medical_Condition, Past_Medical_Conditions),
    (member(Past_Medical_Condition, ['Asthma', 'Diabetes', 'Heart Disease']) -> PastMedicalConditionsWeight is 0.2; PastMedicalConditionsWeight is 0)), PastMedicalConditionsWeights),
    sum_list(PastMedicalConditionsWeights, PastMedicalConditionsWeightTotal),

    (member(Environment, ['Well']) -> EnvironmentWeight is 0; EnvironmentWeight is 0),

    Weight is SymptomWeightTotal + AgeWeight + TimeLenWeight + VicesWeightTotal + PastMedicalConditionsWeightTotal + EnvironmentWeight.


%dengue
dengue(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Weight) :-
    findall(SymptomWeight, (member(Symptom, Symptoms),
    (member(Symptom, ['Severe Headache', 'High Fever', 'Swollen Lymph Glands',
        'Severe Muscle Pains', 'Skin Rash', 'Fatigue', 'Nausea and Vomiting']) -> SymptomWeight is 0.4; SymptomWeight is 0)), SymptomWeights),
    sum_list(SymptomWeights, SymptomWeightTotal),

    (member(Age, ['A']) -> AgeWeight is 0.2;
     member(Age, ['B']) -> AgeWeight is 0.15;
     member(Age, ['C']) -> AgeWeight is 0.1; AgeWeight is 0),

    (member(TimeLen, ['A']) -> TimeLenWeight is 0.2;
     member(TimeLen, ['B']) -> TimeLenWeight is 0.1;
     member(TimeLen, ['C']) -> TimeLenWeight is 0.1; TimeLenWeight is 0),

    findall(VicesWeight, (member(Vice, Vices),
    (member(Vice, ['None']) -> VicesWeight is 0; VicesWeight is 0)), VicesWeights),
    sum_list(VicesWeights, VicesWeightTotal),

    findall(PastMedicalConditionsWeight, (member(Past_Medical_Condition, Past_Medical_Conditions),
    (member(Past_Medical_Condition, ['Dengue']) -> PastMedicalConditionsWeight is 0.2; PastMedicalConditionsWeight is 0)), PastMedicalConditionsWeights),
    sum_list(PastMedicalConditionsWeights, PastMedicalConditionsWeightTotal),

    (member(Environment, ['Tropical']) -> EnvironmentWeight is 0.2; EnvironmentWeight is 0),

    Weight is SymptomWeightTotal + AgeWeight +  TimeLenWeight + VicesWeightTotal + PastMedicalConditionsWeightTotal + EnvironmentWeight.

%chickenpox
chickenpox(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Weight) :-
    findall(SymptomWeight, (member(Symptom, Symptoms),
    (member(Symptom, ['Skin Rash', 'Fever', 'Tiredness', 'Loss of Appetite',
                      'Headache', 'Fatigue', 'Itching']) -> SymptomWeight is 0.2;
    member(Symptom, ['Fluid-Filled Blisters']) -> SymptomWeight is 0.5; SymptomWeight is 0)), SymptomWeights),
    sum_list(SymptomWeights, SymptomWeightTotal),

    (member(Age, ['A']) -> AgeWeight is 0.2;
     member(Age, ['B']) -> AgeWeight is 0.15;
     member(Age, ['C']) -> AgeWeight is 0.1; AgeWeight is 0),

    (member(TimeLen, ['A']) -> TimeLenWeight is 0.2;
     member(TimeLen, ['B']) -> TimeLenWeight is 0.15;
     member(TimeLen, ['C']) -> TimeLenWeight is 0.1; TimeLenWeight is 0),

    findall(VicesWeight, (member(Vice, Vices),
    (member(Vice, ['None']) -> VicesWeight is 0; VicesWeight is 0)), VicesWeights),
    sum_list(VicesWeights, VicesWeightTotal),

    findall(PastMedicalConditionsWeight, (member(Past_Medical_Condition, Past_Medical_Conditions),
    (member(Past_Medical_Condition, ['Immunocompromised', 'HIV', 'AIDS']) -> PastMedicalConditionsWeight is 0.2; PastMedicalConditionsWeight is 0)), PastMedicalConditionsWeights),
    sum_list(PastMedicalConditionsWeights, PastMedicalConditionsWeightTotal),

    (member(Environment, ['Well']) -> EnvironmentWeight is 0; EnvironmentWeight is 0),

    Weight is SymptomWeightTotal + AgeWeight + TimeLenWeight + VicesWeightTotal + PastMedicalConditionsWeightTotal + EnvironmentWeight.


covid19(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Weight) :-
    findall(SymptomWeight, (member(Symptom, Symptoms),
    (member(Symptom, ['Fever', 'Cough', 'Tiredness']) -> SymptomWeight is 0.2;
    member(Symptom, ['Loss of Taste','Loss of Smell', 'Difficulty Breathing',
        'Loss of Speech', 'Loss of Mobility', 'Confusion', 'Chest Pain']) -> SymptomWeight is 0.5; SymptomWeight is 0)), SymptomWeights),
    sum_list(SymptomWeights, SymptomWeightTotal),

    (member(Age, ['A']) -> AgeWeight is 0.1;
     member(Age, ['B']) -> AgeWeight is 0.15;
     member(Age, ['C']) -> AgeWeight is 0.2; AgeWeight is 0),

    (member(TimeLen, ['A']) -> TimeLenWeight is 0.1;
     member(TimeLen, ['B']) -> TimeLenWeight is 0.15;
     member(TimeLen, ['C']) -> TimeLenWeight is 0.2; TimeLenWeight is 0),

    findall(VicesWeight, (member(Vice, Vices),
    (member(Vice, ['Smoking', 'Drinking', 'Drugs']) -> VicesWeight is 0.05; VicesWeight is 0)), VicesWeights),
    sum_list(VicesWeights, VicesWeightTotal),

    findall(PastMedicalConditionsWeight, (member(Past_Medical_Condition, Past_Medical_Conditions),
    (member(Past_Medical_Condition, ['Lung Complications', 'Respiratory Complications']) -> PastMedicalConditionsWeight is 0.15;
     member(Past_Medical_Condition, ['Heart Complications', 'Brain Complications', 'Nervous System Complications']) -> PastMedicalConditionsWeight is 0.15;
     member(Past_Medical_Condition, ['Immunocompromised']) -> PastMedicalConditionsWeight is 0.2; PastMedicalConditionsWeight is 0)),
     PastMedicalConditionsWeights),
     sum_list(PastMedicalConditionsWeights, PastMedicalConditionsWeightTotal),

    (member(Environment, ['Crowded', 'Impoverished']) -> EnvironmentWeight is 0.1; EnvironmentWeight is 0),

    Weight is SymptomWeightTotal + AgeWeight + TimeLenWeight + VicesWeightTotal + PastMedicalConditionsWeightTotal + EnvironmentWeight.

rabies(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Weight) :-
    findall(SymptomWeight, (member(Symptom, Symptoms),
    (member(Symptom, ['Insomnia', 'Agitation', 'Confusion',
        'Hallucination', 'Excess Salivation', 'Foaming at the Mouth', 'Problems Swallowing',
        'Fear of Water', 'Malaise', 'Bit by an Animal']) -> SymptomWeight is 0.5;
    member(Symptom, ['Fever', 'Anxiety', 'Headache']) -> SymptomWeight is 0.2; SymptomWeight is 0)), SymptomWeights),
    sum_list(SymptomWeights, SymptomWeightTotal),

    (member(Age, ['A']) -> AgeWeight is 0.2;
     member(Age, ['B']) -> AgeWeight is 0.15;
     member(Age, ['C']) -> AgeWeight is 0.1; AgeWeight is 0),

    (member(TimeLen, ['A']) -> TimeLenWeight is 0.1;
     member(TimeLen, ['B']) -> TimeLenWeight is 0.15;
     member(TimeLen, ['C']) -> TimeLenWeight is 0.2; TimeLenWeight is 0),

    findall(VicesWeight, (member(Vice, Vices),
    (member(Vice, ['None']) -> VicesWeight is 0; VicesWeight is 0)), VicesWeights),
    sum_list(VicesWeights, VicesWeightTotal),

    findall(PastMedicalConditionsWeight, (member(Past_Medical_Condition, Past_Medical_Conditions),
    (member(Past_Medical_Condition, ['Bit by an Animal in the Past']) -> PastMedicalConditionsWeight is 0.2; PastMedicalConditionsWeight is 0)), PastMedicalConditionsWeights),
     sum_list(PastMedicalConditionsWeights, PastMedicalConditionsWeightTotal),

    (member(Environment, ['Exposed to Wild Animals', 'Living in Rural Area']) -> EnvironmentWeight is 0.2; EnvironmentWeight is 0),

    Weight is SymptomWeightTotal + AgeWeight + TimeLenWeight + VicesWeightTotal + PastMedicalConditionsWeightTotal + EnvironmentWeight.

ostheoarthritis(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Weight) :-
    findall(SymptomWeight, (member(Symptom, Symptoms),
    (member(Symptom, ['Joint Pain', 'Stiffness in the Joint', 'Loss of Flexibility', 'Reduced Range of Motion',
        'Extra Lumps of Bone', 'Swelling in the Joint',
        'Joints Make Popping Sounds When Moved', 'Tenderness on the Area']) -> SymptomWeight is 0.4; SymptomWeight is 0)), SymptomWeights),
    sum_list(SymptomWeights, SymptomWeightTotal),

    (member(Age, ['A']) -> AgeWeight is 0.05;
     member(Age, ['B']) -> AgeWeight is 0.1;
     member(Age, ['C']) -> AgeWeight is 0.15; AgeWeight is 0),

    (member(TimeLen, ['A']) -> TimeLenWeight is 0;
     member(TimeLen, ['B']) -> TimeLenWeight is 0;
     member(TimeLen, ['C']) -> TimeLenWeight is 0.2; TimeLenWeight is 0),

    findall(VicesWeight, (member(Vice, Vices),
    (member(Vice, ['Smoking', 'Drinking']) -> VicesWeight is 0.1; VicesWeight is 0)), VicesWeights),
    sum_list(VicesWeights, VicesWeightTotal),

    findall(PastMedicalConditionsWeight, (member(Past_Medical_Condition, Past_Medical_Conditions),
    (member(Past_Medical_Condition, ['Obesity', 'Injury']) -> PastMedicalConditionsWeight is 0.2; PastMedicalConditionsWeight is 0)), PastMedicalConditionsWeights),
    sum_list(PastMedicalConditionsWeights, PastMedicalConditionsWeightTotal),

    (member(Environment, ['Polluted Air', 'Cold and Damp Weather']) -> EnvironmentWeight is 0.15; EnvironmentWeight is 0),

    Weight is SymptomWeightTotal + AgeWeight + TimeLenWeight + VicesWeightTotal + PastMedicalConditionsWeightTotal + EnvironmentWeight.

%Sample disease format
measles(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Weight) :-
    findall(SymptomWeight, (member(Symptom, Symptoms),
    (member(Symptom, ['Fever', 'Runny Nose', 'Cough', 'Watery Eyes']) -> SymptomWeight is 0.2;
    member(Symptom, ['Koplik Spots', 'Red, Blotchy Rash']) -> SymptomWeight is 0.5; SymptomWeight is 0)), SymptomWeights),
    sum_list(SymptomWeights, SymptomWeightTotal),

    (member(Age, ['A']) -> AgeWeight is 0.2;
     member(Age, ['B']) -> AgeWeight is 0.15;
     member(Age, ['C']) -> AgeWeight is 0.1; AgeWeight is 0),

    (member(TimeLen, ['A']) -> TimeLenWeight is 0.1;
     member(TimeLen, ['B']) -> TimeLenWeight is 0.15;
     member(TimeLen, ['C']) -> TimeLenWeight is 0.2; TimeLenWeight is 0),

    findall(VicesWeight, (member(Vice, Vices),
    (member(Vice, ['Smoking', 'Drinking', 'Drugs']) -> VicesWeight is 0.05; VicesWeight is 0)), VicesWeights),
    sum_list(VicesWeights, VicesWeightTotal),

    findall(PastMedicalConditionsWeight, (member(Past_Medical_Condition, Past_Medical_Conditions),
    (member(Past_Medical_Condition, ['Vitamin A Deficiency']) -> PastMedicalConditionsWeight is 0.15;
     member(Past_Medical_Condition, ['Immunocompromised']) -> PastMedicalConditionsWeight is 0.2; PastMedicalConditionsWeight is 0)), PastMedicalConditionsWeights),
     sum_list(PastMedicalConditionsWeights, PastMedicalConditionsWeightTotal),

    (member(Environment, ['Crowded', 'Impoverished']) -> EnvironmentWeight is 0.2; EnvironmentWeight is 0),

    Weight is SymptomWeightTotal + AgeWeight + TimeLenWeight + VicesWeightTotal + PastMedicalConditionsWeightTotal + EnvironmentWeight.

respiratory_infections(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Disease, Weight) :-
    tubercolosis(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, T_Sum),
    Disease = tuberculosis, Weight = T_Sum;

    pneumonia(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, P_Sum),
    Disease = pneumonia, Weight = P_Sum;

    influenza_a(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, IA_Sum),
    Disease = influenza_A, Weight = IA_Sum;

    covid19(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, C19_Sum),
    Disease = covid19, Weight = C19_Sum.

vector_borne_infections(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Disease, Weight):-
    dengue(Symptoms, Age,  TimeLen, Vices, Past_Medical_Conditions, Environment, D_Sum),
    Disease = dengue, Weight = D_Sum;

    rabies(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, R_Sum),
    Disease = rabies, Weight = R_Sum.

viral_infections_with_rash(Symptoms, Age,  TimeLen, Vices, Past_Medical_Conditions, Environment, Disease, Weight) :-
    measles(Symptoms, Age,  TimeLen, Vices, Past_Medical_Conditions, Environment, M_Sum),
    Disease = measles, Weight = M_Sum;

    chickenpox(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, C_Sum),
    Disease = chickenpox, Weight = C_Sum.

degenerative_joint_disease(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Disease, Weight) :-
    ostheoarthritis(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, O_Sum),
    Disease = ostheoarthritis, Weight = O_Sum.

gastrointestinal_infections(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Disease, Weight) :-
    diarrhea(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, DR_Sum),
    Disease = diarrhea, Weight = DR_Sum.

% Base disease predicate
% Add diseases here, make sure you use ';' to seperate each disease after every disease and weight initialization.
disease(Symptoms, Age, ChiefComplaint, TimeLen, Vices, Past_Medical_Conditions, Environment, Disease, Weight) :-
    ChiefComplaint = respiratory, respiratory_infections(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Disease, Weight);
    ChiefComplaint = vector_borne, vector_borne_infections(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Disease, Weight);
    ChiefComplaint = viral_infections_with_rash, viral_infections_with_rash(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Disease, Weight);
    ChiefComplaint = degenerative_joint, degenerative_joint_disease(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Disease, Weight);
    ChiefComplaint = gastrointestinal, gastrointestinal_infections(Symptoms, Age, TimeLen, Vices, Past_Medical_Conditions, Environment, Disease, Weight).

% Diagnosis predicate
diagnosis(Symptoms, Age, ChiefComplaint, TimeLen, Vices, Past_Medical_Conditions, Environment, MaxResult) :-
    %Find all matching diseases
    findall(Weight-Disease, disease(Symptoms, Age, ChiefComplaint, TimeLen, Vices, Past_Medical_Conditions,
    Environment, Disease, Weight),
            Result),

    %Sort the key pairs, and reverse the order to find and return the highest weight order result
    keysort(Result, SortedResult),
    reverse(SortedResult, MaxResult).