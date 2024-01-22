package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IventoryTest {

	@Test
	public void codeProductAlreadyExistPrevious() {
		Measurement measurement = Measurement.at("pza", "piece");
		Line line = Line.at("plate1", "plate");
		Coin coin = Coin.at("coin1", "USD");
		CodeProduct codeProduct = CodeProduct.at("plate-1", "description", measurement, line, coin);

		Product product = Product.at(codeProduct, 1, 5, 6);

		assertFalse(product.canAddAnyTransaction());
	}

	@Test
	public void verifyAddBuySuccess() {
		Measurement measurement = Measurement.at("pza", "piece");
		Line line = Line.at("plate1", "plate");
		Coin coin = Coin.at("coin1", "USD");
		CodeProduct codeProduct = CodeProduct.at("plate-1", "description", measurement, line, coin);

		Product product = Product.at(codeProduct, 1, 5, 6);

		Buy buy = Buy.at("purchase porcelain plates", product);
		product.addBuy(buy);

		assertTrue(product.wasAddAnyBuy(codeProduct));
	}

	@Test
	public void addTwoSameTimeProduct() {
		Measurement measurement = Measurement.at("pza", "piece");
		Line line = Line.at("plate1", "plate");
		Coin coin = Coin.at("coin1", "USD");
		CodeProduct codeProduct = CodeProduct.at("plate-1", "description", measurement, line, coin);

		Product product = Product.at(codeProduct, 1, 5, 6);

		Buy buy = Buy.at("purchase porcelain plates", product);
		product.addBuy(buy);

		Buy secondBuy = Buy.at("purchase porcelain plates", product);

		assertThrows(RuntimeException.class, () -> product.addBuy(secondBuy), Product.ALREADY_CODE_PRODUCT);
	}

	@Test
	public void changePriceCostAfterIncreaseValue() {
		Measurement measurement = Measurement.at("pza", "piece");
		Line line = Line.at("plate1", "plate");
		Coin coin = Coin.at("coin1", "USD");
		CodeProduct codeProduct = CodeProduct.at("plate-1", "description", measurement, line, coin);

		Product product = Product.at(codeProduct, 1, 5, 6);

		Buy buy = Buy.at("purchase porcelain plates", product);
		buy.changePriceBuy(8);

		assertTrue(buy.getProduct().getListChangePriceCost().size() == 1);
	}

	
	@Test
	public void changePriceCostAfterIncreaseValueTwoTimes() {
		Measurement measurement = Measurement.at("pza", "piece");
		Line line = Line.at("plate1", "plate");
		Coin coin = Coin.at("coin1", "USD");
		CodeProduct codeProduct = CodeProduct.at("plate-1", "description", measurement, line, coin);

		Product product = Product.at(codeProduct, 1, 5, 6);

		Buy buy = Buy.at("purchase porcelain plates", product);
		buy.changePriceBuy(8);
		buy.changePriceBuy(6);

		assertTrue(buy.getProduct().getListChangePriceCost().size() == 1);
	}
	
	@Test
	public void toDoReferralAmountProduct() {
		Measurement measurement = Measurement.at("pza", "piece");
		Line line = Line.at("plate1", "plate");
		Coin coin = Coin.at("coin1", "USD");
		CodeProduct codeProduct = CodeProduct.at("plate-1", "description", measurement, line, coin);

		Product product = Product.at(codeProduct, 1, 5, 10);

		Buy buy = Buy.at("purchase porcelain plates", product);
		product.addBuy(buy);

		Referral referral = Referral.at(product.getCodeProduct(), 2);

		Inventory inventory = new Inventory();
		inventory.addBuyTransaction(product);

		assertThrows(RuntimeException.class, () -> inventory.withdrawProductReferral(referral),
				Inventory.QUANTITY_GREATHER_THAN_AVAILABLE);
	}

	@Test
	public void addBuySameCodeProductTwoTimes() {
		Measurement measurement = Measurement.at("pza", "piece");
		Line line = Line.at("plate1", "plate");
		Coin coin = Coin.at("coin1", "USD");
		CodeProduct codeProduct = CodeProduct.at("plate-1", "description", measurement, line, coin);

		Product product = Product.at(codeProduct, 1, 5, 10);

		Inventory inventory = new Inventory();
		inventory.addBuyTransaction(product);

		assertThrows(RuntimeException.class, () -> inventory.addBuyTransaction(product),
				Inventory.INVALID_ADD_PRODUCT_TWO_TIMES);
	}

	@Test
	public void canNotLetTodoReferralWithoutCodeProductPrevious() {
		Measurement measurement = Measurement.at("pza", "piece");
		Line line = Line.at("plate1", "plate");
		Coin coin = Coin.at("coin1", "USD");
		CodeProduct codeProduct = CodeProduct.at("plate-1", "description", measurement, line, coin);

		Product product = Product.at(codeProduct, 1, 5, 10);
		
		Inventory inventory = new Inventory();
	    inventory.addBuyTransaction(product);

		assertThrows(RuntimeException.class, () -> inventory.canAddReferralTransaction(codeProduct),
				Inventory.NOT_REGISTER_CODE_PRODUCT);
	}
}
