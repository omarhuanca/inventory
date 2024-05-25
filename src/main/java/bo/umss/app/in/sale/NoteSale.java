package bo.umss.app.in.sale;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bo.umss.app.in.NoteTransaction;
import bo.umss.app.in.product.Product;

public class NoteSale extends NoteTransaction {

	public static final String NAME_CAN_NOT_BE_EMPTY = "Name can not be empty";
	public static final String NIT_CAN_NOT_BE_EMPTY = "Nit can not be empty";
	public static final String INVOICE_NUMBER_CAN_NOT_BE_EMPTY = "Invoice number can not be empty";

	private String name;
	private String nit;
	private String invoiceNumber;

	public NoteSale(LocalDate date, String name, String nit, String invoiceNumber) {
		this.date = date;
		this.name = name;
		this.invoiceNumber = invoiceNumber;
		listProductOutput = new ArrayList<>();
	}

	public static NoteSale at(LocalDate date, String name, String nit, String invoiceNumber) {
		if (null == date)
			throw new RuntimeException(NoteTransaction.DATE_CAN_NOT_BE_NULL);
		if (name.isEmpty())
			throw new RuntimeException(NAME_CAN_NOT_BE_EMPTY);
		if (nit.isEmpty())
			throw new RuntimeException(NIT_CAN_NOT_BE_EMPTY);

		if (invoiceNumber.isEmpty())
			throw new RuntimeException(INVOICE_NUMBER_CAN_NOT_BE_EMPTY);

		return new NoteSale(date, name, nit, invoiceNumber);
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

	public String getNit() {
		return nit;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	@Override
	public List<Product> getListProductOutPut() {
		return listProductOutput;
	}
}
