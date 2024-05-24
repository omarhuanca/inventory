package bo.umss.app.in.buy;

import java.time.LocalDate;

import bo.umss.app.in.Transaction;
import bo.umss.app.in.codeProduct.CodeProduct;

public class Buy extends Transaction {

	public static final String DESCRIPTION_CAN_NOT_BE_BLANK = "Description can not be empty";

	private String description;

	public Buy(CodeProduct codeProduct, Integer amount, LocalDate localDate, String description) {
		this.codeProduct = codeProduct;
		this.amount = amount;
		this.localDate = localDate;
		this.description = description;
	}

	public static Buy at(CodeProduct codeProduct, Integer amount, LocalDate localDate, String description) {
		if (null == codeProduct)
			throw new RuntimeException(Transaction.CODE_PRODUCT_CAN_NOT_BE_NULL);
		if (0 >= amount)
			throw new RuntimeException(Transaction.AMOUNT_CAN_NOT_BE_LESS_THAN_ZERO);
		if (null == localDate)
			throw new RuntimeException(Transaction.DATE_CAN_NOT_BE_NULL);
		if (description.isEmpty())
			throw new RuntimeException(Buy.DESCRIPTION_CAN_NOT_BE_BLANK);

		return new Buy(codeProduct, amount, localDate, description);
	}

	@Override
	public CodeProduct getCodeProduct() {
		return codeProduct;
	}

	@Override
	public Integer getAmount() {
		return amount;
	}

	@Override
	public LocalDate getLocalDate() {
		return localDate;
	}

	public String getDescription() {
		return description;
	}
}
