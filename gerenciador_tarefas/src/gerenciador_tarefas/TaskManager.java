package gerenciador_tarefas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


// Bom uso de retornos em métodos para poder verificar se foi possível realizar uma ação
public class TaskManager extends TaskManagerTest {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    
    public boolean createTask(Task task) {
    	if(task.getTitle().equals(" ") || task.getTitle().isEmpty()|| task.getDescription().isEmpty() || task.getExpirationDate().isEmpty()) {
    		return false;
    	}else {
    		tasks.add(task);
    		return true;
    	}
        
    }

    public boolean updateTaskByTitle(String title, Task updatedTask) {
    	
        for (int i = 0; i < tasks.size(); i++) {
        	
            if (tasks.get(i).getTitle().equals(title)) {
                tasks.set(i, updatedTask);

                return true;
            }
        }
        return false;
    }

    public boolean deleteTaskByTitle(String title) {
        return tasks.removeIf(task -> task.getTitle().equals(title));
    }

    public List<Task> listTasksByDate(String date) {
        return tasks.stream()
            .filter(task -> task.getExpirationDate().equals(date))
            .collect(Collectors.toList());
    }

    public List<Task> listTasksByPriority(Priority priority) {
        return tasks.stream()
            .filter(task -> task.getPriority() == priority)
            .collect(Collectors.toList());
    }

    public boolean setTaskPriorityByTitle(String title, Priority priority) {
        for (Task task : tasks) {
            if (task.getTitle().equals(title)) {
                task.setPriority(priority);
                return true;
            }
        }
        return false;
    }

    public List<Task> listAllTasks() {
        return new ArrayList<>(tasks);
    }
}
