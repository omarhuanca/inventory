package bo.umss.app.in.cup;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.Product;
import bo.umss.app.in.codeProduct.NotProvidedProvider;
import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.measurement.Measurement;

public class CupTest {

	public NotProvidedProvider notProvidedProvider;

	@BeforeEach
	public void setUp() {
		Measurement measurement = Measurement.at(Measurement.CODE_PZA, Measurement.NAME_PZA);
		Line line = Line.at(Line.CODE_CUP, Line.NAME_CUP);
		Coin coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		notProvidedProvider = NotProvidedProvider.at("cup-1", "taza porcelain isaylin", measurement, line, coin);
	}

	@Test
	public void noLetInvalidAmount() {
		assertThrows(RuntimeException.class, () -> Cup.at(notProvidedProvider, -5, 5, 6), Product.INVALID_AMOUNT);
	}
}
