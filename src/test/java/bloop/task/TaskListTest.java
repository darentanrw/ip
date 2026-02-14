package bloop.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void size_emptyList_returnsZero() {
        assertEquals(0, taskList.size());
    }

    @Test
    void addTask_singleTask_sizeIncreases() {
        taskList.addTask(new ToDoTask("read book"));
        assertEquals(1, taskList.size());
    }

    @Test
    void addTask_multipleTasks_sizeMatchesCount() {
        taskList.addTask(new ToDoTask("task 1"));
        taskList.addTask(new ToDoTask("task 2"));
        taskList.addTask(new ToDoTask("task 3"));
        assertEquals(3, taskList.size());
    }

    @Test
    void getTask_validIndex_returnsCorrectTask() {
        Task todo = new ToDoTask("read book");
        taskList.addTask(todo);
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    void getTask_multipleTasksRetrievedByIndex_returnsCorrectTasks() {
        Task first = new ToDoTask("first");
        Task second = new ToDoTask("second");
        taskList.addTask(first);
        taskList.addTask(second);
        assertEquals(first, taskList.getTask(0));
        assertEquals(second, taskList.getTask(1));
    }

    @Test
    void getTask_invalidIndex_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(0));
    }

    @Test
    void deleteTask_validIndex_removesAndReturnsTask() {
        Task todo = new ToDoTask("read book");
        taskList.addTask(todo);
        Task removed = taskList.deleteTask(0);
        assertEquals(todo, removed);
        assertEquals(0, taskList.size());
    }

    @Test
    void deleteTask_middleElement_shiftsList() {
        taskList.addTask(new ToDoTask("first"));
        taskList.addTask(new ToDoTask("second"));
        taskList.addTask(new ToDoTask("third"));
        taskList.deleteTask(1);
        assertEquals(2, taskList.size());
        assertEquals("[T] [ ] third", taskList.getTask(1).toString());
    }

    @Test
    void deleteTask_invalidIndex_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(0));
    }

    @Test
    void isValidIndex_emptyList_returnsFalse() {
        assertFalse(taskList.isValidIndex(0));
    }

    @Test
    void isValidIndex_negativeIndex_returnsFalse() {
        taskList.addTask(new ToDoTask("task"));
        assertFalse(taskList.isValidIndex(-1));
    }

    @Test
    void isValidIndex_indexEqualToSize_returnsFalse() {
        taskList.addTask(new ToDoTask("task"));
        assertFalse(taskList.isValidIndex(1));
    }

    @Test
    void isValidIndex_validIndex_returnsTrue() {
        taskList.addTask(new ToDoTask("task"));
        assertTrue(taskList.isValidIndex(0));
    }

    @Test
    void isValidIndex_lastValidIndex_returnsTrue() {
        taskList.addTask(new ToDoTask("a"));
        taskList.addTask(new ToDoTask("b"));
        taskList.addTask(new ToDoTask("c"));
        assertTrue(taskList.isValidIndex(2));
    }

    @Test
    void getTasks_returnsUnderlyingList() {
        Task t1 = new ToDoTask("alpha");
        Task t2 = new ToDoTask("beta");
        taskList.addTask(t1);
        taskList.addTask(t2);
        ArrayList<Task> tasks = taskList.getTasks();
        assertEquals(2, tasks.size());
        assertEquals(t1, tasks.get(0));
        assertEquals(t2, tasks.get(1));
    }

    @Test
    void constructor_withExistingList_usesProvidedTasks() {
        ArrayList<Task> existing = new ArrayList<>();
        existing.add(new ToDoTask("pre-existing"));
        TaskList fromExisting = new TaskList(existing);
        assertEquals(1, fromExisting.size());
        assertEquals("[T] [ ] pre-existing", fromExisting.getTask(0).toString());
    }

    @Test
    void markTask_unmarkedTask_showsX() {
        taskList.addTask(new ToDoTask("read book"));
        taskList.getTask(0).setStatus(true);
        assertEquals("[T] [X] read book", taskList.getTask(0).toString());
    }

    @Test
    void unmarkTask_markedTask_showsSpace() {
        taskList.addTask(new ToDoTask("read book"));
        taskList.getTask(0).setStatus(true);
        taskList.getTask(0).setStatus(false);
        assertEquals("[T] [ ] read book", taskList.getTask(0).toString());
    }

    @Test
    void markTask_alreadyMarked_remainsMarked() {
        taskList.addTask(new ToDoTask("read book"));
        taskList.getTask(0).setStatus(true);
        taskList.getTask(0).setStatus(true);
        assertEquals("[T] [X] read book", taskList.getTask(0).toString());
    }

    @Test
    void unmarkTask_alreadyUnmarked_remainsUnmarked() {
        taskList.addTask(new ToDoTask("read book"));
        taskList.getTask(0).setStatus(false);
        assertEquals("[T] [ ] read book", taskList.getTask(0).toString());
    }

    @Test
    void markTask_multipleTasksIndependently_onlyTargetChanges() {
        taskList.addTask(new ToDoTask("first"));
        taskList.addTask(new ToDoTask("second"));
        taskList.addTask(new ToDoTask("third"));
        taskList.getTask(1).setStatus(true);
        assertEquals("[T] [ ] first", taskList.getTask(0).toString());
        assertEquals("[T] [X] second", taskList.getTask(1).toString());
        assertEquals("[T] [ ] third", taskList.getTask(2).toString());
    }

    @Test
    void markTask_toStorageReflectsStatus() {
        taskList.addTask(new ToDoTask("read book"));
        taskList.getTask(0).setStatus(true);
        ArrayList<String> storage = taskList.getTask(0).toStorage();
        assertEquals("X", storage.get(1));
    }

    @Test
    void unmarkTask_toStorageReflectsStatus() {
        taskList.addTask(new ToDoTask("read book"));
        taskList.getTask(0).setStatus(true);
        taskList.getTask(0).setStatus(false);
        ArrayList<String> storage = taskList.getTask(0).toStorage();
        assertEquals("", storage.get(1));
    }

    @Test
    void findTasks_matchingKeyword_returnsMatchingTasks() {
        taskList.addTask(new ToDoTask("read book"));
        taskList.addTask(new ToDoTask("return book"));
        taskList.addTask(new ToDoTask("buy groceries"));
        ArrayList<Task> results = taskList.findTasks("book");
        assertEquals(2, results.size());
        assertEquals("read book", results.get(0).getTaskName());
        assertEquals("return book", results.get(1).getTaskName());
    }

    @Test
    void findTasks_noMatch_returnsEmptyList() {
        taskList.addTask(new ToDoTask("read book"));
        ArrayList<Task> results = taskList.findTasks("homework");
        assertTrue(results.isEmpty());
    }

    @Test
    void findTasks_singleMatch_returnsOneTask() {
        taskList.addTask(new ToDoTask("read book"));
        taskList.addTask(new ToDoTask("buy groceries"));
        ArrayList<Task> results = taskList.findTasks("groceries");
        assertEquals(1, results.size());
        assertEquals("buy groceries", results.get(0).getTaskName());
    }

    @Test
    void findTasks_emptyTaskList_returnsEmptyList() {
        ArrayList<Task> results = taskList.findTasks("book");
        assertTrue(results.isEmpty());
    }
}
