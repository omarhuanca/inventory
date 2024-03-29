package bo.umss.app.in.buy;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bo.umss.app.in.Product;
import bo.umss.app.in.codeProduct.CodeProduct;
import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.measurement.Measurement;
import bo.umss.app.in.plate.Plate;

public class BuyTest {

	@Test
	public void codeProductCanNotBeNull() {
		assertThrows(RuntimeException.class, () -> Buy.at("bowl7 a round plate of porcelain", null),
				Buy.INVALID_PRODUCT);
	}

	@Test
	public void descriptionCanNotBeEmpty() {
		Measurement measurement = Measurement.at(Measurement.CODE_PZA, Measurement.NAME_PZA);
		Line line = Line.at(Line.CODE_PLATE, Line.NAME_PLATE);
		Coin coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		CodeProduct codeProduct = CodeProduct.at("PLA-1", "bolw8 a round plate of porcelain", measurement, line, coin);

		Product plate = Plate.at(codeProduct, 1, 5, 6);

		assertThrows(RuntimeException.class, () -> Buy.at("", plate), Buy.INVALID_DESCRIPTION);
	}
}
