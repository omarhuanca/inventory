package bo.umss.app.in.inventory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.buy.StockBuy;
import bo.umss.app.in.codeProduct.CodeProduct;
import bo.umss.app.in.codeProduct.NotProvidedProvider;
import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.measurement.Measurement;
import bo.umss.app.in.price.Price;
import bo.umss.app.in.product.Product;
import bo.umss.app.in.stock.Stock;

public class IventoryTest {

	public Product plate;
	public Coin coin;
	public Measurement measurement;
	public LocalDate date;

	@BeforeEach
	public void setUp() {
		Line line = Line.at(Line.CODE_PLATE, Line.NAME_PLATE);
		coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		Price priceCost = Price.at(5, coin);
		Price priceSale = Price.at(10, coin);
		
		CodeProduct notProvidedProvider = NotProvidedProvider.at("plate-1", "description", line);
		measurement = Measurement.at(Measurement.CODE_PZA, Measurement.NAME_PZA);
		Stock stock = Stock.at(10, measurement);
		plate = Product.at(notProvidedProvider, stock, priceCost, priceSale);
	
		date = LocalDate.of(2024, 04, 20);
		
	}

	@Test
	public void tryAddProductWithoutTransaction() {
		Inventory inventory = new Inventory();

		assertThrows(RuntimeException.class, () -> inventory.addProduct(plate), Inventory.ITEM_CAN_NOT_EXIST);
	}

	@Test
	public void addTwoDifferentProduct() {
		StockBuy buy = StockBuy.at(plate.getCodeProduct(), 5, date, "purchase porcelain plates");
		plate.addBuy(buy);

		Line line2 = Line.at(Line.CODE_CUP, Line.NAME_CUP);
		NotProvidedProvider notProvidedProvider2 = NotProvidedProvider.at("cup-1", "description cup", line2);

		Price priceCost = Price.at(8, coin);
		Price priceSale = Price.at(16, coin);
		Stock stock = Stock.at(10, measurement);
		Product cup = Product.at(notProvidedProvider2, stock, priceCost, priceSale);

		StockBuy buy2 = StockBuy.at(notProvidedProvider2, 4, date, "purchase porcelain cupes");
		cup.addBuy(buy2);

		Inventory inventory = new Inventory();
		inventory.addProduct(plate);
		inventory.addProduct(cup);

		assertTrue(inventory.compareListProductGreatherZero(2));
	}
}
