package gerenciador_tarefas_JUnit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gerenciador_tarefas.Priority;
import gerenciador_tarefas.Task;
import gerenciador_tarefas.TaskManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class tabela_decisao_JUnit5 {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void testCreateTaskWithInvalidTitle(String title) {
        Task task = new Task(title, "Descrição", "2023-09-30", Priority.HIGH);
        assertFalse(taskManager.createTask(task));
    }

    @RepeatedTest(10)
    void testUpdateTaskTitle() {
        Task task = new Task("Tarefa Antiga", "Descrição antiga", "2023-08-25", Priority.LOW);
        taskManager.createTask(task);

        Task updatedTask = new Task("Tarefa Atualizada", "Descrição antiga", "2023-08-25", Priority.LOW);
        assertTrue(taskManager.updateTaskByTitle("Tarefa Antiga", updatedTask));
    }

    @RepeatedTest(10)
    void testDeleteExistingTask() {
        Task task = new Task("Título", "Descrição", "2023-08-25", Priority.LOW);
        taskManager.createTask(task);

        assertTrue(taskManager.deleteTaskByTitle("Título"));
    }

    @Test
    void testDeleteExistingTaskWithoutTitle() {
        Task task = new Task("", "Descrição", "2023-08-25", Priority.LOW);
        assertFalse(taskManager.createTask(task));

        assertFalse(taskManager.deleteTaskByTitle(""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Título", "", " "})
    void testDeleteNonExistentTask(String title) {
        assertFalse(taskManager.deleteTaskByTitle(title));
    }

    @Test
    void testListTasksSortedByDueDateAndPriority() {
        Task task1 = new Task("Tarefa 1", "Descrição", "2023-08-25", Priority.LOW);
        Task task2 = new Task("Tarefa 2", "Descrição", "2023-08-24", Priority.HIGH);
        Task task3 = new Task("Tarefa 3", "Descrição", "2023-08-26", Priority.MEDIUM);

        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(task3);

        List<Task> tasks = taskManager.listTasksByPriority(Priority.HIGH);
        assertEquals(1, tasks.size());
    }
}
