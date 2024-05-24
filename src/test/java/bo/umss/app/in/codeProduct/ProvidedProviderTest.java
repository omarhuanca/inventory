package bo.umss.app.in.codeProduct;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.line.Line;
import bo.umss.app.in.provider.Provider;

public class ProvidedProviderTest {

	public Line line;
	public Provider provider;

	@BeforeEach
	public void setUp() {
		line = Line.at("plate1", "plate");
		provider = Provider.at("Juan Perez", "88888888");
	}

	@Test
	public void canNotCreateBlankCode() {
		assertThrows(RuntimeException.class,
				() -> ProvidedProvider.at("", CodeProduct.DESCRIPTION_BOWL8, line, provider),
				CodeProduct.CODE_CAN_NOT_BE_BLANK);
	}

	@Test
	public void canNotCreateBlankDescription() {
		assertThrows(RuntimeException.class,
				() -> ProvidedProvider.at(CodeProduct.CODE_PRODUCT_BOWL8, "", line, provider),
				CodeProduct.DESCRIPTION_CAN_NOT_BE_BLANK);
	}

	@Test
	public void canNotCreateNullLine() {
		assertThrows(RuntimeException.class, () -> ProvidedProvider.at(CodeProduct.CODE_PRODUCT_BOWL8,
				CodeProduct.DESCRIPTION_BOWL8, null, provider), CodeProduct.DESCRIPTION_CAN_NOT_BE_BLANK);
	}

	@Test
	public void canNotCreateNullProvider() {
		assertThrows(RuntimeException.class,
				() -> ProvidedProvider.at(CodeProduct.CODE_PRODUCT_BOWL8, CodeProduct.DESCRIPTION_BOWL8, line, null));
	}
}
