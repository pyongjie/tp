package seedu.pettracker.data;

import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

public class TaskList {
    private static final Logger logger = Logger.getLogger("TaskListLogger");
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static int numberOfTasks;

    public TaskList() {
        numberOfTasks = 0;
    }

    /**
     * Adds a new task to the task list
     *
     * @param todoDescription Description of task to be added
     */
    public static void addTask(String todoDescription) {
        Task newTask = new Task(todoDescription);
        logger.log(Level.INFO, "New task added: " + todoDescription);
        taskList.add(newTask);
        numberOfTasks += 1;
    }
    
    public static void addTask(String todoDescription, String time) {
        Task newTask = new Task(todoDescription, time);
        logger.log(Level.INFO, "New task added: " + todoDescription + " with time: " + time);
        taskList.add(newTask);
        numberOfTasks += 1;
    }

<<<<<<< HEAD
    public static void editTask(int taskNumber, String newDescription, String newTime) {
=======
    public static void addTask(String todoDescription, LocalDate deadline) {
        Task newTask = new Task(todoDescription, deadline);
        logger.log(Level.INFO, "New task added with deadline: " + todoDescription);
        taskList.add(newTask);
        numberOfTasks += 1;
    }

    public static void editTask(int taskNumber, String newDescription) {
>>>>>>> 843cf9ef0cd85a75d055b94a47381e0ea991e2b5
        Task task = taskList.get(taskNumber - 1);
        task.description = newDescription;
        task.time = newTime;
    }

    /**
     * Removes a task from the task list
     *
     * @param taskNumber Number of task to be removed
     */
    public static void removeTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
        logger.log(Level.INFO, "Task removed: " + taskNumber);
        numberOfTasks -= 1;
    }

    /**
     * Prints the list of tasks
     */
    public static void listTasks() {
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).getStatusIcon() + " " + taskList.get(i).description);
            if (taskList.get(i).deadline != null) {
                System.out.println(" (Deadline: " + taskList.get(i).deadline + ")");
            }
        }
    }

    /**
     * Marks a task as done or not done
     *
     * @param taskNumber Number of task to be marked as done
     * @param isDone Boolean value to mark task as done or not done
     */
    public static void markTask(int taskNumber, boolean isDone) {
        taskList.get(taskNumber - 1).isDone = isDone;
        logger.log(Level.INFO, "Task marked as " + isDone + " : " + taskNumber);
    }

    public static void saveTasksToStorage(Storage storage, Ui ui){
        storage.saveTasks(taskList,ui);
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

}
