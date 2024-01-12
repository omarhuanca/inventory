package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CodeProductTest {

	@Test
	public void test1() {
		Measurement measurement = Measurement.at("cm1", "n1");
		Line line = Line.at("cl1", "ln1");
		Coin coin = Coin.at("cc1", "ci1");
		assertThrows(RuntimeException.class, () -> CodeProduct.at("", "d1", measurement, line, coin),
				CodeProduct.INVALID_CODE);
	}

	@Test
	public void test2() {
		Measurement measurement = Measurement.at("cm1", "n1");
		Line line = Line.at("cl1", "ln1");
		Coin coin = Coin.at("cc1", "ci1");
		assertThrows(RuntimeException.class, () -> CodeProduct.at("cp1", "", measurement, line, coin),
				CodeProduct.INVALID_DESCRIPTION);
	}

	@Test
	public void test3() {
		Line line = Line.at("cl1", "ln1");
		Coin coin = Coin.at("cc1", "ci1");
		assertThrows(RuntimeException.class, () -> CodeProduct.at("cp1", "d1", null, line, coin),
				CodeProduct.INVALID_DESCRIPTION);
	}

	@Test
	public void test4() {
		Measurement measurement = Measurement.at("cm1", "n1");
		Coin coin = Coin.at("cc1", "ci1");
		assertThrows(RuntimeException.class, () -> CodeProduct.at("cp1", "d1", measurement, null, coin),
				CodeProduct.INVALID_DESCRIPTION);
	}

	@Test
	public void test5() {
		Measurement measurement = Measurement.at("cm1", "n1");
		Line line = Line.at("cl1", "ln1");
		assertThrows(RuntimeException.class, () -> CodeProduct.at("cp1", "d1", measurement, line, null),
				CodeProduct.INVALID_DESCRIPTION);
	}
}
