package sistema.models.user;

public class User {

	String name;
	String cpf;
	
	public User() {
		this("Default Name","000.000.000-00");
	}
	
	public User(String name, String cpf) {
		this.name=name;
		this.cpf=cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}