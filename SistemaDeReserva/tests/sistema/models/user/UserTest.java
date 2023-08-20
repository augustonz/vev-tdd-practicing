package sistema.models.user;

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class UserTest {

	User testUser;
	
	@Before
	public void createTestUser() {
		testUser = new User();
	}
	
	@Test
	public void basicUserTest() {	  
		assertEquals(testUser.getName(),"Default Name");
		assertEquals(testUser.getCpf(),"000.000.000-00");
		assertEquals(testUser.getContato(),"+55(83)91234-5678");
	}
	
	@Test
	public void specificUserTest() {
		User newUser = new User("Fulano","111.222.333-44","+55(83)90000-0000");
		assertEquals(newUser.getName(),"Fulano");
		assertEquals(newUser.getCpf(),"111.222.333-44");
		assertEquals(newUser.getContato(),"+55(83)90000-0000");
	}
	
	@Test
	public void UserToStringTest() {
		assertEquals(testUser.toString(),"Usuário emissor - Nome: Default Name, cpf: 000.000.000-00, telefone para contato: +55(83)91234-5678");
	}
}
