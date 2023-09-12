package gerenciador_tarefas_TestesFuncionais;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gerenciador_tarefas.Priority;
import gerenciador_tarefas.Task;
import gerenciador_tarefas.TaskManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class tabela_decisao {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void testCreateTaskWithAllFields() {
        Task task = new Task("Título", "Descrição", "2023-09-30", Priority.HIGH);
        assertTrue(taskManager.createTask(task));
    }

    @Test
    void testCreateTaskWithOnlyTitle() {
        Task task = new Task("Título", "", "", Priority.LOW);
        assertFalse(taskManager.createTask(task));
    }

    @Test
    void testCreateTaskWithAllBlankFields() {
        Task task = new Task("", "", "", Priority.MEDIUM);
        assertFalse(taskManager.createTask(task));
    }

    @Test
    void testUpdateTaskTitle() {
        Task task = new Task("Tarefa Antiga", "Descrição antiga", "2023-08-25", Priority.LOW);
        taskManager.createTask(task);

        Task updatedTask = new Task("Tarefa Atualizada", "Descrição antiga", "2023-08-25", Priority.LOW);
        assertTrue(taskManager.updateTaskByTitle("Tarefa Antiga", updatedTask));
    }

    @Test
    void testUpdateTaskDescription() {
        Task task = new Task("Título", "Descrição antiga", "2023-08-25", Priority.LOW);
        taskManager.createTask(task);

        Task updatedTask = new Task("Título", "Descrição atualizada", "2023-08-25", Priority.LOW);
        assertTrue(taskManager.updateTaskByTitle("Título", updatedTask));
    }

    @Test
    void testUpdateTaskDueDate() {
        Task task = new Task("Título", "Descrição", "2023-08-25", Priority.LOW);
        taskManager.createTask(task);

        Task updatedTask = new Task("Título", "Descrição", "2023-08-30", Priority.LOW);
        assertTrue(taskManager.updateTaskByTitle("Título", updatedTask));
    }

    @Test
    void testUpdateTaskPriority() {
        Task task = new Task("Título", "Descrição", "2023-08-25", Priority.LOW);
        taskManager.createTask(task);

        Task updatedTask = new Task("Título", "Descrição", "2023-08-25", Priority.HIGH);
        assertTrue(taskManager.updateTaskByTitle("Título", updatedTask));
    }

    @Test
    void testUpdateNonExistentTask() {
        Task updatedTask = new Task("Tarefa Inexistente", "Atualização inválida", "2023-08-30", Priority.HIGH);
        assertFalse(taskManager.updateTaskByTitle("Tarefa Inexistente", updatedTask));
    }

    @Test
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

    @Test
    void testDeleteNonExistentTask() {
        assertFalse(taskManager.deleteTaskByTitle("Tarefa Inexistente"));
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

