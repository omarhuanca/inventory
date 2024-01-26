package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CoinTest {

	@Test
	public void test1() {
		assertThrows(RuntimeException.class, () -> Coin.at(Coin.CODE_USA, ""), Coin.INVALID_INITIAL);
	}

	@Test
	public void test2() {
		assertThrows(RuntimeException.class, () -> Coin.at("", Coin.NAME_USA), Coin.INVALID_CODE);
	}
}
