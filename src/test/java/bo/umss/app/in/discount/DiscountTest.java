package bo.umss.app.in.discount;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DiscountTest {

	@Test
	public void canNotBeLessThanZeroValue() {
		assertThrows(RuntimeException.class, () -> Discount.at(-3), Discount.VALUE_CAN_NOT_LESS_THAN_ZERO);
	}

	@Test
	public void canNotBeValueLessThanZero() {
		assertThrows(RuntimeException.class, () -> Discount.at(-15), Discount.VALUE_CAN_NOT_LESS_THAN_ZERO);
	}
}
