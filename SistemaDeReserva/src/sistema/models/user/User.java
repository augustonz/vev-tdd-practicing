package sistema.models.user;
//Acredito que esse user default poderia ter sido criado nos testes , não consegui entender bem o motivo para a existencia dele  OK,lógica modificada
//Como seu design ja esta vindo com variaveis em inglês , sugeriria você trocar o atributo "contato" por "contact" OK
public class User {

	String name;
	String cpf;
	String contact;
	
	public User() {
		name="";
		cpf="";
		contact="";
	}
	
	public User(String name, String cpf,String contact) {
		this.name=name;
		this.cpf=cpf;
		this.contact=contact;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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
		return "Usuário emissor - Nome: " + name + ", cpf: " + cpf + ", telefone para contato: " + contact;
	}
}