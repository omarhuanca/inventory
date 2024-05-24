package bo.umss.app.in.referral;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.Transaction;
import bo.umss.app.in.codeProduct.CodeProduct;
import bo.umss.app.in.codeProduct.NotProvidedProvider;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.referral.Referral;

public class ReferralTest {

	public CodeProduct notProvidedProvider;
	public LocalDate date;

	@BeforeEach
	public void setUp() {
		Line line = Line.at(Line.CODE_PLATE, Line.NAME_PLATE);
		notProvidedProvider = NotProvidedProvider.at("PLA-1", "bowl8 a round plate of porcelain", line);
		date = LocalDate.of(2024, 03, 15);
	}

	@Test
	public void canNotBeNullCodeProduct() {
		assertThrows(RuntimeException.class, () -> Referral.at(null, 1, date),
				Transaction.CODE_PRODUCT_CAN_NOT_BE_NULL);
	}

	@Test
	public void canNotBeLessThanZeroAmount() {
		assertThrows(RuntimeException.class, () -> Referral.at(notProvidedProvider, -1, date),
				Transaction.AMOUNT_CAN_NOT_BE_LESS_THAN_ZERO);
	}

	@Test
	public void canNotBeNullDate() {
		assertThrows(RuntimeException.class, () -> Referral.at(notProvidedProvider, 5, null),
				Transaction.DATE_CAN_NOT_BE_NULL);
	}
}
