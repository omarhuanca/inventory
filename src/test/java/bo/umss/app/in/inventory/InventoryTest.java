package bo.umss.app.in.inventory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.TestObjectBucket;
import bo.umss.app.in.buy.StockBuy;
import bo.umss.app.in.product.Product;

public class InventoryTest {

	private LocalDate date;
	private final TestObjectBucket testObjectBucket = new TestObjectBucket();

	@BeforeEach
	public void setUp() {
		date = testObjectBucket.createDate();
	}

	@Test
	public void tryAddProductWithoutTransaction() {
		Inventory inventory = new Inventory();
		Product plate = testObjectBucket.createPlate();

		assertThrows(RuntimeException.class, () -> inventory.addProduct(plate), Inventory.ITEM_CAN_NOT_EXIST);
	}

	@Test
	public void addTwoDifferentProduct() {
		Product plate = testObjectBucket.createPlate();
		StockBuy buy = StockBuy.at(plate.getCodeProduct(), 5, date, TestObjectBucket.PLATE_PURCHEASE_DESCRIPTION);
		plate.addBuy(buy);

		Product cup = testObjectBucket.createCup();
		StockBuy buy2 = StockBuy.at(cup.getCodeProduct(), 4, date, TestObjectBucket.CUP_PURCHASE_DESCRIPTION);
		cup.addBuy(buy2);

		Inventory inventory = new Inventory();
		inventory.addProduct(plate);
		inventory.addProduct(cup);

		assertTrue(inventory.compareListProductGreatherZero(2));
	}
}
