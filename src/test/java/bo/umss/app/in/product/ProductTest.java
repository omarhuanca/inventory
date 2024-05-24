package bo.umss.app.in.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.buy.Buy;
import bo.umss.app.in.codeProduct.NotProvidedProvider;
import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.measurement.Measurement;
import bo.umss.app.in.price.Price;
import bo.umss.app.in.referral.Referral;
import bo.umss.app.in.stock.Stock;

public class ProductTest {

	public NotProvidedProvider notProvidedProvider;
	public Coin coin;
	public Measurement measurement;
	public Line line;
	public Price priceCost;
	public Price priceSale;
	public Stock stock;
	public Product plate;
	public LocalDate date;

	@BeforeEach
	public void setUp() {
		line = Line.at(Line.CODE_PLATE, Line.NAME_PLATE);
		notProvidedProvider = NotProvidedProvider.at("plate-1", "bowl8 porcelana isaylin", line);
		coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		priceCost = Price.at(5, coin);
		priceSale = Price.at(10, coin);
		measurement = Measurement.at(Measurement.CODE_PZA, Measurement.NAME_PZA);
		stock = Stock.at(10, measurement);

		plate = Product.at(notProvidedProvider, stock, priceCost, priceSale);

		date = LocalDate.of(2024, 05, 30);
	}

	@Test
	public void notLetInvalidStock() {
		Price priceCost2 = Price.at(5, coin);
		Price priceSale2 = Price.at(6, coin);
		assertThrows(RuntimeException.class, () -> Product.at(notProvidedProvider, null, priceCost2, priceSale2),
				Product.STOCK_CAN_NOT_BE_NULL);
	}

	@Test
	public void notLetPriceCostBeNull() {
		Price priceSale2 = Price.at(6, coin);
		Stock stock2 = Stock.at(1, measurement);
		assertThrows(RuntimeException.class, () -> Product.at(notProvidedProvider, stock2, null, priceSale2),
				Product.PRICE_COST_CAN_NOT_BE_NULL);
	}

	@Test
	public void notLetPriceSaleBeNull() {
		Price priceCost2 = Price.at(5, coin);
		Stock stock2 = Stock.at(1, measurement);
		assertThrows(RuntimeException.class, () -> Product.at(notProvidedProvider, stock2, priceCost2, null),
				Product.PRICE_SALE_CAN_NOT_BE_NULL);
	}

	@Test
	public void notLetNoExistCodeProduct() {
		Stock stock2 = Stock.at(1, measurement);

		Product plate = Product.at(notProvidedProvider, stock2, priceCost, priceSale);
		assertTrue(plate.alreadyCodeProduct());
	}

	@Test
	public void notLetAnyItemOfListTransaction() {
		Stock stock2 = Stock.at(1, measurement);

		Product plate = Product.at(notProvidedProvider, stock2, priceCost, priceSale);
		assertFalse(plate.listTransactionCompareGreatherThanZero(0));
	}

	@Test
	public void changeSizeListChangePriceAfterIncreasePriceCost() {
		Product plate = Product.at(notProvidedProvider, stock, priceCost, priceSale);

		Price priceCostOther = Price.at(6, coin);
		plate.changePriceBuy(priceCostOther, plate.getStock());

		assertEquals(1, plate.getListChangePriceCost().size());
	}

	@Test
	public void changePriceCostAfterIncreaseValueTwoTimes() {
		Product plate = Product.at(notProvidedProvider, stock, priceCost, priceSale);

		Price priceCostOther1 = Price.at(8, coin);
		plate.changePriceBuy(priceCostOther1, plate.getStock());
		Price priceCostOther2 = Price.at(4, coin);
		plate.changePriceBuy(priceCostOther2, plate.getStock());

		assertEquals(1, plate.getListChangePriceCost().size());
	}

	@Test
	public void addDiffTypeCoinAfterChangeValuePriceCost() {
		Product plate = Product.at(notProvidedProvider, stock, priceCost, priceSale);

		Coin coin2 = Coin.at(Coin.CODE_BS, Coin.NAME_BS);
		Price priceCostOther1 = Price.at(8, coin2);
		plate.changePriceBuy(priceCostOther1, plate.getStock());

		assertEquals(0, plate.getListChangePriceCost().size());
	}

	@Test
	public void verifySizeListAfterAddTransactionBuy() {
		Product plate = Product.at(notProvidedProvider, stock, priceCost, priceSale);

		Buy buy = Buy.at(notProvidedProvider, 5, date, "purchase porcelain plates");
		plate.addBuy(buy);

		assertTrue(plate.listTransactionCompareGreatherThanZero(0));
	}

	@Test
	public void verifyAddBuySuccess() {
		Buy buy = Buy.at(notProvidedProvider, 5, date, "purchase porcelain plates");
		plate.addBuy(buy);

		assertTrue(plate.getStock().compareValue(15));
	}

	@Test
	public void addTwoSameTimeProduct() {
		Buy buy = Buy.at(plate.getCodeProduct(), 3, date, "purchase porcelain plates");
		plate.addBuy(buy);

		Buy secondBuy = Buy.at(plate.getCodeProduct(), 5, date, "purchase porcelain plates");

		assertTrue(plate.getStock().compareValue(13));
		assertThrows(RuntimeException.class, () -> plate.addBuy(secondBuy), Product.CODE_PRODUCT_DUPLICATE);
	}

	@Test
	public void verifyStockAfterAddReferral() {
		Referral referral = Referral.at(plate.getCodeProduct(), 5, date);
		plate.addReferral(referral);

		assertTrue(plate.getStock().compareValue(5));
		assertEquals(1, plate.getListTransaction().size());
	}

	@Test
	public void toDoReferralAmountProduct() {
		// The amount referral should be grether than stock value
		Referral referral = Referral.at(plate.getCodeProduct(), 15, date);

		assertThrows(RuntimeException.class, () -> plate.addReferral(referral), Stock.AMOUNT_GREATHER_THAN_AVAILABLE);
		assertTrue(plate.getStock().compareValue(10));
		assertEquals(0, plate.getListTransaction().size());

	}	
}
