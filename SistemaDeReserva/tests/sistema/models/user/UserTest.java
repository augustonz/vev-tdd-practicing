package sistema.models.user;

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class UserTest {

	User testUser;
	
	@Before
	public void CreateTestUser() {
		testUser = new User();
	}
	
	@Test
	public void BasicUser() {	  
		assertEquals(testUser.getName(),"Default Name");
		assertEquals(testUser.getCpf(),"000.000.000-00");
	}
}
