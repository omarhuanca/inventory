package bo.umss.app.in.codeProduct;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.measurement.Measurement;
import bo.umss.app.in.provider.Provider;

public class ProvidedProviderTest {

	public Measurement measurement;
	public Line line;
	public Coin coin;
	public Provider provider;

	@BeforeEach
	public void setUp() {
		measurement = Measurement.at("pza", "piece");
		line = Line.at("plate1", "plate");
		coin = Coin.at("coin1", "USD");
		provider = Provider.at("Juan Perez", "88888888");
	}

	@Test
	public void canNotCreateBlankCode() {
		assertThrows(RuntimeException.class,
				() -> ProvidedProvider.at("", CodeProduct.DEFINE_DESCRIPTION, measurement, line, coin, provider),
				CodeProduct.INVALID_CODE);
	}

	@Test
	public void canNotCreateBlankDescription() {
		assertThrows(RuntimeException.class,
				() -> ProvidedProvider.at(CodeProduct.DEFINE_CODE_PRODUCT, "", measurement, line, coin, provider),
				CodeProduct.INVALID_DESCRIPTION);
	}

	@Test
	public void canNotCreateNullMeasurement() {
		assertThrows(RuntimeException.class, () -> ProvidedProvider.at(CodeProduct.DEFINE_CODE_PRODUCT,
				CodeProduct.DEFINE_DESCRIPTION, null, line, coin, provider), CodeProduct.INVALID_DESCRIPTION);
	}

	@Test
	public void canNotCreateNullLine() {
		assertThrows(
				RuntimeException.class, () -> ProvidedProvider.at(CodeProduct.DEFINE_CODE_PRODUCT,
						CodeProduct.DEFINE_DESCRIPTION, measurement, null, coin, provider),
				CodeProduct.INVALID_DESCRIPTION);
	}

	@Test
	public void canNotCreateNullCoin() {
		assertThrows(
				RuntimeException.class, () -> ProvidedProvider.at(CodeProduct.DEFINE_CODE_PRODUCT,
						CodeProduct.DEFINE_DESCRIPTION, measurement, line, null, provider),
				CodeProduct.INVALID_DESCRIPTION);
	}

	@Test
	public void canNotCreateNullProvider() {
		assertThrows(RuntimeException.class, () -> ProvidedProvider.at(CodeProduct.DEFINE_CODE_PRODUCT,
				CodeProduct.DEFINE_DESCRIPTION, measurement, line, coin, null));
	}
}
