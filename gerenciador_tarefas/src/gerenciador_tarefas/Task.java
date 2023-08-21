package gerenciador_tarefas;

public class Task extends TaskManagerTest {
	private String title;
    private String description;
    private String expirationDate;
    private Priority priority;
    
	public Task(String title, String description, String expirationDate, Priority priority) {
		this.title = title;
	    this.description = description;
	    this.expirationDate = expirationDate;
	    this.priority = priority;
	
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	
	
	

}
