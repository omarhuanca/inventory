package bo.umss.app.in.buy;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.Transaction;
import bo.umss.app.in.codeProduct.CodeProduct;
import bo.umss.app.in.codeProduct.NotProvidedProvider;
import bo.umss.app.in.line.Line;

public class BuyTest {

	public final String BOWL7_DESCRIPTION = "bolw7 a round plate of porcelain";
	
	public CodeProduct notProvidedProvider;
	public LocalDate date;

	@BeforeEach
	public void setUp() {
		Line line = Line.at(Line.CODE_PLATE, Line.NAME_PLATE);

		notProvidedProvider = NotProvidedProvider.at("PLA-1", BOWL7_DESCRIPTION,
				line);
		date = LocalDate.of(2024, 05, 01);
		
	}

	@Test
	public void canNotByNullCodeProduct() {
		assertThrows(RuntimeException.class, () -> Buy.at(null, 1, date, BOWL7_DESCRIPTION),
				Buy.CODE_PRODUCT_CAN_NOT_BE_NULL);
	}

	@Test
	public void cantNotLetAmountLessThanZero() {
		
		assertThrows(RuntimeException.class, () -> Buy.at(notProvidedProvider, -1, date, BOWL7_DESCRIPTION),
				Transaction.AMOUNT_CAN_NOT_BE_LESS_THAN_ZERO);
	}

	@Test
	public void canNotBeNullDate() {
		assertThrows(RuntimeException.class, () -> Buy.at(notProvidedProvider, 5, null, BOWL7_DESCRIPTION),
				Transaction.DATE_CAN_NOT_BE_NULL);
	}

	@Test
	public void canNotBeEmptyDescription() {
		assertThrows(RuntimeException.class, () -> Buy.at(notProvidedProvider, 1, date, ""),
				Buy.DESCRIPTION_CAN_NOT_BE_BLANK);
	}
}
