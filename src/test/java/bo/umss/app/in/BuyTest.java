package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BuyTest {


	@Test
	public void codeProductCanNotBeNull() {
		assertThrows(RuntimeException.class, () -> Buy.at("bowl7 a round plate of porcelain", null),
				Buy.INVALID_PRODUCT);
	}

	@Test
	public void descriptionCanNotBeEmpty() {
		Measurement measurement = Measurement.at("piece", "Unit");
		Line line = Line.at("plate", "family's plate");
		Coin coin = Coin.at("usd", "$");
		CodeProduct codeProduct = CodeProduct.at("PLA-1", "bolw8 a round plate of porcelain", measurement, line, coin);

		Product product = Product.at(codeProduct, 1, 5, 6);

		assertThrows(RuntimeException.class, () -> Buy.at("", product), Buy.INVALID_DESCRIPTION);
	}
}
