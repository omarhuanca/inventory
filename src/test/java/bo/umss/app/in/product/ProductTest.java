package bo.umss.app.in.product;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.codeProduct.NotProvidedProvider;
import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.measurement.Measurement;

public class ProductTest {

	public NotProvidedProvider notProvidedProvider;

	@BeforeEach
	public void setUp() {
		Measurement measurement = Measurement.at(Measurement.CODE_PZA, Measurement.NAME_PZA);
		Line line = Line.at(Line.CODE_PLATE, Line.NAME_PLATE);
		Coin coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		notProvidedProvider = NotProvidedProvider.at("plate-1", "bowl8 porcelana isaylin", measurement, line, coin);
	}

	@Test
	public void noLetInvalidAmount() {
		assertThrows(RuntimeException.class, () -> Product.at(notProvidedProvider, -5, 5, 6), Product.INVALID_AMOUNT);
	}

	@Test
	public void noLetInvalidPriceCost() {
		assertThrows(RuntimeException.class, () -> Product.at(notProvidedProvider, 1, 0, 6),
				Product.INVALID_PRICE_COST);
	}

	@Test
	public void noLetInvalidPriceSale() {
		assertThrows(RuntimeException.class, () -> Product.at(notProvidedProvider, 1, 5, 0),
				Product.INVALID_PRICE_SALE);
	}

	@Test
	public void noLetNoExistCodeProduct() {
		Product plate = Product.at(notProvidedProvider, 1, 5, 10);
		assertTrue(plate.alreadyCodeProduct());
	}

	@Test
	public void noLetAnyItemOfListTransaction() {
		Product plate = Product.at(notProvidedProvider, 1, 5, 10);
		assertFalse(plate.listTransactionCompareGreatherThanZero(0));
	}
}
