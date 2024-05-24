package bo.umss.app.in.price;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bo.umss.app.in.coin.Coin;

public class PriceTest {

	@Test
	public void canNotBeLessThanZeroValue() {
		Coin coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		assertThrows(RuntimeException.class, () -> Price.at(0, coin), Price.VALUE_CAN_NOT_BE_LESS_ZERO);
	}

	@Test
	public void canNotBeNullCoin() {
		assertThrows(RuntimeException.class, () -> Price.at(50, null), Price.COIN_CAN_NOT_BE_NULL);
	}
}
