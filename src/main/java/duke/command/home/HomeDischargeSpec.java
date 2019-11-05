package duke.command.home;

import duke.DukeCore;
import duke.command.ArgCommand;
import duke.command.ArgLevel;
import duke.command.ArgSpec;
import duke.command.CommandUtils;
import duke.command.Switch;
import duke.data.Patient;
import duke.exception.DukeException;

public class HomeDischargeSpec extends ArgSpec {
    private static final HomeDischargeSpec spec = new HomeDischargeSpec();

    public static HomeDischargeSpec getSpec() {
        return spec;
    }

    private HomeDischargeSpec() {
        cmdArgLevel = ArgLevel.OPTIONAL;
        initSwitches(
                new Switch("bed", String.class, true, ArgLevel.REQUIRED, "b"),
                new Switch("summary", String.class, true, ArgLevel.OPTIONAL, "sum")
        );
    }

    private static final String header = "DISCHARGED PATIENT REPORT";
    private static final String explanation = "This report shows all the data that was stored about a patient at the "
            + "time the report was created.";
    private static final String result = "Patient discharged. A discharge report have been created.";

    @Override
    public void execute(DukeCore core, ArgCommand cmd) throws DukeException {
        String bed = cmd.getSwitchVal("bed");
        Patient patient = CommandUtils.findFromHome(core, bed, cmd.getArg());

        HomeReportSpec.createReport(patient, header, explanation, cmd.getSwitchVal("summary"));
        core.patientMap.deletePatient(patient.getBedNo());
        core.writeJsonFile();
        core.updateUi(result);
    }
}
