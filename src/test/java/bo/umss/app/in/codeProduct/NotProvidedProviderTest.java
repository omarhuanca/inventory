package bo.umss.app.in.codeProduct;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.line.Line;

public class NotProvidedProviderTest {

	public Line line;

	@BeforeEach
	public void setUp() {
		line = Line.at("plate1", "plate");
	}

	@Test
	public void canNotCreateBlankCode() {
		assertThrows(RuntimeException.class, () -> NotProvidedProvider.at("", CodeProduct.DESCRIPTION_BOWL8, line),
				CodeProduct.CODE_CAN_NOT_BE_BLANK);
	}

	@Test
	public void canNotCreateBlankDescription() {
		assertThrows(RuntimeException.class, () -> NotProvidedProvider.at(CodeProduct.CODE_PRODUCT_BOWL8, "", line),
				CodeProduct.DESCRIPTION_CAN_NOT_BE_BLANK);
	}

	@Test
	public void canNotCreateNullLine() {
		assertThrows(RuntimeException.class,
				() -> NotProvidedProvider.at(CodeProduct.CODE_PRODUCT_BOWL8, CodeProduct.DESCRIPTION_BOWL8, null),
				CodeProduct.DESCRIPTION_CAN_NOT_BE_BLANK);
	}
}
