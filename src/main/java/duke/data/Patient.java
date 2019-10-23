package duke.data;

import duke.exception.DukeException;

import java.util.ArrayList;

public class Patient extends DukeObject {

    private String bedNo;
    private String allergies;
    private Impression priDiagnosis;
    private ArrayList<Impression> impressions;

    private Integer height;
    private Integer weight;
    private Integer age;
    private Integer number;
    private String address;
    private String history;

    /**
     * Represents the patient.
     * A Patient object corresponds to the biometric information of a patient,
     * patient details, medical history, the impressions the doctor has about a patient.
     * Attributes:
     * - priDiagnosis the chief complaint or most serious impression of a patient
     * - impression the list of all impressions of a patient
     * @param name the name of the patient
     * @param bedNo the bed number of the patient
     * @param height the height of the patient
     * @param weight the weight of the patient
     * @param age the age of the patient
     * @param number the contact details of a patient's NOK
     * @param address the residential address of a patient
     * @param history the medical history of a patient
     * @param allergies the Food and Drug allergies a patient has
     */
    public Patient(String name, String bedNo, String allergies, Integer height, Integer weight,
                    Integer age, Integer number, String address, String history) {
        super(name);
        this.bedNo = bedNo;
        this.allergies = allergies;
        this.priDiagnosis = null;
        this.impressions = new ArrayList<Impression>();

        this.height = height;
        this.weight = weight;
        this.age = age;
        this.number = number;
        this.address = address;
        this.history = history;
    }

    /**
     * Represents the patient.
     * A Patient object corresponds to the biometric information of a patient,
     * patient details, medical history, the impressions the doctor has about a patient.
     * Attributes:
     * @param name the name of the patient
     * @param bedNo the bed number of the patient
     * @param allergies the Food and Drug allergies a patient has
     */
    public Patient(String name, String bedNo, String allergies) {
        super(name);
        this.bedNo = bedNo;
        this.allergies = allergies;
        this.priDiagnosis = null;
        this.impressions = new ArrayList<Impression>();

        this.height = null;
        this.weight = null;
        this.age = null;
        this.number = null;
        this.address = null;
        this.history = null;
    }

    /**
     * This discharge function runs the procedure to discharges a patient from the hospital.
     * Todo write the function
     */
    public void discharge() {
        // Todo
    }

    /**
     * This addNewImpression function adds a new impression to the impressions list.
     * @param newImpression the impression to be added
     * @return the Impression newly added
     */
    public Impression addNewImpression(Impression newImpression) {
        this.impressions.add(newImpression);
        return newImpression;
    }

    /**
     * This deleteImpression function deletes an impression at the specified index
     * from the impressions list.
     * @param idx index of the impression
     * @return the Impression of the deleted Impression
     */
    public Impression deleteImpression(int idx) throws DukeException {
        if (idx >= 0 && idx < this.impressions.size()) {
            Impression imp = this.impressions.get(idx);
            this.impressions.remove(idx);
            return imp;
        } else {
            throw new DukeException("I don't have that entry in the list!");
        }
    }

    /**
     * This getImpression function returns the impression from the impressions list at the specified index.
     * @param idx index of the impression
     * @return Impression the impression specified by the index
     */
    public Impression getImpression(int idx) throws DukeException {
        if (idx >= 0 && idx < this.impressions.size()) {
            return this.impressions.get(idx);
        } else {
            throw new DukeException("I don't have that entry in the list!");
        }
    }

    /**
     * Sets the Primary Diagnosis of the patient specified by the index chosen.
     * @param idx index of the impression
     */
    public void setPriDiagnosis(int idx) throws DukeException {
        if (idx >= 0 && idx < this.impressions.size()) {
            this.priDiagnosis = this.impressions.get(idx);
            return;
        } else {
            throw new DukeException("I don't have that entry in the list!");
        }
    }

    /**
     * This function find returns a list of all DukeObjs
     * with names related to the patient containing the search term.
     * @param searchTerm String to be used to filter the DukeObj
     * @return the list of DukeObjs
     */
    public ArrayList<DukeObject> find(String searchTerm) throws DukeException {
        int i = 1;
        ArrayList<DukeObject> searchResult = new ArrayList<DukeObject>();
        for (Impression imp : this.impressions) {
            if (imp.getName().contains(searchTerm)) {
                searchResult.add(imp);
                ++i;
            }
        }
        if (i == 1) {
            throw new DukeException("Can't find any matching tasks!");
        } else {
            return searchResult;
        }
    }

    /**
     * This function find returns if a patient is allergic to an allergy.
     * @param allergy String the possible allergy striped of spaces
     * @return boolean
     */
    public boolean isAllergic(String allergy) {
        return this.allergies.contains(allergy);
    }

    @Override
    public String toString() {
        // Todo
        return getName() + " " +  getClass(); // change back to null later
    }

    @Override
    public String toDisplayString() {
        // Todo
        return null;
    }

    @Override
    public String toReportString() {
        // Todo
        return null;
    }

    public String getBedNo() {
        return bedNo;
    }

    public ArrayList<Impression> getImpressions() {
        return impressions;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Impression getPriDiagnosis() {
        return priDiagnosis;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
