package seedu.pettracker.commands;

import seedu.pettracker.exceptions.IllegalArgException;
import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.TaskList;

public class UnMarkTaskCommand extends Command{
    final String NEGATIVE_ARG_MESSAGE = "Task number must be a positive integer";
    protected int taskNumber;
    public UnMarkTaskCommand(int taskNumber) throws IllegalArgException {
        super();
        if (taskNumber <= 0) {
            throw new IllegalArgException(NEGATIVE_ARG_MESSAGE);
        }
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the given command
     *
     * @param ui Ui to do printing if required
     * @param storage Storage to save files if required
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        try {
            TaskList.markTask(taskNumber, false);
            TaskList.saveTasksToStorage(storage, ui);
            String unmarkTaskDescription = TaskList.getSpecificTaskDescription(taskNumber);
            ui.unmarkTaskCommandMessage(unmarkTaskDescription);
        } catch (IndexOutOfBoundsException e) {
            ui.taskNumberOutOfBoundsMessage();
        }
    }

    /**
     * Sets isExit to be true to exit the program
     *
     * @return isExit boolean value for program to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
