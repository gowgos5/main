package duke.ui.card;

import duke.data.Evidence;
import duke.data.Result;

/**
 * A UI card that displays the basic information of a {@code Result}.
 */
public class ResultCard extends EvidenceCard {
    private static final String FXML = "ResultCard.fxml";

    private final Result result;

    /**
     * Constructs a ResultCard object with the specified {@code Result}'s details.
     *
     * @param result Result object.
     * @param index  Displayed index.
     */
    public ResultCard(Result result, int index) {
        super(FXML, result, index);

        this.result = result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        if (super.equals(object)) {
            return true;
        }

        if (!(object instanceof ResultCard)) {
            return false;
        }

        ResultCard card = (ResultCard) object;
        return result.equals(card.getEvidence());
    }

    @Override
    public Evidence getEvidence() {
        return result;
    }
}