package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CupTest {

	public CodeProduct codeProduct;

	@BeforeEach
	public void setUp() {
		Measurement measurement = Measurement.at(Measurement.CODE_PZA, Measurement.NAME_PZA);
		Line line = Line.at(Line.CODE_CUP, Line.NAME_CUP);
		Coin coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		codeProduct = CodeProduct.at("cup-1", "taza porcelain isaylin", measurement, line, coin);
	}

	@Test
	public void noLetInvalidAmount() {
		assertThrows(RuntimeException.class, () -> Cup.at(codeProduct, -5, 5, 6), Product.INVALID_AMOUNT);
	}
}
