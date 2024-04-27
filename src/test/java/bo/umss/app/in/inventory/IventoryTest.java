package bo.umss.app.in.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.Product;
import bo.umss.app.in.buy.Buy;
import bo.umss.app.in.codeProduct.CodeProduct;
import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.cup.Cup;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.measurement.Measurement;
import bo.umss.app.in.plate.Plate;
import bo.umss.app.in.referral.Referral;

public class IventoryTest {

	public CodeProduct codeProduct;
	public Product plate;
	public Measurement measurement;
	public Coin coin;
	public Line line;

	@BeforeEach
	public void setUp() {
		measurement = Measurement.at(Measurement.CODE_PZA, Measurement.NAME_PZA);
		line = Line.at(Line.CODE_PLATE, Line.NAME_PLATE);
		coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		codeProduct = CodeProduct.at("plate-1", "description", measurement, line, coin);
		plate = Plate.at(codeProduct, 10, 5, 10);
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
		buy.changePriceBuy(4);

		assertTrue(buy.getProduct().getListChangePriceCost().size() == 1);
	}

	@Test
	public void tryAddProductWithoutTransaction() {
		Inventory inventory = new Inventory();

		assertThrows(RuntimeException.class, () -> inventory.addBuyTransaction(plate), Inventory.ITEM_CAN_NOT_EXIST);
	}

	@Test
	public void canNotLetWithdrawWithoutBuy() {
		Referral referral = Referral.at(plate.getCodeProduct(), 15);

		Inventory inventory = new Inventory();
		assertThrows(RuntimeException.class, () -> inventory.withdrawReferralTransaction(referral),
				Inventory.NOT_REGISTER_CODE_PRODUCT);
	}

	@Test
	public void toDoReferralAmountProduct() {
		Buy buy = Buy.at("purchase porcelain plates", plate);
		plate.addBuy(buy);

		// The amount referal should be greather than amount product Referral
		Referral referral = Referral.at(plate.getCodeProduct(), 15);

		Inventory inventory = new Inventory();
		inventory.addBuyTransaction(plate);

		assertThrows(RuntimeException.class, () -> inventory.withdrawReferralTransaction(referral),
				Inventory.QUANTITY_GREATHER_THAN_AVAILABLE);
	}

	@Test
	public void addTwoDiferentProductInventory() {
		Buy buy = Buy.at("purchase porcelain plates", plate);
		plate.addBuy(buy);

		Line line2 = Line.at(Line.CODE_CUP, Line.NAME_CUP);
		CodeProduct codeProduct2 = CodeProduct.at("cup-1", "description cup", measurement, line2, coin);

		Product cup = Cup.at(codeProduct2, 10, 8, 16);

		Buy buy2 = Buy.at("purchase porcelain cupes", cup);
		cup.addBuy(buy2);

		Inventory inventory = new Inventory();
		inventory.addBuyTransaction(plate);
		inventory.addBuyTransaction(cup);

		assertTrue(inventory.compareListProductGreatherZero(2));
	}

	@Test
	public void tryAddReferralWithAddBuy() {
		Buy buy = Buy.at("purchase porcelain plates", plate);
		plate.addBuy(buy);

		Line line2 = Line.at(Line.CODE_CUP, Line.NAME_CUP);
		CodeProduct codeProduct2 = CodeProduct.at("cup-1", "description cup", measurement, line2, coin);

		Product cup = Cup.at(codeProduct2, 10, 8, 16);

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
