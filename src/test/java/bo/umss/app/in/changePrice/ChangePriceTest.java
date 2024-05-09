package bo.umss.app.in.changePrice;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChangePriceTest {

	public LocalDate currentDate;

	@BeforeEach
	public void setUp() {
		currentDate = LocalDate.now();
	}

	@Test
	public void test1() {
		final Integer currentPrice = 0;
		final Integer oldPrice = 4;
		assertThrows(RuntimeException.class, () -> ChangePrice.at(currentPrice, oldPrice, currentDate),
				ChangePrice.CURRENT_PRICE_CAN_NOT_BE_LESS_THAN);
	}

	@Test
	public void test2() {
		final Integer currentPrice = 5;
		final Integer oldPrice = 0;
		assertThrows(RuntimeException.class, () -> ChangePrice.at(currentPrice, oldPrice, currentDate),
				ChangePrice.OLD_PRICE_CAN_NOT_BE_LESS_THAN);
	}
}
