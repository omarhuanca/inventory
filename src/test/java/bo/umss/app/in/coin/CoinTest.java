package bo.umss.app.in.coin;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bo.umss.app.in.TestObjectBucket;
import bo.umss.app.in.coin.Coin;

public class CoinTest {

	@Test
	public void test1() {
		assertThrows(RuntimeException.class, () -> Coin.at(TestObjectBucket.CODE_USA, ""),
				Coin.INITIAL_CAN_NOT_BE_BLANK);
	}

	@Test
	public void test2() {
		assertThrows(RuntimeException.class, () -> Coin.at("", TestObjectBucket.NAME_USA), Coin.CODE_CAN_NOT_BE_EMPTY);
	}
}
