package bo.umss.app.in.changePrice;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.TestObjectBucket;
import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.measurement.Measurement;
import bo.umss.app.in.price.Price;
import bo.umss.app.in.stock.Stock;

public class ChangePriceTest {

	private LocalDate currentDate;
	private Coin coin;
	private Stock stock;

	@BeforeEach
	public void setUp() {
		currentDate = LocalDate.now();
		coin = Coin.at(TestObjectBucket.CODE_USA, TestObjectBucket.NAME_USA);
		Measurement measurement = Measurement.at(TestObjectBucket.CODE_PZA, TestObjectBucket.NAME_PZA);
		stock = Stock.at(2, measurement);

	}

	@Test
	public void canNotLetNewPriceBeNull() {
		Price oldPrice = Price.at(4.0, coin);

		assertThrows(RuntimeException.class, () -> ChangePrice.at(null, oldPrice, stock, currentDate),
				ChangePrice.NEW_PRICE_CAN_NOT_BE_NULL);
	}

	@Test
	public void canNotLetOldPriceBeNull() {
		Price newPrice = Price.at(5.0, coin);

		assertThrows(RuntimeException.class, () -> ChangePrice.at(newPrice, null, stock, currentDate),
				ChangePrice.OLD_PRICE_CAN_NOT_BE_NULL);
	}

	@Test
	public void canNotBeNullStock() {
		Price newPrice = Price.at(5.0, coin);
		Price oldPrice = Price.at(10.0, coin);

		assertThrows(RuntimeException.class, () -> ChangePrice.at(newPrice, oldPrice, null, currentDate),
				ChangePrice.STOCK_CAN_NOT_BE_NULL);
	}

	@Test
	public void canNotBeNullCurrentDate() {
		Price newPrice = Price.at(5.0, coin);
		Price oldPrice = Price.at(10.0, coin);

		assertThrows(RuntimeException.class, () -> ChangePrice.at(newPrice, oldPrice, stock, null),
				ChangePrice.CURRENT_DATE_CAN_NOT_BE_NULL);
	}

	@Test
	public void newPriceCanNotBeLessThanOldPrice() {
		Price newPrice = Price.at(5.0, coin);
		Price oldPrice = Price.at(10.0, coin);

		assertThrows(RuntimeException.class, () -> ChangePrice.at(newPrice, oldPrice, stock, currentDate),
				ChangePrice.NEW_PRICE_CAN_NOT_LESS_THAN_OLD_PRICE);
	}

	@Test
	public void newPriceDoesNotHasDiffMeasurementToOldPrice() {
		Coin coin2 = Coin.at(TestObjectBucket.CODE_BS, TestObjectBucket.NAME_BS);
		Price newPrice = Price.at(5.0, coin);
		Price oldPrice = Price.at(10.0, coin2);

		assertThrows(RuntimeException.class, () -> ChangePrice.at(newPrice, oldPrice, stock, currentDate),
				ChangePrice.NEW_PRICE_DOES_NOT_HAS_DIFF_MEASUREMENT_TO_OLD_PRICE);

	}
}
