package bo.umss.app.in.client;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GymClientTest {

	@Test
	public void firstnameCanNotBeEmpty() {
		assertThrows(RuntimeException.class, () -> GymClient.at("", "Perez"), GymClient.INVALID_FIRST_NAME);
	}

	@Test
	public void lastnameCanNotBeEmpty() {
		assertThrows(RuntimeException.class, () -> GymClient.at("Juan", ""), GymClient.INVALID_LAST_NAME);
	}
}
