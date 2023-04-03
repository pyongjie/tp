package seedu.pettracker.parser;

import seedu.pettracker.commands.AddTaskCommand;
import seedu.pettracker.commands.Command;
import seedu.pettracker.commands.EditStatCommand;
import seedu.pettracker.commands.EditTaskCommand;
import seedu.pettracker.commands.ExitCommand;
import seedu.pettracker.commands.HelpCommand;
import seedu.pettracker.commands.InvalidCommand;
import seedu.pettracker.commands.ListPetCommand;
import seedu.pettracker.commands.ListTasksCommand;
import seedu.pettracker.commands.MarkTaskCommand;
import seedu.pettracker.commands.RemoveTaskCommand;
import seedu.pettracker.commands.UnMarkTaskCommand;
import seedu.pettracker.commands.ScheduleCommand;

import seedu.pettracker.exceptions.UnknownKeywordException;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    private static final Logger logger = Logger.getLogger("CommandLogger");
    final String KEYWORD_EXIT = "exit";
    final String KEYWORD_ADD_PET = "add-pet";
    final String KEYWORD_REMOVE_PET = "remove-pet";
    final String KEYWORD_LIST_PET = "list";
    final String KEYWORD_ADD_STAT = "add-stat";
    final String KEYWORD_REMOVE_STAT = "remove-stat";
    final String KEYWORD_EDIT_STAT = "edit-stat";
    final String KEYWORD_EDIT_TASK = "edit-task";
    final String KEYWORD_ADD_TASK = "add-task";
    final String KEYWORD_REMOVE_TASK = "remove-task";
    final String KEYWORD_LIST_TASKS = "list-tasks";
    final String KEYWORD_MARK_TASK = "mark-task";
    final String KEYWORD_UNMARK_TASK = "unmark-task";
    final String KEYWORD_SCHEDULE_TASKS = "schedule";
    final String KEYWORD_HELP = "help";
    final String COMMAND_STRING_BAD_FORMAT = "Command string is not in the correct format";
    final String UNKNOWN_KEYWORD_MESSAGE = "Keyword is not recognized";
    private static final Pattern COMMAND_FORMAT = Pattern.compile("(?<keyword>\\S+)(?<arguments>.*)");

    public CommandParser() {
    }

    public Command parseCommand(String commandString) {
        logger.log(Level.INFO, "Parser received: " + commandString + "\n");
        try {
            return newCommand(commandString);
        } catch (Exception e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Creates a new command object from the user input string
     *
     * @param commandString Initial String that the user typed in
     * @return new Command object
     */
    public Command newCommand(String commandString) throws Exception {
        final Matcher matcher = COMMAND_FORMAT.matcher(commandString.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(COMMAND_STRING_BAD_FORMAT);
        }

        final String keyword = matcher.group("keyword");
        final String arguments = matcher.group("arguments");

        switch (keyword) {
        case KEYWORD_EXIT:
            return new ExitCommand();
        case KEYWORD_ADD_PET:
            return new AddPetParser().parse(arguments);
        case KEYWORD_REMOVE_PET:
            return new RemovePetParser().parse(arguments);
        case KEYWORD_LIST_PET:
            return new ListPetCommand();
        case KEYWORD_ADD_STAT:
            return new AddStatParser().parse(arguments);
        case KEYWORD_REMOVE_STAT:
            return new RemoveStatParser().parse(arguments);
        case KEYWORD_EDIT_STAT:
            return new EditStatCommand(arguments);
        case KEYWORD_EDIT_TASK:
            return new EditTaskCommand(arguments);
        case KEYWORD_ADD_TASK:
            return new AddTaskCommand(arguments);
        case KEYWORD_REMOVE_TASK:
            return new RemoveTaskCommand(arguments);
        case KEYWORD_LIST_TASKS:
            return new ListTasksCommand();
        case KEYWORD_MARK_TASK:
            return new MarkTaskCommand(arguments);
        case KEYWORD_UNMARK_TASK:
            return new UnMarkTaskCommand(arguments);
        case KEYWORD_SCHEDULE_TASKS: 
            return new ScheduleCommand();
        case KEYWORD_HELP:
            return new HelpCommand();
        default:

            throw new UnknownKeywordException(UNKNOWN_KEYWORD_MESSAGE);
        }
    }
}
