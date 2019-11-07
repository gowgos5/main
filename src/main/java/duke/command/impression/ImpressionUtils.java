package duke.command.impression;

import duke.DukeCore;
import duke.data.DukeData;
import duke.data.Impression;
import duke.data.Patient;
import duke.data.SearchResults;
import duke.exception.DukeException;
import duke.exception.DukeUtilException;

import java.util.List;

public class ImpressionUtils {

    /**
     * Gets the current Impression being displayed.
     * @param core The DukeCore that the app is running on.
     * @return The current Impression.
     * @throws DukeException If the current context is not an Impression, should not happen.
     */
    public static Impression getImpression(DukeCore core) throws DukeException {
        try {
            return (Impression) core.uiContext.getObject();
        } catch (ClassCastException excp) {
            throw new DukeException("The current context is not an Impression!");
        }
    }

    /**
     * Gets the Patient that is the parent of an Impression.
     * @param impression The impression whose parent we are trying to get.
     * @return The parent Patient of an Impression.
     * @throws DukeException If the parent of the Impression is not a Patient, should not happen.
     */
    public static Patient getPatient(Impression impression) throws DukeException {
        try {
            return (Patient) impression.getParent();
        } catch (ClassCastException excp) {
            throw new DukeException("This Impression is not attached to a Patient!");
        }
    }

    /**
     * General-purpose function for finding DukeData by name or index. One and only one of allStr (search evidences
     * and treatments), treatStr (search treatments) and evidStr (search evidences) is to be non-null. The appropriate
     * search in the names will then be performed.
     * @param allStr A search term that will be searched for amongst both treatments and evidences.
     * @param evidStr A search term that will be searched for amongst evidences.
     * @param treatStr A search term that will be searched for amongst treatments.
     * @param impression The impression whose DukeData we want to find.
     * @return The required DukeData matching the user's query.
     * @throws DukeException If a matching evidence or treatment cannot be found, given the provided search terms, or
     *                       more than one search term was non-null.
     */
    public static SearchResults getData(String allStr, String evidStr, String treatStr, Impression impression)
            throws DukeException {
        DukeData data;
        DukeException dataNotFound;
        SearchResults results;

        // TODO handle idx
        if (allStr != null && evidStr == null && treatStr == null) {
            results = impression.findByName(allStr);
            dataNotFound = new DukeException("Can't find any data item with that name!");
        } else if (allStr == null && evidStr != null && treatStr == null) {
            results = impression.findEvidencesByName(evidStr);
            dataNotFound = new DukeException("Can't find any evidences with that name!");
        } else if (allStr == null && evidStr == null && treatStr != null) {
            results = impression.findTreatmentsByName(treatStr);
            dataNotFound = new DukeException("Can't find any treatments with that name!");
        } else {
            throw new DukeUtilException("I don't know what you want me to look for!");
        }

        if (results.getCount() != 0) {
            return results;
        } else {
            throw dataNotFound;
        }
    }

    /**
     * Checks if an integer is a valid priority value.
     * @param priority The integer to check.
     * @throws DukeUtilException If the priority is out of the valid range.
     */
    public static void checkPriority(int priority) throws DukeUtilException {
        if (priority < 0 || priority > DukeData.PRIORITY_MAX) {
            throw new DukeUtilException("Priority must be between 0 and " + DukeData.PRIORITY_MAX + "!");
        }
    }

    /**
     * Checks if a status is a string or an integer, and returns the appropriate integer if it is a string.
     * @param status The String supplied as an argument to the status switch.
     * @param statusList The status descriptions that the numeric value of the status represent. The numeric value of
     *                  the status is the index of the corresponding description in the array.
     * @return The Integer that the string represents, or 0 if it is null.
     * @throws NumberFormatException If the string is not a valid representation of an integer.
     */
    public static int processStatus(String status, List<String> statusList)
            throws DukeUtilException {
        assert (status != null);
        if ("".equals(status)) {
            return 0;
        } else {
            try {
                int convertedStatus = Integer.parseInt(status);
                if (convertedStatus < 0 || convertedStatus >= statusList.size()) {
                    throw new DukeUtilException(status + "is not a valid numeric value for the status!");
                }
                return convertedStatus;
            } catch (NumberFormatException excp) { // not numeric
                // TODO: parse with autocorrect?
                for (int i = 0; i < statusList.size(); ++i) {
                    if (statusList.get(i).equalsIgnoreCase(status)) {
                        return i;
                    }
                }
                throw new DukeUtilException("'" + status + "' is not a valid status name!");
            }
        }
    }
}
