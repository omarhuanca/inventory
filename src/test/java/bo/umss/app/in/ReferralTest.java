package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ReferralTest {

	private final String POSSIBLE_PURCHASE_MERCHANDISE = "Possible purchase of kitchen utensils";

	@Test
	public void verifyCodeProductCanNotBeNull() {
		assertThrows(RuntimeException.class, () -> Referral.at(null, "description1", 1), Referral.INVALID_CODE_PRODUCT);
	}

	@Test
	public void test02() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);

		assertThrows(RuntimeException.class, () -> Referral.at(codeProduct, "", 1), Referral.INVALID_DESCRIPTION);
	}

	@Test
	public void test03() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);

		assertThrows(RuntimeException.class, () -> Referral.at(codeProduct, "description1", -1), Referral.INVALID_AMOUNT);
		
	}

	@Test
	public void test04() {
		Measurement measurement = Measurement.at("pza", "piece");
		Line line = Line.at("plate1", "plate");
		Coin coin = Coin.at("coin1", "USD");
		CodeProduct codeProduct = CodeProduct.at("plate-1", "description", measurement, line, coin);

		Product product = new Product(POSSIBLE_PURCHASE_MERCHANDISE, codeProduct);

		Buy buy = Buy.at(codeProduct, "bdescription1", 2, 5, 6);
		product.addBuy(buy);
		
		Referral referral = Referral.at(codeProduct, "description1", -1);
		
		assertThrows(RuntimeException.class, () -> referral.withdraw(3), Referral.QUANTITY_GREATHER_THAN_AVAILABLE);
	}
}
