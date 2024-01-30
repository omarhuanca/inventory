package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class GymDateTest {

	@Test
	public void startDateCanNotBeNull() {
		assertThrows(RuntimeException.class, () -> GymDate.at(null, LocalDate.now()), GymDate.INVALID_START_DATE);
	}

	@Test
	public void endDateCanNotBeNull() {
		assertThrows(RuntimeException.class, () -> GymDate.at(LocalDate.now(), null), GymDate.INVALID_END_DATE);
	}

	@Test
	public void verifyStartDateCanNotBeGreatherEndDate() {
		GymDate gymDate = GymDate.at(LocalDate.of(2024, Month.JANUARY, 30), LocalDate.of(2024, Month.JANUARY, 1));
		assertThrows(RuntimeException.class, () -> gymDate.validateCorrectMonth(),
				GymDate.INVALID_START_DATE_GREATHER_THAN_END_DATE);
	}

	@Test
	public void verifyEndDateCanNotBeLessThanStartDate() {
		GymDate gymDate = GymDate.at(LocalDate.of(2024, Month.FEBRUARY, 1), LocalDate.of(2024, Month.JANUARY, 1));
		assertThrows(RuntimeException.class, () -> gymDate.validateCorrectMonth(),
				GymDate.INVALID_END_DATE_LESS_THAN_START_DATE);
	}
}
