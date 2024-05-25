package bo.umss.app.in.discount;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bo.umss.app.in.coin.Coin;

public class DiscountTest {

	@Test
	public void canNotBeValueLessThanZero() {
		Coin coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		assertThrows(RuntimeException.class, () -> Discount.at(-15, coin), Discount.VALUE_CAN_NOT_LESS_THAN_ZERO);
	}
	
	@Test
	public void canNotBeNullCoin() {
		assertThrows(RuntimeException.class, () -> Discount.at(5, null), Discount.COIN_CAN_NOT_BE_NULL);
	}
}
