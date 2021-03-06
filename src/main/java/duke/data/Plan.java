package duke.data;

import duke.exception.DukeException;
import duke.exception.DukeFatalException;
import duke.ui.card.PlanCard;
import duke.ui.context.Context;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Plan extends SummaryTreatment {

    private static final List<String> statusArr = Arrays.asList("Not done/ordered", "In progress", "Completed");
    private static final int STATUSIDX_COMPLETE = 2;

    /**
     * Represents the Plan devised by the doctor to treat a symptom or impression of a patient.
     * A Plan object corresponds to the plan devised by a doctor to treat the signs and symptoms of a Patient,
     * the impression that led to that particular plan being necessary, the status of the treatment,
     * a description of the status, the summary of the plan as well as an integer between 1-4
     * representing the priority or significance of the plan.
     * Attributes:
     * @param name the generic plan name
     * @param impression the impression object the plan is tagged to
     * @param priority the priority level of the plan
     * @param status the current status of the plan
     * @param summary the summary of what the plan entails
     */
    public Plan(String name, Impression impression, int priority, String status, String summary) throws DukeException {
        super(name, impression, priority, status, summary);
    }

    @Override
    public String toReportString() {
        return null;
    }

    public String getStatusStr() {
        return statusArr.get(getStatusIdx());
    }

    @Override
    public List<String> getStatusArr() {
        return Collections.unmodifiableList(statusArr);
    }

    @Override
    public PlanCard toCard() throws DukeFatalException {
        return new PlanCard(this);
    }

    @Override
    public Context toContext() {
        return Context.PLAN;
    }

    @Override
    public boolean isFollowUp() {
        return getStatusIdx() < STATUSIDX_COMPLETE;
    }
}
