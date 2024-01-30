package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class ServiceTest {

	@Test
	public void priceServieCanNotBeZero() {
		GymDate gymDate = GymDate.at(LocalDate.of(2024, Month.JANUARY, 2), LocalDate.now());
		assertThrows(RuntimeException.class, () -> Service.at(0, gymDate), Service.INVALID_GYM_PRICE);
	}

	@Test
	public void gymDateCanNotBeNull() {
		assertThrows(RuntimeException.class, () -> Service.at(0, null), Service.INVALID_GYM_PRICE);
	}

	
}
