package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ProductTest {

	private final String POSSIBLE_PURCHASE_MERCHANDISE = "Posible compra de utencilios";

	@Test
	public void test1() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);

		Product product = new Product(POSSIBLE_PURCHASE_MERCHANDISE, codeProduct);
		assertFalse(product.verifyConceptIsNotEmpty());
	}

	@Test
	public void test2() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);

		Product product = new Product(POSSIBLE_PURCHASE_MERCHANDISE, codeProduct);
		assertTrue(product.alreadyCodeProduct());
	}

	@Test
	public void test3() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);

		Product product = new Product(POSSIBLE_PURCHASE_MERCHANDISE, codeProduct);
		assertFalse(product.compareGreatherThanZero(0));
	}
}
