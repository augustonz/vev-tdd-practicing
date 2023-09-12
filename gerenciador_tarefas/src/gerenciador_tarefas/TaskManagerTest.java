package gerenciador_tarefas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void createTask() {
        Task task = new Task("Comprar mantimentos", "Comprar leite e pão", "2023-08-25", Priority.MEDIUM);
        List<Task> tasks = taskManager.listAllTasks();
        assertEquals(0, tasks.size());
        assertTrue(taskManager.createTask(task));
        tasks.add(task);
        assertEquals(1, tasks.size());

     
    }

    @Test
    void updateTask() {
        Task task = new Task("Estudar para a prova", "Revisar capítulo 5", "2023-08-30", Priority.HIGH);
        taskManager.createTask(task);

        Task updatedTask = new Task("Estudar para a prova2", "Revisar capítulos 5 e 6", "2023-08-30", Priority.HIGH);
        assertTrue(taskManager.updateTaskByTitle("Estudar para a prova", updatedTask));
    }

    @Test
    void deleteTask() {
        Task task = new Task("Ir à academia", "Fazer treino de pernas", "2023-08-22", Priority.MEDIUM);
        List<Task> tasks = taskManager.listAllTasks();
        taskManager.createTask(task);
        tasks.add(task);
        assertEquals(1, tasks.size());
        assertTrue(taskManager.deleteTaskByTitle("Ir à academia"));
        
    }

    @Test
    void listTasks() {
        taskManager.createTask(new Task("Ler livro", "Capítulo 3 e 4", "2023-08-27", Priority.LOW));
        taskManager.createTask(new Task("Fazer exercícios", "30 minutos de cardio", "2023-08-23", Priority.MEDIUM));
        taskManager.createTask(new Task("Preparar apresentação", "Slides e notas", "2023-08-28", Priority.HIGH));

        List<Task> tasks = taskManager.listAllTasks();
        assertEquals(3, tasks.size());
    }

    @Test
    void setTaskPriority() {
        Task task = new Task("Enviar relatório", "Enviar relatório mensal", "2023-08-29", Priority.LOW);
        taskManager.createTask(task);

        assertTrue(taskManager.setTaskPriorityByTitle("Enviar relatório", Priority.HIGH));
    }
}
