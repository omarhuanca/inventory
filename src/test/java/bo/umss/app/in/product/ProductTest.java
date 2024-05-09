package bo.umss.app.in.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
		assertThrows(RuntimeException.class, () -> Product.at(notProvidedProvider, -5, 5, 6), Product.AMOUNT_CAN_NOT_BE_LESS_THAN);
	}

	@Test
	public void noLetInvalidPriceCost() {
		assertThrows(RuntimeException.class, () -> Product.at(notProvidedProvider, 1, 0, 6),
				Product.PRICE_COST_CAN_NOT_BE_LESS_THAN);
	}

	@Test
	public void noLetInvalidPriceSale() {
		assertThrows(RuntimeException.class, () -> Product.at(notProvidedProvider, 1, 5, 0),
				Product.PRICE_SALE_CAN_NOT_BE_LESS_THAN);
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
