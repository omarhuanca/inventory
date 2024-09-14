package bo.umss.app.in.price;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.TestObjectBucket;
import bo.umss.app.in.coin.Coin;

public class PriceTest {

	private Coin coin;
	private Price potentialPrice;

	@BeforeEach
	public void setUp() {
		coin = Coin.at(TestObjectBucket.CODE_USA);
		potentialPrice = Price.at(30.0, coin);
	}

	@Test
	public void canNotBeLessThanZeroValue() {
		assertThrows(RuntimeException.class, () -> Price.at(-3.0, coin), Price.VALUE_CAN_NOT_BE_LESS_ZERO);
	}

	@Test
	public void canNotEqualZeroValue() {
		assertThrows(RuntimeException.class, () -> Price.at(0.0, coin), Price.VALUE_CAN_NOT_BE_LESS_ZERO);
	}

	@Test
	public void verifyValue() {
		assertTrue(potentialPrice.compareValueLessThanPotentialValue(31));
	}

	@Test
	public void verifySendValue() {
		assertTrue(potentialPrice.compareOtherValue(30.0));
	}

	@Test
	public void addTwoPriceSale() {
		Price priceTwo = Price.at(4.0, coin);

		assertEquals(34.0, potentialPrice.addWithOtherPrice(priceTwo));
	}

	@Test
	public void verifyValueDoesntNegative() {
		assertFalse(potentialPrice.isNegativeValue());
	}

	@Test
	public void verifyValueIsNegative() {
		potentialPrice.setValue(-8.0);

		assertTrue(potentialPrice.isNegativeValue());
	}
}
