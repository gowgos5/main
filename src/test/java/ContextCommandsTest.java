import duke.command.ByeCommand;
import duke.command.Parser;
import duke.command.home.HomeHelpCommand;
import duke.command.home.HomeNewCommand;
import duke.exception.DukeException;
import duke.ui.context.Context;
import duke.ui.context.UiContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for the parser's ability to correctly identify Commands in different contexts.
 */
public class ContextCommandsTest {

    private static UiContext testUiContext = new UiContext();

    @Test
    public void parseCommands_validHomeCommands_correctCommandsReturned() {
        testUiContext.setContext(Context.HOME, null);
        Parser actualParser = new Parser(testUiContext);
        try {
            assertEquals(ByeCommand.class, actualParser.parse("bye").getClass());
            assertEquals(actualParser.parse("new Hello -b 100 -a world").getClass(),
                    HomeNewCommand.class);
            assertEquals(actualParser.parse("help").getClass(),
                    HomeHelpCommand.class);
        } catch (DukeException excp) {
            fail("Exception thrown while extracting valid commands!");
        }
    }
}
