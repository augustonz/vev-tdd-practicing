package gerenciador_tarefas_TestesFuncionais;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gerenciador_tarefas.Priority;
import gerenciador_tarefas.Task;
import gerenciador_tarefas.TaskManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class particao_equivalencia {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void createTaskWithAllFields() {
        Task task = new Task("Comprar mantimentos", "Comprar leite e pão", "2023-08-25", Priority.MEDIUM);
        assertTrue(taskManager.createTask(task));
        assertEquals(1, taskManager.listAllTasks().size());
    }

    @Test
    void createTaskWithOnlyTitle() {
        Task task = new Task("Título", "", "", Priority.LOW);
        assertFalse(taskManager.createTask(task));
        assertEquals(0, taskManager.listAllTasks().size());
    }

    @Test
    void createTaskWithAllBlankFields() {
        Task task = new Task("", "", "", Priority.LOW);
        assertFalse(taskManager.createTask(task));
        assertEquals(0, taskManager.listAllTasks().size());
    }

    @Test
    void updateTaskTitle() {
        Task task = new Task("Tarefa Antiga", "Descrição antiga", "2023-08-25", Priority.LOW);
        taskManager.createTask(task);

        Task updatedTask = new Task("Tarefa Atualizada", "Descrição antiga", "2023-08-25", Priority.LOW);
        assertTrue(taskManager.updateTaskByTitle("Tarefa Antiga", updatedTask));

    }

    @Test
    void updateTaskDescription() {
        Task task = new Task("Título", "Descrição antiga", "2023-08-25", Priority.LOW);
        taskManager.createTask(task);

        Task updatedTask = new Task("Título", "Descrição atualizada", "2023-08-25", Priority.LOW);
        assertTrue(taskManager.updateTaskByTitle("Título", updatedTask));

    }

    @Test
    void updateTaskDueDate() {
        Task task = new Task("Título", "Descrição", "2023-08-25", Priority.LOW);
        taskManager.createTask(task);

        Task updatedTask = new Task("Título", "Descrição", "2023-08-30", Priority.LOW);
        assertTrue(taskManager.updateTaskByTitle("Título", updatedTask));

    }

    @Test
    void updateTaskPriority() {
        Task task = new Task("Título", "Descrição", "2023-08-25", Priority.LOW);
        taskManager.createTask(task);

        Task updatedTask = new Task("Título", "Descrição", "2023-08-25", Priority.HIGH);
        assertTrue(taskManager.updateTaskByTitle("Título", updatedTask));

       
    }

    @Test
    void updateNonExistentTask() {
        Task updatedTask = new Task("Tarefa Inexistente", "Atualização inválida", "2023-08-30", Priority.HIGH);
        assertFalse(taskManager.updateTaskByTitle("Tarefa Inexistente", updatedTask));
    }

    @Test
    void deleteExistingTask() {
        Task task = new Task("Título", "Descrição", "2023-08-25", Priority.LOW);
        taskManager.createTask(task);

        assertTrue(taskManager.deleteTaskByTitle("Título"));
        assertEquals(0, taskManager.listAllTasks().size());
    }

    @Test
    void deleteExistingTaskWithoutTitle() {
        Task task = new Task("", "Descrição", "2023-08-25", Priority.LOW);
        assertFalse(taskManager.createTask(task)); // Certifica-se de que a tarefa não seja criada sem título.

        assertFalse(taskManager.deleteTaskByTitle(""));
        assertEquals(0, taskManager.listAllTasks().size());
    }

    @Test
    void deleteNonExistentTask() {
        assertFalse(taskManager.deleteTaskByTitle("Tarefa Inexistente"));
    }

    @Test
    void listTasksSortedByDueDateAndPriority() {
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
