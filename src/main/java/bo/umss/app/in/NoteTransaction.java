package bo.umss.app.in;

import java.time.LocalDate;
import java.util.List;

import bo.umss.app.in.product.Product;

public abstract class NoteTransaction {

	public static final String DATE_CAN_NOT_BE_NULL = "Date can not be null";
	
	protected LocalDate date;
	protected List<Product> listProductOutput;

	public abstract LocalDate getDate();

	public abstract List<Product> getListProductOutPut();
}
	