package bo.umss.app.in.provider;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ProviderTest {

	@Test
	public void canNotCreateEmptyName() {
		assertThrows(RuntimeException.class, () -> Provider.at("", "77777777"), Provider.INVALID_NAME);

	}

	@Test
	public void canNotCreateEmptyPhoneNumber() {
		assertThrows(RuntimeException.class, () -> Provider.at("Juan Perez", ""), Provider.INVALID_NAME);

	}
}
