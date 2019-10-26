package duke.command;

import duke.DukeCore;
import duke.data.Patient;
import duke.exception.DukeException;

public class PatientEditCommand extends ArgCommand {
    @Override
    protected ArgSpec getSpec() {
        return PatientEditSpec.getSpec();
    }

    @Override
    public void execute(DukeCore core) throws DukeException {
        super.execute(core);

        Patient patient = (Patient) core.uiContext.getObject();

        // TODO: refactor
        // TODO: Ability to change bed number and name
        int height = CommandHelpers.switchToInt("height", this);
        if (height != 0) {
            patient.setHeight(height);
        }

        int weight = CommandHelpers.switchToInt("weight", this);
        if (weight != 0) {
            patient.setWeight(weight);
        }

        int age = CommandHelpers.switchToInt("age", this);
        if (age != 0) {
            patient.setAge(age);
        }

        int number = CommandHelpers.switchToInt("number", this);
        if (number != 0) {
            patient.setNumber(number);
        }

        patient.setAddress(getSwitchVal("address"));
        patient.setHistory(getSwitchVal("history"));
        patient.setAllergies(getSwitchVal("allergies"));

        patient.updateAttributes();
        core.ui.print("Edited details of patient!");
        core.storage.writeJsonFile(core.patientMap.getPatientHashMap());
    }
}
