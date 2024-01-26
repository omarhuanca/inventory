package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IventoryTest {

	public CodeProduct codeProduct;
	public Product plate;

	@BeforeEach
	public void setUp() {
		Measurement measurement = Measurement.at(Measurement.CODE_PZA, Measurement.NAME_PZA);
		Line line = Line.at(Line.CODE_PLATE, Line.NAME_PLATE);
		Coin coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		codeProduct = CodeProduct.at("plate-1", "description", measurement, line, coin);
		plate = Plate.at(codeProduct, 2, 5, 10);
	}

	@Test
	public void codeProductAlreadyExistPrevious() {
		assertFalse(plate.canAddAnyTransaction());
	}

	@Test
	public void verifyAddBuySuccess() {
		Buy buy = Buy.at("purchase porcelain plates", plate);
		plate.addBuy(buy);

		assertTrue(plate.wasAddAnyBuy(codeProduct));
	}

	@Test
	public void addTwoSameTimeProduct() {
		Buy buy = Buy.at("purchase porcelain plates", plate);
		plate.addBuy(buy);

		Buy secondBuy = Buy.at("purchase porcelain plates", plate);

		assertThrows(RuntimeException.class, () -> plate.addBuy(secondBuy), Product.ALREADY_CODE_PRODUCT);
	}

	@Test
	public void changePriceCostAfterIncreaseValue() {
		Buy buy = Buy.at("purchase porcelain plates", plate);
		buy.changePriceBuy(8);

		assertTrue(buy.getProduct().getListChangePriceCost().size() == 1);
	}

	@Test
	public void changePriceCostAfterIncreaseValueTwoTimes() {
		Buy buy = Buy.at("purchase porcelain plates", plate);
		buy.changePriceBuy(8);
		buy.changePriceBuy(6);

		assertTrue(buy.getProduct().getListChangePriceCost().size() == 1);
	}

	@Test
	public void addBuySameCodeProductTwoTimes() {
		Inventory inventory = new Inventory();
		inventory.addBuyTransaction(plate);

		assertThrows(RuntimeException.class, () -> inventory.addBuyTransaction(plate),
				Inventory.INVALID_ADD_PRODUCT_TWO_TIMES);
	}

	@Test
	public void canNotLetTodoReferralWithoutCodeProductPrevious() {
		Inventory inventory = new Inventory();
		inventory.addBuyTransaction(plate);

		assertThrows(RuntimeException.class, () -> inventory.canAddReferralTransaction(codeProduct),
				Inventory.NOT_REGISTER_CODE_PRODUCT);
	}

	@Test
	public void toDoReferralAmountProduct() {
		Buy buy = Buy.at("purchase porcelain plates", plate);
		plate.addBuy(buy);

		Referral referral = Referral.at(plate.getCodeProduct(), 3);

		Inventory inventory = new Inventory();
		inventory.addBuyTransaction(plate);

		assertThrows(RuntimeException.class, () -> inventory.withdrawReferralTransaction(referral),
				Inventory.QUANTITY_GREATHER_THAN_AVAILABLE);
	}

	@Test
	public void addTwoDiferentProductInventory() {
		Line line2 = Line.at(Line.CODE_CUP, Line.NAME_CUP);
		Measurement measurement = Measurement.at(Measurement.CODE_PZA, Measurement.NAME_PZA);
		Coin coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		codeProduct = CodeProduct.at("cup-1", "description cup", measurement, line2, coin);

		Product cup = Cup.at(codeProduct, 10, 8, 16);

		Inventory inventory = new Inventory();
		inventory.addBuyTransaction(plate);
		inventory.addBuyTransaction(cup);

		assertTrue(inventory.compareListProductGreatherZero(2));
	}

	@Test
	public void tryAddReferralWithAddBuy() {
		Line line2 = Line.at(Line.CODE_CUP, Line.NAME_CUP);
		Measurement measurement = Measurement.at(Measurement.CODE_PZA, Measurement.NAME_PZA);
		Coin coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		codeProduct = CodeProduct.at("cup-1", "description cup", measurement, line2, coin);

		Product cup = Cup.at(codeProduct, 10, 8, 16);

		Referral referral = Referral.at(cup.getCodeProduct(), 5);

		Inventory inventory = new Inventory();
		inventory.addBuyTransaction(plate);

		assertThrows(RuntimeException.class, () -> inventory.withdrawReferralTransaction(referral),
				Inventory.INVALID_CODE_PRODUCT_REFERRAL);
	}

	@Test
	public void verifyAmountTransactionWasAdded() {
		Buy buy = Buy.at("purchase porcelain plates", plate);
		plate.addBuy(buy);

		Referral referral = Referral.at(plate.getCodeProduct(), 1);
		Referral referralSecond = Referral.at(plate.getCodeProduct(), 1);

		Inventory inventory = new Inventory();
		inventory.addBuyTransaction(plate);
		inventory.withdrawReferralTransaction(referral);
		inventory.withdrawReferralTransaction(referralSecond);

		assertEquals(inventory.compareEqualListTransaction(plate), 3);
	}
}
