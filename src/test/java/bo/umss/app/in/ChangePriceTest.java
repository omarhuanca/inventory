package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ChangePriceTest {

	@Test
	public void test1() {
		final Integer currentPrice = 0;
		final Integer oldPrice = 4;
		final LocalDate currentDate = LocalDate.now();
		assertThrows(RuntimeException.class, () -> ChangePrice.at(currentPrice, oldPrice, currentDate),
				ChangePrice.INVALID_CURRENT_PRICE);
	}

	@Test
	public void test2() {

		final Integer currentPrice = 5;
		final Integer oldPrice = 0;
		final LocalDate currentDate = LocalDate.now();
		assertThrows(RuntimeException.class, () -> ChangePrice.at(currentPrice, oldPrice, currentDate),
				ChangePrice.INVALID_OLD_PRICE);
	}
}
