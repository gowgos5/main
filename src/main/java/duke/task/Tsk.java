package duke.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Maintains the associations between task type characters and tasks (e.g. "T" -> ToDoTask). For use in parsing data
 * files.
 */
public enum Tsk {
    TODO("T") {
        public Task getTask(String[] taskArr) throws IndexOutOfBoundsException {
            return new ToDoTask(taskArr[2]);
        }
    },
    EVENT("E") {
        public Task getTask(String[] taskArr) throws DateTimeParseException, IndexOutOfBoundsException {
            LocalDateTime datetime = LocalDateTime.parse(taskArr[3], TimedTask.getDataFormatter());
            return new EventTask(taskArr[2], datetime);
        }
    },
    DLINE("D") {
        public Task getTask(String[] taskArr) throws DateTimeParseException, IndexOutOfBoundsException {
            LocalDateTime datetime = LocalDateTime.parse(taskArr[3], TimedTask.getDataFormatter());
            return new DeadlineTask(taskArr[2], datetime);
        }
    };

    private final String taskTypeStr;

    /**
     * Creates the Tsk enum instance and associates the specified character with it.
     *
     * @param taskTypeStr The string to be associated with the specified task type.
     */
    Tsk(final String taskTypeStr) {
        this.taskTypeStr = taskTypeStr;
    }

    @Override
    public String toString() {
        return taskTypeStr;
    }

    /**
     * Creates and sets up a new task from an array containing the data it is to have. The parameters for the task
     * constructor are extracted by this method.
     *
     * @param taskArr An array containing the data for the task, format specific to each task type.
     * @return A task loaded with the data from taskArr.
     */
    public abstract Task getTask(String[] taskArr);
}