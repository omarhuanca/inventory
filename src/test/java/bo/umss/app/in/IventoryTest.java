package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IventoryTest {

	private final String POSSIBLE_PURCHASE_MERCHANDISE = "Possible purchase of kitchen utensils";

	@Test
	public void test10() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);

		Product product = new Product(POSSIBLE_PURCHASE_MERCHANDISE, codeProduct);

		assertFalse(product.verifyConceptIsNotEmpty());
	}

	@Test
	public void test11() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);

		Product product = new Product(POSSIBLE_PURCHASE_MERCHANDISE, codeProduct);

		assertTrue(product.canAddAnyTransaction());
	}

	@Test
	public void addBuyForInventory() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("codigo2", "description2", measurement, line, coin);

		Product product = new Product(POSSIBLE_PURCHASE_MERCHANDISE, codeProduct);

		Buy buy = Buy.at(codeProduct, "bdescription1", 1, 5, 6);
		product.addBuy(buy);
		assertTrue(product.wasAddAnyBuy(codeProduct));
	}

	@Test
	public void test13() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("codigo2", "description2", measurement, line, coin);

		Buy buy = Buy.at(codeProduct, "bdescription1", 1, 5, 6);
		buy.changePriceBuy(8);
		assertTrue(buy.getListChangePriceCost().size() == 1);
	}

	@Test
	public void test20() {
		Inventory inventory = new Inventory();

		assertFalse(inventory.compareGreatherZero(0));
	}
}
