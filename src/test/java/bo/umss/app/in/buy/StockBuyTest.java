package bo.umss.app.in.buy;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.StockTransaction;
import bo.umss.app.in.TestObjectBucket;
import bo.umss.app.in.codeProduct.CodeProduct;
import bo.umss.app.in.codeProduct.NotProvidedProvider;
import bo.umss.app.in.line.Line;

public class StockBuyTest {

	private CodeProduct notProvidedProvider;
	private LocalDate date;
	private final TestObjectBucket testObjectBucket = new TestObjectBucket();

	@BeforeEach
	public void setUp() {
		Line line = Line.at(TestObjectBucket.PLATE_NAME);

		notProvidedProvider = NotProvidedProvider.at(TestObjectBucket.PLATE_CODE, TestObjectBucket.BOWL7_DESCRIPTION,
				line);
		date = testObjectBucket.createDate();

	}

	@Test
	public void canNotByNullCodeProduct() {
		assertThrows(RuntimeException.class, () -> StockBuy.at(null, 1, date, TestObjectBucket.BOWL7_DESCRIPTION),
				StockBuy.CODE_PRODUCT_CAN_NOT_BE_NULL);
	}

	@Test
	public void cantNotLetAmountLessThanZero() {
		assertThrows(RuntimeException.class,
				() -> StockBuy.at(notProvidedProvider, -1, date, TestObjectBucket.BOWL7_DESCRIPTION),
				StockTransaction.AMOUNT_CAN_NOT_BE_LESS_THAN_ZERO);
	}

	@Test
	public void canNotBeNullDate() {
		assertThrows(RuntimeException.class,
				() -> StockBuy.at(notProvidedProvider, 5, null, TestObjectBucket.BOWL7_DESCRIPTION),
				StockTransaction.DATE_CAN_NOT_BE_NULL);
	}

	@Test
	public void canNotBeEmptyDescription() {
		assertThrows(RuntimeException.class, () -> StockBuy.at(notProvidedProvider, 1, date, ""),
				StockBuy.DESCRIPTION_CAN_NOT_BE_BLANK);
	}
}
