package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlateTest {

	public CodeProduct codeProduct;

	@BeforeEach
	public void setUp() {
		Measurement measurement = Measurement.at(Measurement.CODE_PZA, Measurement.NAME_PZA);
		Line line = Line.at(Line.CODE_PLATE, Line.NAME_PLATE);
		Coin coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		codeProduct = CodeProduct.at("plate-1", "bowl8 porcelana isaylin", measurement, line, coin);
	}

	@Test
	public void noLetInvalidAmount() {
		assertThrows(RuntimeException.class, () -> Plate.at(codeProduct, -5, 5, 6), Product.INVALID_AMOUNT);
	}

	@Test
	public void noLetInvalidPriceCost() {
		assertThrows(RuntimeException.class, () -> Plate.at(codeProduct, 1, 0, 6), Product.INVALID_PRICE_COST);
	}

	@Test
	public void noLetInvalidPriceSale() {
		assertThrows(RuntimeException.class, () -> Plate.at(codeProduct, 1, 5, 0), Product.INVALID_PRICE_SALE);
	}

	@Test
	public void noLetNoExistCodeProduct() {
		Product plate = Plate.at(codeProduct, 1, 5, 10);
		assertTrue(plate.alreadyCodeProduct());
	}

	@Test
	public void noLetAnyItemOfListTransaction() {
		Product plate = Plate.at(codeProduct, 1, 5, 10);
		assertTrue(plate.listTransactionCompareGreatherThanZero(0));
	}
}
