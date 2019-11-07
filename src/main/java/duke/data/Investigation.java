package duke.data;

import duke.ui.card.InvestigationCard;
import duke.ui.card.UiCard;
import duke.ui.context.Context;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Investigation extends Treatment {

    private static final List<String> statusArr = Arrays.asList("Not ordered", "In progress");

    /**
     * Represents the investigation needed to investigate an impression the Doctor has about a patient.
     * A Investigation object corresponds to the investigation a doctor needs to better understand the
     * signs and symptoms of a Patient, the impression that led to that particular investigation being necessary,
     * the status of the treatment, a description of the status, a summary of why the investigation is needed
     * as well as an integer between 1-4 representing the priority or significance of the investigation.
     * Attributes:
     * @param name the investigation needed
     * @param impression the impression object the investigation is tagged to
     * @param priority the priority level of the investigation
     * @param status the current status of the investigation
     * @param summary description of the investigation
     */
    public Investigation(String name, Impression impression, int priority,
                         int status, String summary) {
        super(name, impression, priority, status);
        this.summary = summary;
    }

    /**
     * This toResult function returns the result or conclusion from the investigation done.
     * @param resultSummary description of the result
     * @return the result object
     */
    public Result toResult(String resultSummary) {
        return new Result(getName() + " Result", (Impression) getParent(), getPriority(),
                (resultSummary == null) ? "" : resultSummary);
    }

    @Override
    public String toString() {
        String informationString;
        informationString = "Summary " + this.summary + "\n";
        return super.toString() + informationString;
    }

    //TODO complete
    @Override
    public String toReportString() {
        return null;
    }

    public String getStatusStr() {
        return statusArr.get(getStatusIdx());
    }

    public static List<String> getStatusArr() {
        return Collections.unmodifiableList(statusArr);
    }

    public UiCard toCard() {
        return new InvestigationCard(this);
    }

    @Override
    public Context toContext() {
        return Context.INVESTIGATION;
    }
}
