package duke.command.impression;

import duke.DukeCore;
import duke.command.ArgLevel;
import duke.command.Switch;
import duke.data.DukeData;
import duke.data.DukeObject;
import duke.data.Impression;
import duke.exception.DukeException;
import duke.exception.DukeHelpException;

public class ImpressionEditSpec extends ImpressionObjSpec {

    private static final ImpressionEditSpec spec = new ImpressionEditSpec();

    public static ImpressionEditSpec getSpec() {
        return spec;
    }

    private ImpressionEditSpec() {
        cmdArgLevel = ArgLevel.OPTIONAL;
        initSwitches(
                new Switch("append", null, true, ArgLevel.NONE, "a"),
                new Switch("impression", String.class, true, ArgLevel.NONE, "im"),
                new Switch("evidence", String.class, true, ArgLevel.REQUIRED, "e"),
                new Switch("treatment", String.class, true, ArgLevel.REQUIRED, "t"),
                new Switch("name", String.class, true, ArgLevel.REQUIRED, "n"),
                new Switch("status", String.class, true, ArgLevel.REQUIRED, "sta"),
                new Switch("priority", Integer.class, true, ArgLevel.REQUIRED, "pri"),
                new Switch("summary", String.class, true, ArgLevel.REQUIRED, "sum"),
                new Switch("dose", String.class, true, ArgLevel.REQUIRED, "d"),
                new Switch("date", String.class, true, ArgLevel.REQUIRED, "da"),
                new Switch("duration", String.class, true, ArgLevel.REQUIRED, "du"),
                new Switch("subjective", null, true, ArgLevel.NONE, "sub"),
                new Switch("objective", null, true, ArgLevel.NONE, "obj"),
                new Switch("description", String.class, true, ArgLevel.REQUIRED, "desc")
        );
    }

    @Override
    protected void execute(DukeCore core) throws DukeException {
        boolean isAppending = false;
        if (cmd.isSwitchSet("append")) {
            isAppending = true;
        }

        if (cmd.isSwitchSet("impression")) {
            if (cmd.getArg() == null && cmd.getSwitchVal("evidence") == null
                && cmd.getSwitchVal("treatment") == null) { // edit impression
                editImpression(ImpressionUtils.getImpression(core), isAppending);
                core.writeJsonFile();
                core.updateUi("Updated details of this Impression!");
            } else {
                throw new DukeHelpException("Please specify whether you want to edit this impression, or a treatment "
                        + "or evidence!", cmd);
            }
        } else {
            if (cmd.isSwitchSet("description")) {
                throw new DukeHelpException("Descriptions are only for impressions!", cmd);
            }
            super.execute(core); // find specified DukeData
        }
    }

    @Override
    protected void executeWithObj(DukeCore core, DukeObject obj) throws DukeException {
        ImpressionUtils.editData(core, cmd, (DukeData) obj);
    }

    private void editImpression(Impression impression, boolean isAppending) {
        String newName = cmd.getSwitchVal("name");
        if (newName != null) {
            impression.setName((isAppending) ? impression.getName() + " " + newName : newName);
        }

        String newDesc = cmd.getSwitchVal("description");
        if (newDesc != null) {
            impression.setDescription((isAppending) ? impression.getDescription() + "\n\n" + newDesc : newDesc);
        }
    }
}
