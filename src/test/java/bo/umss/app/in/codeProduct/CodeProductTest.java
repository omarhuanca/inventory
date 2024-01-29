package bo.umss.app.in.codeProduct;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.codeProduct.CodeProduct;
import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.measurement.Measurement;

public class CodeProductTest {

	public Measurement measurement;
	public Line line;
	public Coin coin;

	@BeforeEach
	public void setUp() {
		measurement = Measurement.at("pza", "piece");
		line = Line.at("plate1", "plate");
		coin = Coin.at("coin1", "USD");
	}

	@Test
	public void test1() {
		assertThrows(RuntimeException.class,
				() -> CodeProduct.at("", CodeProduct.DEFINE_DESCRIPTION, measurement, line, coin),
				CodeProduct.INVALID_CODE);
	}

	@Test
	public void test2() {
		assertThrows(RuntimeException.class,
				() -> CodeProduct.at(CodeProduct.DEFINE_CODE_PRODUCT, "", measurement, line, coin),
				CodeProduct.INVALID_DESCRIPTION);
	}

	@Test
	public void test3() {
		assertThrows(RuntimeException.class,
				() -> CodeProduct.at(CodeProduct.DEFINE_CODE_PRODUCT, CodeProduct.DEFINE_DESCRIPTION, null, line, coin),
				CodeProduct.INVALID_DESCRIPTION);
	}

	@Test
	public void test4() {
		assertThrows(RuntimeException.class, () -> CodeProduct.at(CodeProduct.DEFINE_CODE_PRODUCT,
				CodeProduct.DEFINE_DESCRIPTION, measurement, null, coin), CodeProduct.INVALID_DESCRIPTION);
	}

	@Test
	public void test5() {
		assertThrows(RuntimeException.class, () -> CodeProduct.at(CodeProduct.DEFINE_CODE_PRODUCT,
				CodeProduct.DEFINE_DESCRIPTION, measurement, line, null), CodeProduct.INVALID_DESCRIPTION);
	}
}
