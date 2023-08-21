package sistema.models.user;
//Acredito que esse user default poderia ter sido criado nos testes , não consegui entender bem o motivo para a existencia dele 
//Como seu design ja esta vindo com variaveis em inglês , sugeriria você trocar o atributo "contato" por "contact" 
public class User {

	String name;
	String cpf;
	String contato;
	
	public User() {
		this("Default Name","000.000.000-00","+55(83)91234-5678");
	}
	
	public User(String name, String cpf,String contato) {
		this.name=name;
		this.cpf=cpf;
		this.contato=contato;
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

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usu�rio emissor - Nome: " + name + ", cpf: " + cpf + ", telefone para contato: " + contato;
	}
}