package sistema.models.user;

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class UserTest {

	User testUser;
	
	@Before
	public void createTestUser() {
		testUser = new User("Fulano","111.222.333-44","+55(83)90000-0000");
	}
	
	@Test
	public void BaiscUserTest() {
		assertEquals(testUser.getName(),"Fulano");
		assertEquals(testUser.getCpf(),"111.222.333-44");
		assertEquals(testUser.getContact(),"+55(83)90000-0000");
	}
	
	@Test
	public void UserToStringTest() {
		assertEquals(testUser.toString(),"Usuário emissor - Nome: Fulano, cpf: 111.222.333-44, telefone para contato: +55(83)90000-0000");
	}
}
