package bo.umss.app.in.sale;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.NoteTransaction;

public class NoteSaleTest {

	public final String JUAN_PEREZ_NAME = "Juan Perez";
	public final String JUAN_PEREZ_NIT = "123456";

	private LocalDate date;
	private String invoiceNumber;

	@BeforeEach
	public void setUp() {
		date = LocalDate.of(2024, 05, 15);
		invoiceNumber = "654987";
	}

	@Test
	public void canNotBeNullDate() {
		assertThrows(RuntimeException.class, () -> NoteSale.at(null, JUAN_PEREZ_NAME, JUAN_PEREZ_NIT, invoiceNumber),
				NoteTransaction.DATE_CAN_NOT_BE_NULL);
	}

	@Test
	public void canNotBeEmptyName() {
		assertThrows(RuntimeException.class, () -> NoteSale.at(date, "", JUAN_PEREZ_NIT, invoiceNumber),
				NoteSale.NAME_CAN_NOT_BE_EMPTY);
	}

	@Test
	public void canNotBeEmptyNit() {
		assertThrows(RuntimeException.class, () -> NoteSale.at(date, JUAN_PEREZ_NAME, "", invoiceNumber),
				NoteSale.NIT_CAN_NOT_BE_EMPTY);
	}

	@Test
	public void canNotBeEmptyInvoiceNumber() {
		assertThrows(RuntimeException.class, () -> NoteSale.at(date, JUAN_PEREZ_NAME, JUAN_PEREZ_NIT, ""),
				NoteSale.INVOICE_NUMBER_CAN_NOT_BE_EMPTY);
	}
}
