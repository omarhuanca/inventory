package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ProductTest {

	@Test
	public void test13() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);
		
		assertThrows(RuntimeException.class, () -> Product.at(codeProduct, 0, 5, 6), Product.INVALID_AMOUNT);
	}

	@Test
	public void test14() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);

		assertThrows(RuntimeException.class, () -> Product.at(codeProduct, -5, 5, 6), Product.INVALID_AMOUNT);
	}

	@Test
	public void test15() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);

		assertThrows(RuntimeException.class, () -> Product.at(codeProduct, 1, 0, 6), Product.INVALID_PRICE_COST);
	}

	@Test
	public void test16() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);

		assertThrows(RuntimeException.class, () -> Product.at(codeProduct, 1, 5, 0), Product.INVALID_PRICE_SALE);
	}

	@Test
	public void test2() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);

		Product product = Product.at(codeProduct, 1, 5, 10);
		assertTrue(product.alreadyCodeProduct());
	}

	@Test
	public void test3() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);

		Product product = Product.at(codeProduct, 1, 5, 10);
		assertTrue(product.compareGreatherThanZero(0));
	}
}
